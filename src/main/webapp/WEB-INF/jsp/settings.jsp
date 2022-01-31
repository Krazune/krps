<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Settings - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form method="post" action="settings">
			<label for="current-password">Current password</label>
			<input id="current-password" type="password" name="current-password">
			<c:forEach items="${currentPasswordErrorMessages}" var="currentPasswordErrorMessage">
				<c:out value="${currentPasswordErrorMessage}"/>
			</c:forEach>

			<label for="new-password">New password</label>
			<input id="new-password" type="password" name="new-password">
			<c:forEach items="${newPasswordErrorMessages}" var="newPasswordErrorMessage">
				<c:out value="${newPasswordErrorMessage}"/>
			</c:forEach>

			<label for="new-password-confirmation">New password confirmation</label>
			<input id="new-password-confirmation" type="password" name="new-password-confirmation">
			<c:forEach items="${newPasswordConfirmationErrorMessages}" var="newPasswordConfirmationErrorMessage">
				<c:out value="${newPasswordConfirmationErrorMessage}"/>
			</c:forEach>

			<c:out value="${changePasswordErrorMessage}"/>
			<c:out value="${changePasswordSuccessMessage}"/>
			<input type="submit" name="change-password-submit" value="Change password">
		</form>
	</body>
</html>
