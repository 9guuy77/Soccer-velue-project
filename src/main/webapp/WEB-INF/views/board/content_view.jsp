<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		.btn{
			text-decoration:none;
		}
	</style>
<link href="resources/css/write_view.css" rel="stylesheet" />
<link href="resources/css/content_view.css" rel="stylesheet" />
<link href="resources/css/comment.css" rel="stylesheet" />
<link href="resources/css/board_list.css" rel="stylesheet" />


<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script>
</script>
<script>
var user_ID;
$(document).ready(function(){
	user_ID=user();
	listReply();
	$(".comment_input").click(function() {//메인 댓글 작성
		$.ajax({
			url : "comment_write",
			type : 'post',
			data : JSON.stringify({"bId": "${content_view.bId}", "comment_content": $(".comment_content").val()}),
			dataType : "json",
	        contentType: "application/json;",
			success : function(data) {
				listReply();
                $(".comment_content").val("");
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		}); // ajax 끝
	});
});
function listReply(){	//메인댓글 리스트
	$.ajax({
		type:"get",
        contentType: "application/json;",
		url:"comment?bId=${content_view.bId}",
		success:function(result){
			var output="";
			for(var i in result){
				output+="<div class=\"comment_box\">";
				output+="<div class=\"comment_info\">";
				output+="<span>"+result[i].user_ID+"</span><span>"+result[i].comment_DATE+"</span></div>";
				output+="<div class=\"commend_text\"><p id=\"text"+result[i].comment_GROUP+"\">"+result[i].comment_TEXT+"</p></div>";
		 		output+="<button type=\"button\" class=\"comment_more"+result[i].comment_GROUP+"\" onclick=\"comment_more_reply("+result[i].comment_GROUP+",1)\">답글 "+result[i].group_COUNT+"개</button>";
		 		if(user_ID==result[i].user_ID){
		 		output+="<button type=\"button\" class=\"comment_update push\" data-rno="+result[i].comment_GROUP+" onclick=\"update_open("+result[i].comment_GROUP+",\'update_box\')\">수정</button>";
		 		output+="<button type=\"button\" class=\"comment_delete push\"  onclick=\"comment_delete(\'COMMENT_GROUP\',"+result[i].comment_GROUP+")\">삭제</button>";
		 		}
		 		output+="<div style=\"clear:both\"></div>";
		 		output+="<div class=\"update_box"+result[i].comment_GROUP+" more_text\" id=\"update_box"+result[i].comment_GROUP+"\"></div>"
		 		output+="<div class=\"more_comment"+result[i].comment_GROUP+" more_text\" id=\"more_comment"+result[i].comment_GROUP+"\"></div>"
				output+="</div>";
				}
			$(".new_comment").html(output);
		}
	})
}
function user(){
	var user_id="";
	$.ajax({
		type:"get",
        contentType: "application/json;",
		url:"userID",
		async:false,
		success:function(result){
			user_id=result;
		}
	})
	return user_id;
}
function comment_more_reply(comment_group,find){	//추가 댓글
	if(find>0){
		var comment_more_box = document.getElementById("more_comment"+comment_group);
		comment_more_box.style.display=(document.getElementById('more_comment'+comment_group).style.display=='block') ? 'none' : 'block';
	}
	$.ajax({
		type:"get",
        contentType: "application/json;",
		url:"comment_more?comment_group="+comment_group,
		success:function(result){
			var more_comment="";
			for(var i in result){
				more_comment+="<div class=\"comment_box\">";
				more_comment+="<div class=\"comment_info\">";
				more_comment+="<span>┗"+result[i].user_ID+"</span><span>"+result[i].comment_DATE+"</span></div>";
				more_comment+="<div class=\"commend_text\"><p id=\"text"+result[i].comment_STEP+"\">"+result[i].comment_TEXT+"</p></div>";
				if(user_ID==result[i].user_ID){
		 		more_comment+="<button type=\"button\" class=\"comment_more_update push\" data-rno="+result[i].comment_STEP+" onclick=\"update_open("+result[i].comment_STEP+",\'update_more_box\')\">수정</button>";
		 		more_comment+="<button type=\"button\" class=\"comment_more_delete push\" onclick=\"comment_delete(\'COMMENT_STEP\',"+result[i].comment_STEP+")\">삭제</button>";
				}
		 		more_comment+="<div class=\"update_more_box"+result[i].comment_STEP+"\" id=\"update_more_box"+result[i].comment_STEP+"\"></div>"
		 		more_comment+="<div class=\"more_comment"+result[i].comment_STEP+"\" id=\"more_comment"+result[i].comment_STEP+"\"></div>"
		 		more_comment+="</div>";
				}
			more_comment+="<div class=\"comment_insert\">";
			more_comment+="<div class=\"comment_text\">";
			more_comment+="<textarea name=\"comment_more_text"+comment_group+"\" rows=\"4\" cols=\"95\" class=\"comment_more_text"+comment_group+"\"></textarea></div>"
			more_comment+="<button type=\"button\" class=\"comment_input\" onclick=\"comment_more_write("+comment_group+")\">답글</button></div>"
			
			$(".more_comment"+comment_group).html(more_comment);
		}
	})
}
function comment_more_write(comment_group){		//추가댓글작성
	$.ajax({
		url : "comment_more_write",
		type : 'post',
		data : JSON.stringify({"bId": "${content_view.bId}","comment_group": String(comment_group), "comment_more_text": $(".comment_more_text"+comment_group).val()}),
		dataType : "json",
        contentType: "application/json;",
		success : function(data) {
			comment_more_reply(comment_group,0);
            $(".comment_content").val("");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	}); // ajax 끝
}

function comment_delete(comment_selet,comment_num){		//댓글삭제
	$.ajax({
		url : "comment_delete",
		type : 'post',
		data : JSON.stringify({"comment_num": String(comment_num),"comment_select":comment_selet}),
		dataType : "json",
        contentType: "application/json;",
		success : function(data) {
			listReply();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	}); // ajax 끝
}

function update_open(comment_group,comment_view){	//수정활성화
	var update_box = document.getElementById(""+comment_view+comment_group);
	update_box.style.display=(document.getElementById(comment_view+comment_group).style.display=='block') ? 'none' : 'block';
	update_box.style.height="60px";
	var update_text=document.getElementById('text'+comment_group).innerHTML;
	var update_floor="";
	update_floor+="<div class=\"comment_insert\">";
	update_floor+="<div class=\"comment_text\">";
	update_floor+="<textarea name=\"comment_text"+comment_group+"\" rows=\"4\" cols=\"90\" class=\"comment_text"+comment_group+"\">"+update_text+"</textarea></div>"
	if(comment_view==update_box){
		update_floor+="<button type=\"button\" class=\"comment_input\" onclick=\"update_comment("+comment_group+",\'comment_update\')\">수정</button></div>"}
	else{
		update_floor+="<button type=\"button\" class=\"comment_input\" onclick=\"update_comment("+comment_group+",\'comment_more_update\')\">수정</button></div>"}
	$("."+comment_view+comment_group).html(update_floor);
}
function update_comment(comment_group,comment_update){		//수정 작성
	var comment_text=$(".comment_text"+comment_group).val();
	$.ajax({
		url : comment_update,
		type : 'post',
		data : JSON.stringify({"comment_group": String(comment_group),"comment_text": comment_text}),
		dataType : "json",
        contentType: "application/json;",
		success : function(data) {
			listReply();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	}); // ajax 끝
}

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
<div class="view_box">
	<div class=title_box>
		<a href="list" class=Main_title>자유게시판</a>
	</div>
		<form action="modify" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<div class="menu_box">
				<div class="title_box_content">
					<span class="content_title">${content_view.bTitle}</span>
				</div>
				<div class="info_box">
					<div class="another_info">
						<span class="bName">${content_view.bName}</span>
						<span class="infocss">작성시간 : ${content_view.bDate}</span>
						<span class="infocss">조회수 : ${content_view.bHit}</span>
					</div>
				</div>
			</div>
			<div class="view_content">
				<span>${content_view.bContent}</span>
			</div>
			<div class="update_box">
				<a href="list" class="list_view">목록보기</a>
				<c:if test="${login_user==content_view.bName}">
					<a class="btn list_view delete" href="delete?bId=${content_view.bId}">삭제</a>
					<a class="btn list_view update" href="update_view?bId=${content_view.bId}">수정</a> 
				</c:if>
			</div>
		</form>	
		<div class="comment">
		    <h3>댓글쓰기</h3>
		    <div class="comment_insert">
		        <div class="comment_text">
		            <textarea name="comment_content" rows="4" cols="100" class="comment_content"></textarea>
		        </div>
		        <button type="button" class="comment_input">등록</button>
		    </div>
		</div>
	<div class="new_comment"></div>
</div>

</body>
</html>