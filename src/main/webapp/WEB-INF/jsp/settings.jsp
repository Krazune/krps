<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Settings | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<form class="password-change-form password-change-form--limited-width" action="/settings" method="post">
			<p class="text password-change-form__title">Password settings</p>
			<label class="text registration-form__label" for="current-password">Current password:</label>
			<input class="text input-text" type="password" id="current-password" name="current-password">

			<label class="text registration-form__label" for="password">New password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<label class="text registration-form__label" for="password-confirmation">New password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">

			<c:forEach items="${newPasswordErrorMessages}" var="errorMessage">
				<p class="text text--error password-change-form__error">${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${newPasswordConfirmationErrorMessages}" var="errorMessage">
				<p class="text text--error password-change-form__error">${errorMessage}</p>
			</c:forEach>
			<c:if test="${accountErrorMessage != null}">
				<p class="text text--error password-change-form__error">${accountErrorMessage}</p>
			</c:if>

			<input class="text button password-change-form__button" type="submit" value="Change password">
		</form>
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="statistics" value="true"/>
			<jsp:param name="information" value="true"/>
			<jsp:param name="logout" value="true"/>
		</jsp:include>
    </body>
</html>
