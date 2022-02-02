<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
		<script src="${pageContext.request.contextPath}/resources/scripts/index.js"></script>
		<script src="${pageContext.request.contextPath}/resources/scripts/game-manager.js"></script>
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<main class="game game--limited-width">
			<div class="game__player-profile game__player-profile--user">
				<div class="game__player-choice-container">
					<img id="user-choice-image" class="game__player-choice" src="${pageContext.request.contextPath}/resources/images/question.svg" alt="Unknown choice."/>
				</div>
				<p class="text game__player-name"><c:out value="${gameUsername}"/></p>
			</div>
			<p class="text game__vs-label">VS</p>
			<div class="game__player-profile game__player-profile--computer">
				<div class="game__player-choice-container">
					<img id="computer-choice-image" class="game__player-choice" src="${pageContext.request.contextPath}/resources/images/question.svg" alt="Unknown choice."/>
				</div>
				<p class="text game__player-name">Computer</p>
			</div>
			<p id="game-status" class="text game__status">Choose your move, and confirm it.</p>
			<div class="game__choices">
				<button id="game-choice-rock" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-rock.svg" alt="Choose rock."/></button>
				<button id="game-choice-paper" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-paper.svg" alt="Choose paper."/></button>
				<button id="game-choice-scissors" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-scissors.svg" alt="Choose scissors."/></button>
			</div>
			<button id="game-confirm-button" class="button text game__confirm-button" disabled="true">Confirm</button>
			<c:if test="${showAccountWarning}">
				<p class="text game__account-warning">Register your account to keep track of your games.</p>
			</c:if>
		</main>
	</body>
</html>
