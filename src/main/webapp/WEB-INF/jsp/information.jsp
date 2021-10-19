<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Information | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		KRPS - Krazune's Rock Paper Scissors
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<c:if test="${sessionScope.sessionUserName != null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/settings">Settings</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/logoutuser">Log out</a></li>
				</c:if>

				<c:if test="${sessionScope.sessionUserName == null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/registration">Register</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
				</c:if>
			</ul>
		</nav>
    </body>
</html>
