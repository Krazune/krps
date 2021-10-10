<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>KRPS</title>
    </head>
    <body>
        <h1>Index page</h1>
		<c:if test="${sessionScope.sessionUserName != null}">
			<p>Welcome, ${sessionScope.sessionUserName}</p>
			<a href="/logoutuser">Log out</a>
		</c:if>
		<c:if test="${sessionScope.sessionUserName == null}">
			<a href="/login">Log in</a>
		</c:if>
    </body>
</html>