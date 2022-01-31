<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<main class="game game--limited-width">
			<div class="game__player-profile">
				<div class="game__player-choice-container">
					<img class="game__player-choice" src="/resources/images/question.svg" alt="Unknown choice."/>
				</div>
				<p class="text game__player-name">Guest</p>
			</div>
			<p class="text game__vs-label">VS</p>
			<div class="game__player-profile">
				<div class="game__player-choice-container">
					<img class="game__player-choice" src="/resources/images/question.svg" alt="Unknown choice."/>
				</div>
				<p class="text game__player-name">Computer</p>
			</div>
			<p class="text game__status">Choose your move, and confirm it.</p>
			<div class="game__choices">
				<button class="game__choice-button"><img class="game__choice-image" src="/resources/images/question.svg" alt="Choose rock."/></button>
				<button class="game__choice-button"><img class="game__choice-image" src="/resources/images/question.svg" alt="Choose paper."/></button>
				<button class="game__choice-button"><img class="game__choice-image" src="/resources/images/question.svg" alt="Choose scissors."/></button>
			</div>
			<button class="button text game__confirm-button" disabled="true">Confirm</button>
			<p class="text game__account-warning">Register your account to keep track of your statistics.</p>
		</main>
	</body>
</html>
