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
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
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
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="settings" value="true"/>
			<jsp:param name="information" value="true"/>
			<jsp:param name="login" value="true"/>
			<jsp:param name="logout" value="true"/>
			<jsp:param name="registration" value="true"/>
		</jsp:include>
    </body>
</html>
