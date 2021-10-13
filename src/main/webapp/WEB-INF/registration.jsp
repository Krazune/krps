<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body class="page">
		<form action="/registeruser" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username">

			<label for="password">Password:</label>
			<input type="password" id="password" name="password">

			<label for="password-confirmation">Password:</label>
			<input type="password" id="password-confirmation" name="password-confirmation">

			<input type="submit" value="Register">
		</form>
		<a href="/">Index</a>
    </body>
</html>
