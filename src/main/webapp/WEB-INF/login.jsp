<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Log in | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body>
		<form action="/loginuser" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username">

			<label for="password">Password:</label>
			<input type="password" id="password" name="password">

			<input type="submit" value="Log in">
		</form>
		<a href="/">Index</a>
    </body>
</html>
