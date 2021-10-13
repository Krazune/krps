<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body class="page">
		<header class="header">
			<h1 class="text header-title">ROCK PAPER SCISSORS</h1>
			<h2 class="text textheader-subtitle">by krazune</h2>
		</header>
		<form class="registration-form registration-form--limited-width" action="/registeruser" method="post">
			<p class="text registration-form__title">Registration</p>
			<label class="text registration-form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username">

			<label class="text registration-form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<label class="text registration-form__label" for="password-confirmation">Password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">

			<input class="text button registration-form__button" type="submit" value="Register">
		</form>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
			</ul>
		</nav>
    </body>
</html>
