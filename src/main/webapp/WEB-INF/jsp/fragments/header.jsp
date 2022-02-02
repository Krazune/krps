<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">
	<h2 class="text header__author">Krazune's</h2>
	<h1 class="text text--special header__title">Rock Paper Scissors</h1>
	<nav class="navigation">
		<ul class="list navigation__list">
			<c:if test="${showHomeLink}">
				<li><a class="text link navigation__link" href="/">Home</a></li>
			</c:if>
			<c:if test="${showLoginLink}">
				<li><a class="text link navigation__link" href="/login">Login</a></li>
			</c:if>
			<c:if test="${showRegistrationLink}">
				<li><a class="text link navigation__link" href="/registration">Registration</a></li>
			</c:if>
			<c:if test="${showStatisticsLink}">
				<li><a class="text link navigation__link" href="/statistics">Statistics</a></li>
			</c:if>
			<c:if test="${showInformationLink}">
				<li><a class="text link navigation__link" href="/information">Information</a></li>
			</c:if>
			<c:if test="${showSettingsLink}">
				<li><a class="text link navigation__link" href="/settings">Settings</a></li>
			</c:if>
			<c:if test="${showLogoutLink}">
				<li>
					<form method="post" action="/logout">
						<input class="text link navigation__link navigation__link--fake" type="submit" value="Logout">
					</form>
				</li>
			</c:if>
		</ul>
	</nav>
</header>
