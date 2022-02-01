<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Information - KRPS</title>
		<link rel="stylesheet" type="text/css" href="/resources/styles/styles.css">
	</head>
	<body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<main class="article">
			<h2 class="text article__title">Technical notes</h2>
			<p class="text article__paragraph">Every password is hashed and salted, using <a class="text link" href="https://en.wikipedia.org/wiki/Argon2" target="_blank">Argon2</a>, before being stored in the database.</p>
			<p class="text article__paragraph">The full website source code is available on <a class="text link" href="https://github.com/Krazune/krps" target="_blank">GitHub</a>.</p>

			<hr class="hr">

			<h2 class="text article__title">Credits</h2>
			<p class="text information__p"><a class="text link" href="https://fonts.google.com/specimen/Dosis" target="_blank">Dosis</a> font by Impallari Type (license available <a class="text link" href="${pageContext.request.contextPath}/resources/fonts/Dosis_OFL.txt" target="_blank">here</a>).</p>
			<p class="text information__p"><a class="text link" href="https://fontawesome.com/" target="_blank">Font Awesome</a> icons by Font Awesome Team (licenses included in the SVG files).</p>
		</main>
</html>
