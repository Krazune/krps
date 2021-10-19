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
		<form class="login-form login-form--limited-width" action="/loginuser" method="post">
			<p class="text login-form__title">Login</p>
			<label class="text login-form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username">

			<label class="text login-form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">

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
