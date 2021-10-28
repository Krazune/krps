<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
		<div class="statistics statistics--limited-width">
			<h2 class="text statistics__title">Global statistics</h2>
			<table class="table table--no-decorations">
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
					<td class="text">${winCount}</td>
					<td class="text">${lossCount}</td>
					<td class="text">${drawCount}</td>
					<td class="text">${userChoiceRockCount}</td>
					<td class="text">${userChoicePaperCount}</td>
					<td class="text">${userChoiceScissorsCount}</td>
				</tr>
			</table>

			<c:if test="${sessionScope.sessionUser != null}">
				<hr class="hr">

				<h2 class="text statistics__title">${sessionScope.sessionUser.getName()}'s statistics</h2>

				<table class="table table--no-decorations">
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
						<td class="text">${sessionUserGameCount}</td>
						<td class="text">${sessionUserWinCount}</td>
						<td class="text">${sessionUserLossCount}</td>
						<td class="text">${sessionUserDrawCount}</td>
						<td class="text">${sessionUserRockCount}</td>
						<td class="text">${sessionUserPaperCount}</td>
						<td class="text">${sessionUserScissorsCount}</td>
					</tr>
				</table>

				<hr class="hr">

				<c:if test="${lastGames != null}">
					<h2 class="text statistics__title">${sessionScope.sessionUser.getName()}'s most recent game(s)</h2>
					<table class="table">
						<tr class="table__row table__row--header">
							<th class="text table__data table__header">Player choice</th>
							<th class="text table__data table__header">Computer choice</th>
							<th class="text table__data table__header">Result</th>
							<th class="text table__data table__header">Date</th>
						</tr>
						<c:if test="${lastGames.size() == 0}">
							<tr class="table__row">
								<td class="text table__data">-</td>
								<td class="text table__data">-</td>
								<td class="text table__data">-</td>
								<td class="text table__data">-</td>
							</tr>
						</c:if>
						<c:forEach items="${lastGames}" var="game">
							<tr class="table__row">
								<td class="text table__data">${game.getUserChoice()}</td>
								<td class="text table__data">${game.getComputerChoice()}</td>
								<td class="text table__data">${game.getResult()}</td>
								<td class="text text--right-align table__data"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${game.getCreationDate()}"/></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</c:if>
		</div>
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
