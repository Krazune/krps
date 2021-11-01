<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Settings | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form class="form form--limited-width" action="/settings" method="post">
			<p class="text form__title">Password settings</p>
			<label class="text form__label" for="current-password">Current password:</label>
			<input class="text input-text" type="password" id="current-password" name="current-password">

			<label class="text form__label" for="password">New password:</label>
			<input class="text input-text" type="password" id="password" name="password">
			<p class="text form__input-description">The password must have a minimum of 6 characters, and a maximum of 128 characters.</p>

			<label class="text form__label" for="password-confirmation">New password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">
			<p class="text form__input-description">Both passwords must be equal.</p>

			<c:forEach items="${newPasswordErrorMessages}" var="errorMessage">
				<p class="text text--error form__error">${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${newPasswordConfirmationErrorMessages}" var="errorMessage">
				<p class="text text--error form__error">${errorMessage}</p>
			</c:forEach>
			<c:if test="${accountErrorMessage != null}">
				<p class="text text--error form__error">${accountErrorMessage}</p>
			</c:if>

			<input class="text button form__submit-button" type="submit" value="Confirm">
		</form>
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="statistics" value="true"/>
			<jsp:param name="information" value="true"/>
			<jsp:param name="logout" value="true"/>
		</jsp:include>
    </body>
</html>
