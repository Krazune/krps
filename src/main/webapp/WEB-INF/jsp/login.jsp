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
		<c:forEach items="${usernameErrorMessages}" var="usernameErrorMessage">
			<c:out value="${usernameErrorMessage}"/>
		</c:forEach>

		<label for="password">Password</label>
		<input id="password" type="password" name="password">
		<c:forEach items="${passwordErrorMessages}" var="passwordErrorMessage">
			<c:out value="${passwordErrorMessage}"/>
		</c:forEach>

		<c:out value="${loginErrorMessage}"/>
		<input type="submit" name="login-submit" value="Log in">
		</form>
	</body>
</html>
