<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Registration - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form method="post" action="registration">
		<label for="username">Username</label>
		<input id="username" type="text" name="username" value="${previousUsername}">

		<label for="password">Password</label>
		<input id="password" type="password" name="password">

		<label for="password-confirmation">Password confirmation</label>
		<input id="password-confirmation" type="password" name="password-confirmation">

		<input type="submit" name="registration-submit" value="Register">
		</form>
	</body>
</html>
