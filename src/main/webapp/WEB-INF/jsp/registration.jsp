<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Registration - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form class="form form--limited-width" method="post" action="registration">
			<h3 class="text form__title">Registration</h3>
			<label class="text form__label" for="username">Username</label>
			<input class="text text-input" id="username" type="text" name="username" value="${previousUsername}">
			<p class="text form__text">The username must have a minimum of 6 characters, and a maximum of 16 characters. Only letters and numbers are allowed.</p>

			<label class="text form__label" for="password">Password</label>
			<input class="text text-input" id="password" type="password" name="password">
			<p class="text form__text">The password must have a minimum of 6 characters, and a maximum of 64 characters.</p>

			<label class="text form__label" for="password-confirmation">Password confirmation</label>
			<input class="text text-input" id="password-confirmation" type="password" name="password-confirmation">
			<p class="text form__text">Both passwords must be equal.</p>

			<c:forEach items="${usernameErrorMessages}" var="usernameErrorMessage">
				<p class="text text--error form__text"><c:out value="${usernameErrorMessage}"/></p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="passwordErrorMessage">
				<p class="text text--error form__text"><c:out value="${passwordErrorMessage}"/></p>
			</c:forEach>
			<c:forEach items="${passwordConfirmationErrorMessages}" var="passwordConfirmationErrorMessage">
				<p class="text text--error form__text"><c:out value="${passwordConfirmationErrorMessage}"/></p>
			</c:forEach>
			<input class="text button form__button" type="submit" name="registration-submit" value="Register">
		</form>
	</body>
</html>
