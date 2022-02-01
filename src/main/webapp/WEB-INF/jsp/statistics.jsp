<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Statistics - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<main class="article">
			<h2 class="text article__title">Global statistics</h2>
			<table class="table table--no-decoration">
				<tr>
					<th class="text text--left-align">Games</th>
					<th class="text text--left-align">Wins</th>
					<th class="text text--left-align">Losses</th>
					<th class="text text--left-align">Draws</th>
					<th class="text text--left-align">Rock</th>
					<th class="text text--left-align">Paper</th>
					<th class="text text--left-align">Scissors</th>
				</tr>
				<tr>
					<td class="text">${gameCount}</td>
					<td class="text">${gameWins}</td>
					<td class="text">${gameLosses}</td>
					<td class="text">${gameDraws}</td>
					<td class="text">${totalRocks}</td>
					<td class="text">${totalPapers}</td>
					<td class="text">${totalScissors}</td>
				</tr>
			</table>

			<c:if test="${showUserStatistics}">
				<hr class="hr">
				<h2 class="text article__title">User statistics</h2>
				<table class="table table--no-decoration">
					<tr>
						<th class="text text--left-align">Games</th>
						<th class="text text--left-align">Wins</th>
						<th class="text text--left-align">Losses</th>
						<th class="text text--left-align">Draws</th>
						<th class="text text--left-align">Rock</th>
						<th class="text text--left-align">Paper</th>
						<th class="text text--left-align">Scissosrs</th>
					</tr>
					<tr>
						<td class="text">${userGameCount}</td>
						<td class="text">${userWins}</td>
						<td class="text">${userLosses}</td>
						<td class="text">${userDraws}</td>
						<td class="text">${userRocks}</td>
						<td class="text">${userPapers}</td>
						<td class="text">${userScissors}</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${showLastGames}">
				<hr class="hr">
				<h2 class="text article__title">Last games</h2>
				<table class="table table--no-decoration">
					<tr>
						<th class="text text--left-align">Player choice</th>
						<th class="text text--left-align">Computer choice</th>
						<th class="text text--left-align">Result</th>
						<th class="text text--left-align">Date</th>
					</tr>
					<c:if test="${lastGames.size() == 0}">
						<tr>
							<td class="text">-</td>
							<td class="text">-</td>
							<td class="text">-</td>
							<td class="text">-</td>
						</tr>
					</c:if>
					<c:forEach items="${lastGames}" var="game">
						<tr>
							<td class="text">${game.getUserChoice()}</td>
							<td class="text">${game.getComputerChoice()}</td>
							<td class="text">${game.getOutcome()}</td>
							<td class="text"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${game.getCreationDate()}"/></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
	</body>
</html>
