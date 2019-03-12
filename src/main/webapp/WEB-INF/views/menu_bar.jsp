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
			<a href="<c:url value="/login.do" />">�α���</a>
		</p>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<p>
			<a href="${pageContext.request.contextPath}/logout">�α׾ƿ�</a>
		</p>
		</sec:authorize>
		<p>
			<a href="Suggestions">���ǻ���</a>
		</p>
		<p>
			<a href="list">�����Խ���</a>
		</p>
		<p>
			<a href="">��������</a>
		</p>
	</div>
</div>
</body>
</html>