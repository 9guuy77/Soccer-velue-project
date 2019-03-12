<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
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
			<a href="board/Suggestions">건의사항</a>
		</p>
		<p>
			<a href="board/list">자유게시판</a>
		</p>
		<p>
			<a href="">선수정보</a>
		</p>
	</div>
</div>
</body>
</html>