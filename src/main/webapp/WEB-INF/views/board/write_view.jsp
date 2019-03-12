<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resources/Trumbowyg-master/dist/ui/trumbowyg.css" rel="stylesheet">
<link href="resources/css/write_view.css" rel="stylesheet" />
<link href="resources/css/board_list.css" rel="stylesheet" />
<style type="text/css">
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="resources/Trumbowyg-master/dist/trumbowyg.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
<div class="view_box">

	<div class=title_box>
		<a href="list" class=Main_title>자유게시판</a>
	</div>
	<c:if test="${content_view.bTitle eq null}">
	<form action="write" method="post">
		<p><div class="write_title"><input type="text" name="bTitle" placeholder="제목을 입력해 주세요" class="btitle"></div></p>
		<p><textarea class="form-control edit" id="contents" name="contents" rows="30" placeholder="내용"></textarea></p>
		<p>
			<a href="list" class=list_view>목록보기</a><input type="submit" value="등록" class=subput>
        </p>
	</form>
	</c:if>
	
	<c:if test="${content_view.bTitle ne null}">
	<form action="update" method="post">
		<input type="hidden" name="bId" value='${content_view.bId}'>
		<p><div class="update_title"><input type="text" name="bTitle" class="btitle" value='${content_view.bTitle}'></div></p>
		<p><textarea class="form-control edit" id="contents" name="contents" rows="30">${content_view.bContent}</textarea></p>
		<p><a href="list" class=list_view>목록보기</a><input type="submit" value="수정" class=subput></p>
	</form>
	</c:if>
</div>
<script>
$('.edit').trumbowyg({ lang : 'kr' });
</script>
</body>
</html>