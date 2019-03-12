<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="resources/css/menu_bar.css" rel="stylesheet" />
</head>
<body>
<div class="header_box">
	<div class="header_area">
		<sec:authorize access="isAnonymous()">
		<p>
			<a href="<c:url value="/login.do" />">로그인</a>
		</p>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<p>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		</p>
		</sec:authorize>
		<p>
			<a href="Suggestions">건의사항</a>
		</p>
		<p>
			<a href="list">자유게시판</a>
		</p>
		<p>
			<a href="">선수정보</a>
		</p>
	</div>
</div>
</body>
</html>