$(document).ready(function(){
	listReply();
	$(".comment_input").click(function() {
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
	function listReply(){
		$.ajax({
			type:"get",
			url:"comment?bId=${content_view.bId}",
			success:function(result){
				$(".new_commnet").html(result);
			}
		})
	}
	
});
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