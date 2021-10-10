<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Settings | KRSP</title>
    </head>
    <body>
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
