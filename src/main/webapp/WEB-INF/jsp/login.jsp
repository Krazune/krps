<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<form class="form form--limited-width" action="/login" method="post">
			<p class="text form__title">Login</p>
			<label class="text form__label" for="username">Username:</label>
			<input class="text input-text" type="text" id="username" name="username" value="${previousUsernameInput}">

			<label class="text form__label" for="password">Password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<c:forEach items="${usernameErrorMessages}" var="errorMessage">
				<p class="text text--error form__error">${errorMessage}</p>
			</c:forEach>
			<c:forEach items="${passwordErrorMessages}" var="errorMessage">
				<p class="text text--error form__error">${errorMessage}</p>
			</c:forEach>
			<c:if test="${accountErrorMessage != null}">
				<p class="text text--error form__error">${accountErrorMessage}</p>
			</c:if>

			<input class="text button form__submit-button" type="submit" value="Log in">
		</form>
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="registration" value="true"/>
			<jsp:param name="statistics" value="true"/>
			<jsp:param name="information" value="true"/>
		</jsp:include>
    </body>
</html>
