<!DOCTYPE html>
<!--  Validation OK 
@author sabine
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Startseite</title>
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
		<h2>Willkommen bei IN<img src="./img/INBars_Glas_Gruen2.jpg" class="glas" alt="*">Bars</h2>
		<p>Wir bieten eine Plattform an, über die Sie Bars, Kneipen und Lokale in Ihrer Nähe finden können und die Ihren Interessen entsprechen.</p>
		
		<img src="./img/mitkommen_feiern-500x500.jpg" class="big" alt="Feiern"> <!-- Bild-Quelle: https://www.berlin-mitte-institut.de/du-du-und-du-mitkommen-feiern/ -->
		<img src="./img/Freitag_Montag.jpg" class="big" alt="Feiern"> <!-- Bild-Quelle: https://www.grusskartenshop.de/postkarten/postkarten-spr%C3%BCche-lustige-postkarten-feiern-party-freitag-montag/nacht-freitag-montag -->		
		
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>