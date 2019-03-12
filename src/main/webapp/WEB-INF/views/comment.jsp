<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script>
$(".comment_delete").click(function() {
	$.ajax({
		url : "comment_delete",
		type : 'post',
		data : JSON.stringify({"comment_group": $(this).attr("data-rno")}),
		dataType : "json",
        contentType: "application/json;",
		success : function(data) {
			location.reload();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	}); // ajax 끝
});
</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${comment}" var="dto">
	<div class="comment_box">
		<div class="dto.BOARD_NO">
			<span>${dto.USER_ID}</span><span>${dto.COMMENT_DATE}</span>
		</div>
		<div class="command_text">
			<p class="text">${dto.COMMENT_TEXT}</p>
		</div>
		<a class="group_list" href="delete?bId=${dto.COMMENT_GROUP}">댓글보기</a>
 		<button type="button" class="comment_update" data-rno="${dto.COMMENT_GROUP}">수정</button>
 		<button type="button" class="comment_delete" data-rno="${dto.COMMENT_GROUP}">삭제</button>
	</div>
	</c:forEach>	
</body>
</html>