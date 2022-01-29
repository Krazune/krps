<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form method="post" action="/login">
		<label for="username">Username</label>
		<input id="username" type="text" name="username" value="${previousUsername}">

		<label for="password">Password</label>
		<input id="password" type="password" name="password">

		<input type="submit" name="login-submit" value="Log in">
		</form>
	</body>
</html>
