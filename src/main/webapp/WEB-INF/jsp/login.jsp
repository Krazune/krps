<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form class="form form--limited-width" method="post" action="/login">
			<h3 class="text form__title">Login</h3>
			<label class="text form__label" for="username">Username</label>
			<input class="text text-input" id="username" type="text" name="username" value="${previousUsername}">

			<label class="text form__label" for="password">Password</label>
			<input class="text text-input" id="password" type="password" name="password">

			<c:forEach items="${usernameErrorMessages}" var="usernameErrorMessage">
				<p class="text text--error form__text"><c:out value="${usernameErrorMessage}"/></p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="passwordErrorMessage">
				<p class="text text--error form__text"><c:out value="${passwordErrorMessage}"/></p>
			</c:forEach>
			<c:if test="${loginErrorMessage != null && !loginErrorMessage.isEmpty()}">
				<p class="text text--error form__text"><c:out value="${loginErrorMessage}"/></p>
			</c:if>
			<input class="text button form__button" type="submit" name="login-submit" value="Log in">
		</form>
	</body>
</html>
