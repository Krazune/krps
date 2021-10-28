<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Information | KRSP</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/styles.css">
    </head>
    <body class="page">
		<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
		<div class="information information--limited-width">
			<h2 class="text information__title">KRPS</h2>
			<p class="text information__p">Krazune's Rock Paper Scissors is yet another implementation of the rock, paper, scissors game, created by <a class="text link" href="https://twitter.com/krazune" target="_blank">krazune</a>. This website was created for entertainment, and educational purposes.</p>

			<hr class="hr">

			<h2 class="text information__title">Technical notes</h2>
			<p class="text information__p">Every password is hashed and salted, using <a class="text link" href="https://en.wikipedia.org/wiki/Argon2" target="_blank">Argon2</a>, before being stored in the database.</p>
			<p class="text information__p">The full website source code is available on <a class="text link" href="https://github.com/Krazune/krps" target="_blank">GitHub</a>.</p>

			<hr class="hr">

			<h2 class="text information__title">Additional credits</h2>
			<p class="text information__p"><a class="text link" href="https://fonts.google.com/specimen/Dosis" target="_blank">Dosis</a> font by Impallari Type (license available <a class="text link" href="${pageContext.request.contextPath}/resources/fonts/Dosis_OFL.txt" target="_blank">here</a>).</p>
			<p class="text information__p"><a class="text link" href="https://fontawesome.com/" target="_blank">Font Awesome</a> icons by Font Awesome Team (licenses included in the SVG files).</p>
		</div>
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/fragments/navigation.jsp">
			<jsp:param name="home" value="true"/>
			<jsp:param name="settings" value="true"/>
			<jsp:param name="statistics" value="true"/>
			<jsp:param name="logout" value="true"/>
			<jsp:param name="login" value="true"/>
			<jsp:param name="registration" value="true"/>
		</jsp:include>
    </body>
</html>
