<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<form class="registration-form registration-form--limited-width" action="/registration" method="post">
			<p class="text registration-form__title">Registration</p>
			<label class="text registration-form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username" value="${previousUsernameInput}">
			<p class="text registration-form__input-description">The username must have a minimum of 3 characters, and a maximum of 32 characters. Only letters and numbers are allowed.</p>

			<label class="text registration-form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">
			<p class="text registration-form__input-description">The password must have a minimum of 6 characters, and a maximum of 128 characters.</p>

			<label class="text registration-form__label" for="password-confirmation">Password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">
			<p class="text registration-form__input-description">Both passwords must be equal.</p>

			<c:forEach items="${usernameErrorMessages}" var="errorMessage">
				<p class="text text--error registration-form__error">${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="errorMessage">
				<p class="text text--error registration-form__error">${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordConfirmationErrorMessages}" var="errorMessage">
				<p class="text text--error registration-form__error">${errorMessage}</p>
			</c:forEach>
			<c:if test="${accountErrorMessage != null}">
				<p class="text text--error registration-form__error">${accountErrorMessage}</p>
			</c:if>

			<input class="text button registration-form__button" type="submit" value="Register">
		</form>
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="login" value="true"/>
			<jsp:param name="statistics" value="true"/>
			<jsp:param name="information" value="true"/>
		</jsp:include>
    </body>
</html>
