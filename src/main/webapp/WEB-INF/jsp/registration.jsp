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
		<c:forEach items="${usernameErrorMessages}" var="usernameErrorMessage">
			<c:out value="${usernameErrorMessage}"/>
		</c:forEach>

		<label for="password">Password</label>
		<input id="password" type="password" name="password">
		<c:forEach items="${passwordErrorMessages}" var="passwordErrorMessage">
			<c:out value="${passwordErrorMessage}"/>
		</c:forEach>

		<label for="password-confirmation">Password confirmation</label>
		<input id="password-confirmation" type="password" name="password-confirmation">
		<c:forEach items="${passwordConfirmationErrorMessages}" var="passwordConfirmationErrorMessage">
			<c:out value="${passwordConfirmationErrorMessage}"/>
		</c:forEach>

		<input type="submit" name="registration-submit" value="Register">
		</form>
	</body>
</html>
