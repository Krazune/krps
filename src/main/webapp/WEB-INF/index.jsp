<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>KRPS</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body class="page">
        <h1>Index page</h1>
		<c:if test="${sessionScope.sessionUserName != null}">
			<p>Game</p>
			<form action="/playgame" method="POST">
				<input type="radio" id="rock" name="decision" value="rock">
				<label for="rock">Rock</label>
				<input type="radio" id="paper" name="decision" value="paper">
				<label for="paper">Paper</label>
				<input type="radio" id="scissors" name="decision" value="scissors">
				<label for="scissors">Scissors</label>
				<input type="submit" value="Confirm">
			</form>
		</c:if>
		<c:if test="${sessionScope.sessionUserName != null}">
			<p>Welcome, ${sessionScope.sessionUserName}</p>
			<a href="/settings">Settings</a>
			<a href="/logoutuser">Log out</a>
		</c:if>
		<c:if test="${sessionScope.sessionUserName == null}">
			<a href="/login">Log in</a>
			<a href="/registration">Register</a>
		</c:if>
    </body>
</html>