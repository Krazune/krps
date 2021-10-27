<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navigation">
	<ul class="navigation__list">
		<c:if test="${param.home != null && param.home.equalsIgnoreCase('true')}">
			<li class="navigation__list-item"><a class="text link" href="/">Home</a></li>
		</c:if>

		<c:if test="${param.login != null && param.login.equalsIgnoreCase('true') && sessionScope.sessionUser == null}">
			<li class="navigation__list-item"><a class="text link" href="/login">Log in</a></li>
		</c:if>

		<c:if test="${param.registration != null && param.registration.equalsIgnoreCase('true') && sessionScope.sessionUser == null}">
			<li class="navigation__list-item"><a class="text link" href="/registration">Register</a></li>
		</c:if>

		<c:if test="${param.statistics != null && param.statistics.equalsIgnoreCase('true')}">
			<li class="navigation__list-item"><a class="text link" href="/statistics">Statistics</a></li>
		</c:if>

		<c:if test="${param.information != null && param.information.equalsIgnoreCase('true')}">
			<li class="navigation__list-item"><a class="text link" href="/information">Information</a></li>
		</c:if>

		<c:if test="${param.settings != null && param.settings.equalsIgnoreCase('true') && sessionScope.sessionUser != null}">
			<li class="navigation__list-item"><a class="text link" href="/settings">Settings</a></li>
		</c:if>

		<c:if test="${param.logout != null && param.logout.equalsIgnoreCase('true') && sessionScope.sessionUser != null}">
			<li class="navigation__list-item">
				<form method="post" action="/logoutuser">
					<input class="text link" type="submit" value="Log out">
				</form>
			</li>
		</c:if>
	</ul>
</nav>