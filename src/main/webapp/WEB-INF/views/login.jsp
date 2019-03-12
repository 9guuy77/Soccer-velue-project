<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="resources/css/login_signup.css" rel="stylesheet" />
<title>로그인 페이지</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu_bar.jsp" flush="false"/>
<div class="login_info">
	<h3>로그인</h3>
	<c:url value="/login" var="loginUrl" />
	<form:form name="f" action="${loginUrl}" method="POST">
		<div class="login_error">
		<c:if test="${param.error != null}">
			<p>아이디와 비밀번호가 잘못되었습니다.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>로그아웃 하였습니다.</p>
		</c:if>
		</div>
		<p><label for="username">아이디</label> 
		</p>
			<p><input type="text" class="input" id="email" name="email" placeholder="아이디를 입력하세요." /></p>
		<p><label for="password">비밀번호</label>
		</p> 
		<p><input type="password" class="input" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
		</p>
		<p><button type="submit" class="login_btn">로그인</button></p>
		<a class="sign_btn" href="sign_up">회원가입</a>
	</form:form>
</div>
</body>
</html>
