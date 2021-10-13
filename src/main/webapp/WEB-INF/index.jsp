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
		<header class="header">
			<h1 class="text header-title">ROCK PAPER SCISSORS</h1>
			<h2 class="text textheader-subtitle">by krazune</h2>
		</header>
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
		<nav class="navigation">
			<ul class="navigation__list">
				<c:if test="${sessionScope.sessionUserName != null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/settings">Settings</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/logoutuser">Log out</a></li>
				</c:if>

				<c:if test="${sessionScope.sessionUserName == null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/registration">Register</a></li>
				</c:if>
			</ul>
		</nav>
    </body>
</html>