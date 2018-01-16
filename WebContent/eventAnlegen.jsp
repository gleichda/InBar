<!DOCTYPE html>
<!-- @author Sabine -->
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/datumPruefen.js"></script>
<meta charset="UTF-8">
<title>Event anlegen</title>
</head>
<body>
	<noscript>Ihr Browser erlaubt die Ausführung von Javascript nicht. Bitte aktivieren sie Javascript um die vollständigen Funktionalitäten der Website zu verwenden</noscript>
	<header>
		<%@include file="./fragments/header.jsp"%>
	</header>
	<nav>
		<c:choose>
			<c:when test="${not empty selfUser.vorname}">
				<jsp:include page="./fragments/navigationEingeloggt.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="./fragments/navigation.jsp" />
			</c:otherwise>
		</c:choose>
	</nav>

	<article>
		<h2>Neues Event anlegen</h2>
		<p>
			Hier koennen Sie ein neues Event anlegen
		</p>
		<form id="eventAnlegen" action="EventAnlegenServlet" method="post"
			enctype="multipart/form-data">

			<div>
				<label for="eventname">Name des Events: </label> <input type="text"
					name="eventname" id="eventname" value="" title="Geben Sie Ihrem Event einen Namen.">
				<label for="startdatum"> Bitte das Start-Datum waehlen</label> 
				<input type="date" name="startdatum" id="startdatum" value="" title="Datum an dem das Event startet." required> 
				<span></span>
				<label for="start"> Bitte Startuhrzeit auswaehlen</label> 
				<input type="time" name="start" id="start" value="" title="Uhrzeit um die das Event startet." required> 
				<span></span>	
				<label for="enddatum"> Bitte das End-Datum waehlen</label> 
				<input type="date" name="enddatum" id="enddatum" value="" title="Tag an dem das Event endet. Achtung: Das End-Datum muss nach dem Start-Datum liegen." required> 
				<span></span>						
				<label for="ende"> Bitte Enduhrzeit auswaehlen </label> 
				<input type="time" name="ende" id="ende" value="" title="Uhrzeit um die das Event endet. Achtung: Wenn Start und Ende am gleichen Datum ist, muss der Endzeitpunkt nach dem Startzeitpunkt liegen." required> 
				<span></span>
			</div>
			<br>
			<textarea name="ebeschreibung" id="ebeschreibung" placeholder="Beschreiben Sie ihr Event." rows="5" cols="100" title="Beschreiben Sie das Event."></textarea> 
			<span></span>
			<br>

			<input type="hidden" name="barid" value="${param.barid}">
			<button id="event_anlegen" type="submit">Event anlegen</button>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>