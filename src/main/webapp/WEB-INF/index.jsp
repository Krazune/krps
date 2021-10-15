<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>KRPS</title>
		<c:if test="${sessionScope.sessionUserName != null}">
			<script src="${pageContext.request.contextPath}/resources/index.js"></script>
			<script src="${pageContext.request.contextPath}/resources/game-manager.js"></script>
		</c:if>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles.css">
    </head>
    <body class="page">
		<header class="header">
			<h1 class="text header-title">ROCK PAPER SCISSORS</h1>
			<h2 class="text textheader-subtitle">by krazune</h2>
		</header>
		<c:if test="${sessionScope.sessionUserName != null}">
			<div class="game game--limited-width">
				<div class="game__player-container game__player">
					<img id="player-choice-image" class="game__player-choice-image" src="${pageContext.request.contextPath}/resources/question.svg" alt="Unknown choice."/>
					<p class="text game__player-title">You</p>
				</div>
				<p class="text game__versus-label">VS</p>
				<div class="game__player-container game__computer">
					<img id="computer-choice-image" class="game__player-choice-image" src="${pageContext.request.contextPath}/resources/question.svg" alt="Unknown choice."/>
					<p class="text game__player-title">Computer</p>
				</div>
				<p id="game-description" class="text game__description">Choose your move, and confirm it.</p>
				<div class="game__choices">
					<button id="game-rock-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/hand-rock.svg" alt="Choose rock."/></button>
					<button id="game-paper-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/hand-paper.svg" alt="Choose paper."/></button>
					<button id="game-scissors-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/hand-scissors.svg" alt="Choose scissors."/></button>
				</div>
				<button id="game-confirm-button" class="button text game__confirm-button" disabled="true">Confirm</button>
			</div>
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