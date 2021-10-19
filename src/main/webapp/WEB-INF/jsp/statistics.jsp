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
		<p>Total games: ${totalGameCount}</p>
		<p>Total wins: ${totalWinCount}</p>
		<p>Total losses: ${totalLossCount}</p>
		<p>Total draws: ${totalDrawCount}</p>
		<p>Rock chosen: ${playerRockCount}</p>
		<p>Paper chosen: ${playerPaperCount}</p>
		<p>Scissors chosen: ${playerScissorsCount}</p>

		<c:if test="${sessionScope.sessionUserName != null}">
			<p>${sessionScope.sessionUserName}'s statistics</p>
			<p>Total games: ${userGameCount}</p>
			<p>Total wins: ${userWinCount}</p>
			<p>Total losses: ${userLossCount}</p>
			<p>Total draws: ${userDrawCount}</p>
			<p>Rock chosen: ${userRockCount}</p>
			<p>Paper chosen: ${userPaperCount}</p>
			<p>Scissors chosen: ${playerScissorsCount}</p>
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
							<td>${game.getCPUChoice()}</td>
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
				<c:if test="${sessionScope.sessionUserName != null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/settings">Settings</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/logoutuser">Log out</a></li>
				</c:if>

				<c:if test="${sessionScope.sessionUserName == null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/login">Log in</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/registration">Register</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
				</c:if>
			</ul>
		</nav>
    </body>
</html>
