<!DOCTYPE html>
<!--   
@author Sabine -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/datumPruefen.js"></script>
<meta charset="UTF-8">
<title>IN-Bars Event-Bearbeitung</title>
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
		<h2>Bearbeitung für Events</h2>
		<p>
			Die hinterlegten Daten ihres Events.
		</p>
		

		<form id="eventBearbeiten" action="EventBearbeiten">
			<p>
				<label for="eventname">Name des Events: </label> 
				<input type="text" name="eventname" id="eventname" value="${event.eventname}"> 
<%-- 				<input type="text" name="eventid" id="eventid" value="${event.eventid}"> --%>
			</p>
			<br>
			
			<p>
				<label for="startdatum"> Startdatum: </label> 
				<input type="date" name="startdatum" id="startdatum" value="${event.startdatum}" required> 
				<label for="startzeit"> Startzeitpunkt: </label>
				<input type="time" name="startzeit" id="startzeit" value="${event.startzeit}" required>
			</p>
			<p>
				<label for="enddatum"> Enddatum: </label> 
				<input type="date" name="enddatum" id="enddatum" value="${event.enddatum}" required> 
				<label for="endzeit"> Endzeitpunkt: </label>
				<input type="time" name="endzeit" id="endzeit" value="${event.endzeit}" required>
			</p>
			<br>
			<p>
				<label for = "ebeschreibung"> Eventbeschreibung: </label>
				<input type="text" name="ebeschreibung" id="ebeschreibung" value ="${event.ebeschreibung}"> 

			<p>
				<input type="hidden" name="id" value="${bar.barid}">
				<input type="hidden" name="eventid" value="${event.eventid}">
				<button id="event_aendern" type="submit">Speichern</button>
			</p>

		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>