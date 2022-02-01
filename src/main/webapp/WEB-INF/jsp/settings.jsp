<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Settings - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form class="form form--limited-width" method="post" action="settings">
			<h3 class="text form__title">Change password</h3>
			<label class="text" for="current-password">Current password</label>
			<input class="text text-input" id="current-password" type="password" name="current-password">

			<label class="text" for="new-password">New password</label>
			<input class="text text-input" id="new-password" type="password" name="new-password">

			<label class="text" for="new-password-confirmation">New password confirmation</label>
			<input class="text text-input" id="new-password-confirmation" type="password" name="new-password-confirmation">

			<c:forEach items="${currentPasswordErrorMessages}" var="currentPasswordErrorMessage">
				<p class="text text--error"><c:out value="${currentPasswordErrorMessage}"/></p>
			</c:forEach>
			<c:forEach items="${newPasswordErrorMessages}" var="newPasswordErrorMessage">
				<p class="text text--error"><c:out value="${newPasswordErrorMessage}"/></p>
			</c:forEach>
			<c:forEach items="${newPasswordConfirmationErrorMessages}" var="newPasswordConfirmationErrorMessage">
				<p class="text text--error"><c:out value="${newPasswordConfirmationErrorMessage}"/></p>
			</c:forEach>
			<c:if test="${changePasswordErrorMessage != null && !changePasswordErrorMessage.isEmpty()}">
				<p class="text text--error"><c:out value="${changePasswordErrorMessage}"/></p>
			</c:if>
			<c:if test="${changePasswordSuccessMessage != null && !changePasswordSuccessMessage.isEmpty()}">
				<p class="text"><c:out value="${changePasswordSuccessMessage}"/></p>
			</c:if>
			<input class="text button" type="submit" name="change-password-submit" value="Change password">
		</form>
	</body>
</html>
