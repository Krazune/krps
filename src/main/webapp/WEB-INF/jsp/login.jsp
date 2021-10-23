<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<form class="login-form login-form--limited-width" action="/login" method="post">
			<p class="text login-form__title">Login</p>
			<label class="text login-form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username" value="${previousUsernameInput}">

			<label class="text login-form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<c:forEach items="${usernameErrorMessages}" var="errorMessage">
				<p>${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="errorMessage">
				<p>${errorMessage}</p>
			</c:forEach>
			<c:if test="${accountErrorMessage != null}">
				<p>${accountErrorMessage}</p>
			</c:if>

			<input class="text button login-form__button" type="submit" value="Log in">
		</form>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/registration">Register</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
			</ul>
		</nav>
    </body>
</html>
