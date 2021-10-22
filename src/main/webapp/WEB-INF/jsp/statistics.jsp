<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Statistics | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<p>Global statistics</p>
		<p>Total games: ${gameCount}</p>
		<p>Total wins: ${winCount}</p>
		<p>Total losses: ${lossCount}</p>
		<p>Total draws: ${drawCount}</p>
		<p>Rock chosen: ${userChoiceRockCount}</p>
		<p>Paper chosen: ${userChoicePaperCount}</p>
		<p>Scissors chosen: ${userChoiceScissorsCount}</p>

		<c:if test="${sessionScope.sessionUser != null}">
			<p>${sessionScope.sessionUser.getName()}'s statistics</p>
			<p>Total games: ${sessionUserGameCount}</p>
			<p>Total wins: ${sessionUserWinCount}</p>
			<p>Total losses: ${sessionUserLossCount}</p>
			<p>Total draws: ${sessionUserDrawCount}</p>
			<p>Rock chosen: ${sessionUserRockCount}</p>
			<p>Paper chosen: ${sessionUserPaperCount}</p>
			<p>Scissors chosen: ${sessionUserScissorsCount}</p>
			<c:if test="${lastGames != null}">
				Last games
				<table>
					<tr>
						<th>Player choice</th>
						<th>Computer choice</th>
						<th>Result</th>
						<th>Date</th>
					</tr>
					<c:if test="${lastGames.size() == 0}">
						<tr>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</tr>
					</c:if>
					<c:forEach items="${lastGames}" var="game">
						<tr>
							<td>${game.getUserChoice()}</td>
							<td>${game.getComputerChoice()}</td>
							<td>${game.getResult()}</td>
							<td>${game.getCreationDate()}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:if>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<c:if test="${sessionScope.sessionUser != null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/settings">Settings</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
					<li class="navigation__list-item">
						<form method="post" action="/logoutuser">
							<input class="text navigation__link" type="submit" value="Log out">
						</form>
					</li>
				</c:if>

				<c:if test="${sessionScope.sessionUser == null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/registration">Register</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
				</c:if>
			</ul>
		</nav>
    </body>
</html>
