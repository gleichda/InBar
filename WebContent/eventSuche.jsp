<!DOCTYPE html>
<!-- Validation
@author sabine -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<title>Suche</title>
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
	
<!-- 04.01 Sabine kopiert von suche.jsp und auf UserSuche angepasst -->

	<article>
		<h1>Das ist die Suchseite für Events</h1>
	
		<form id="eventSuchForm" method="post" action="EventSuche">
			<div>
			<!-- Textfeld zur Suche nach dem Eventnamen -->
			<label for="suchbegriff">Suchbegriff: </label>
			<input type="text" name="suchbegriff" id="suchbegriff" placeholder="Suchbegriff" autofocus>
			</div>
			<br>
			<div>
			<!-- Textfeld zur Suche nach dem Startdatum -->
			<label for="start">Von: </label>
			<input type="date" name="start" id="start">
			</div>
			<br>
			<div>
			<!-- Textfeld zur Suche nach dem Enddatum -->
			<label for="ende">Bis: </label>
			<input type="date" name="ende" id="ende">
			</div>
			<br>

			<div>
				<button name="suchen" type="submit">Suchen</button>
			</div>
		</form>
	</article>
	<footer>
		<jsp:include page="./fragments/fusszeile.jsp"/>
	</footer>
</body>
</html>