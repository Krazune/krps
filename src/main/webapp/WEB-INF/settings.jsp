<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Settings | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body class="page">
		<header class="header">
			<h1 class="text header-title">ROCK PAPER SCISSORS</h1>
			<h2 class="text textheader-subtitle">by krazune</h2>
		</header>
		<form action="/changeuserpassword" method="post">
			<label for="username">Current password:</label>
			<input type="password" id="username" name="username">

			<label for="password">New password:</label>
			<input type="password" id="password" name="password">

			<label for="password-confirmation">New password:</label>
			<input type="password" id="password-confirmation" name="password-confirmation">

			<input type="submit" value="Change password">
		</form>
		<a href="/">Index</a>
    </body>
</html>
