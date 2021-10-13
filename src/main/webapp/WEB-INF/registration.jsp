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
		<form action="/registeruser" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username">

			<label for="password">Password:</label>
			<input type="password" id="password" name="password">

			<label for="password-confirmation">Password:</label>
			<input type="password" id="password-confirmation" name="password-confirmation">

			<input type="submit" value="Register">
		</form>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
			</ul>
		</nav>
    </body>
</html>
