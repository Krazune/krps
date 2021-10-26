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
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		KRPS - Krazune's Rock Paper Scissors
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
