<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Settings | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<form class="password-change-form password-change-form--limited-width" action="/changeuserpassword" method="post">
			<p class="text password-change-form__title">Password settings</p>
			<label class="text registration-form__label" for="current-password">Current password:</label>
			<input class="text input-text" type="password" id="current-password" name="current-password">

			<label class="text registration-form__label" for="password">New password:</label>
			<input class="text input-text" type="password" id="password" name="password">

			<label class="text registration-form__label" for="password-confirmation">New password (confirmation):</label>
			<input class="text input-text" type="password" id="password-confirmation" name="password-confirmation">

			<input class="text button password-change-form__button" type="submit" value="Change password">
		</form>
		<nav class="navigation">
			<ul class="navigation__list">
				<li class="navigation__list-item"><a class="text navigation__link" href="/">Home</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/statistics">Statistics</a></li>
				<li class="navigation__list-item"><a class="text navigation__link" href="/information">Information</a></li>
				<li class="navigation__list-item">
					<form method="post" action="/logoutuser">
						<input class="text navigation__link" type="submit" value="Log out">
					</form>
				</li>
			</ul>
		</nav>
    </body>
</html>
