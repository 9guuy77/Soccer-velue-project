<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link href="resources/css/sign_up.css" rel="stylesheet" />
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
<div class="sign_up_box">
	<h2>회원가입</h2>
</div>
<div class="form-box">
	<form:form role="form" commandName="User_Dto" action="sign_up" method="post">
		<div class="form-group">
			<p>아이디</p>
			<form:input type="text" class="UserId" placeholder="ID" path="UserId" />
			<form:errors path="UserId" cssClass="error" />
			<button type="button" class="IdCheck">중복확인</button>
			<p class="result">
				<span class="msg"></span>
			</p>
		</div>
		<div class="form-group">
			<p>비밀번호</p>
			<form:password class="form-control" placeholder="Password"
				path="Password" />
			<p><form:errors path="Password" cssClass="error" /></p>
		</div>
		<div class="form-group">
			<p>비밀번호 확인</p>
			<form:password class="form-control" placeholder="Password Check"
				path="checkPw" />
			<p><form:errors path="checkPw" cssClass="error" /></p>
		</div>
		<div class="form-group">
			<p>이메일 주소</p>
			<form:input type="email" class="form-control" placeholder="Email"
				path="Email" />
		</div>
		<button type="submit" class="sign_btn" disabled="disabled">가입하기</button>
		<button type="reset" class="btn btn-default">취소하기</button>
	</form:form>
</div>
	<script type="text/javascript">
		$(".UserId").keyup(function(){
			 $(".result .msg").text("아이디를 확인해주십시오.");
			 $(".result .msg").attr("style", "color:#000");
			 $(".sign_btn").attr("disabled", "disabled");
		});
		$(".IdCheck").click(function() {
			var query = $(".UserId").val();
			$.ajax({
				url : "idCheck",
				type : 'post',
				data : query,
				dataType : "json",
	            contentType: "application/json;",
				success : function(data) {
					if (data == 1) {
						$(".result .msg").text("사용 불가");
						$(".result .msg").attr("style", "color:#f00");
						$(".sign_btn").attr("disabled", "disabled");
					} else {
						$(".result .msg").text("사용 가능");
						$(".result .msg").attr("style", "color:#00f");
						$(".sign_btn").removeAttr("disabled");
					}
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			}); // ajax 끝
		});
	</script>
</body>
</html>