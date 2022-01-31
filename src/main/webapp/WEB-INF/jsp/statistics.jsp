<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Statistics - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<table>
			<tr>
				<th class="text">Games</th>
				<th class="text">Wins</th>
				<th class="text">Losses</th>
				<th class="text">Draws</th>
				<th class="text">Rock</th>
				<th class="text">Paper</th>
				<th class="text">Scissors</th>
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
	</body>
</html>
