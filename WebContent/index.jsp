<!DOCTYPE html>
<!--  Validation OK 
@author sabine
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Login</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp" %>
	</header>
	<nav>
		<c:choose>
		<c:when test="${not empty selfUser.vorname}"> 
			<jsp:include page="./fragments/navigationEingeloggt.jsp"/>
		</c:when>
		<c:otherwise>
					<jsp:include page ="./fragments/navigation.jsp"/>
		</c:otherwise>
		</c:choose>		
	</nav>

	<article>
		<h2>Willkommen bei IN*Bars</h2>
		<p>Wir bieten eine Plattform über die Sie Bars, Kneipen und Lokale in Ihrer Nähe finden und die Ihren Interessen entsprechen.</p>
		

	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>