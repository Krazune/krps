<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>KRPS</title>
		<c:if test="${sessionScope.sessionUser != null}">
			<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
			<script src="${pageContext.request.contextPath}/resources/js/game-manager.js"></script>
		</c:if>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<div class="game game--limited-width">
			<div class="game__player-container game__player">
				<img id="player-choice-image" class="game__player-choice-image" src="${pageContext.request.contextPath}/resources/images/question.svg" alt="Unknown choice."/>
				<p class="text game__player-title">You</p>
			</div>
			<p class="text game__versus-label">VS</p>
			<div class="game__player-container game__computer">
				<img id="computer-choice-image" class="game__player-choice-image" src="${pageContext.request.contextPath}/resources/images/question.svg" alt="Unknown choice."/>
				<p class="text game__player-title">Computer</p>
			</div>
			<c:if test="${sessionScope.sessionUser == null}">
			<p id="game-description" class="text game__description">Log in, or create an account to play.</p>
			</c:if>
			<c:if test="${sessionScope.sessionUser != null}">
			<p id="game-description" class="text game__description">Choose your move, and confirm it.</p>
			</c:if>
			<div class="game__choices">
				<button id="game-rock-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-rock.svg" alt="Choose rock."/></button>
				<button id="game-paper-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-paper.svg" alt="Choose paper."/></button>
				<button id="game-scissors-choice" class="game__choice-button"><img class="game__choice-image" src="${pageContext.request.contextPath}/resources/images/hand-scissors.svg" alt="Choose scissors."/></button>
			</div>
			<c:if test="${sessionScope.sessionUser != null}">
			<button id="game-confirm-button" class="button text game__confirm-button" disabled="true">Confirm</button>
			</c:if>
		</div>
		<c:if test="${sessionScope.sessionUser == null}">
			<div class="account-buttons">
				<a class="button text account-buttons__button" href="/login">Log in</a>
				<a class="button text account-buttons__button" href="/registration">Register</a>
			</div>
		</c:if>
		<nav class="navigation">
			<ul class="navigation__list">
				<c:if test="${sessionScope.sessionUser != null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/settings">Settings</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/logoutuser">Log out</a></li>
				</c:if>

				<c:if test="${sessionScope.sessionUser == null}">
					<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
					<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
				</c:if>
			</ul>
		</nav>
    </body>
</html>