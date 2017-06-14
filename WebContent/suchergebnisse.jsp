<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ergebnisse der Suche</title>
</head>
<body>
	<nav>
	<%@include file="navigation.jsp" %>
	</nav>
	
	<h1>Suchergebnisse</h1>
	<h3>Ihre Sucheingaben:</h3>
	<br>
	<b>Gesuchter Begriff </b>${form.suchbegriff}
	<br>
	<b>Du suchst nach:</b>${form.suchart}

	<c:if test="${not empty form.musikart}">
		<c:forEach var="art" items="${form.musikart}" varStatus="status">
			<br>Musikart ${status.count}: ${art}
		</c:forEach>
	</c:if>
	
	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>

</html>