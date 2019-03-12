<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <div class="header_area">
		<sec:authorize access="isAnonymous()">
			<p>
				<a href="<c:url value="/login.do" />">로그인</a>
			</p>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<form:form action="${pageContext.request.contextPath}/logout"
				method="POST">
				<input type="submit" value="로그아웃" />
			</form:form>
		</sec:authorize>
		<p>
			<a href="">선수정보</a>
		<p>
			<a href="board/list">자유게시판</a>
		</p>
		<p>
			<a href="board/Suggestions">건의사항</a>
		</p>
	</div>
</body>
</html>