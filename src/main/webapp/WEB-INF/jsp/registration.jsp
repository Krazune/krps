<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<form class="registration-form registration-form--limited-width" action="/registration" method="post">
			<p class="text registration-form__title">Registration</p>
			<label class="text registration-form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username">

			<label class="text registration-form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<label class="text registration-form__label" for="password-confirmation">Password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">

			<c:forEach items="${usernameErrorMessages}" var="errorMessage">
				<p>${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="errorMessage">
				<p>${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordConfirmationErrorMessages}" var="errorMessage">
				<p>${errorMessage}</p>
			</c:forEach>

			<input class="text button registration-form__button" type="submit" value="Register">
		</form>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
			</ul>
		</nav>
    </body>
</html>
