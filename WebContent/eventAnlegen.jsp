<!DOCTYPE html>
<!-- @author David -->
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Registrierung für Bars</title>
</head>
<body>
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
<!-- 		06.01 start Sabine input hidden um die Bar-ID zu erhalten -->
<%-- 		<form action="BarAnzeigen">
				<input type="hidden" name="id" value="${bar.barid}">
				<input type="hidden" name="bearbeiten" value="1">
    			<button type="submit">Bearbeiten</button>
		</form> --%>
<!-- 		06.01 ende Sabine input hidden um die Bar-ID zu erhalten -->
		<form id="eventAnlegen" action="EventAnlegenServlet" method="post"
			enctype="multipart/form-data">

			<div>
				<label for="eventname">Name des Events: </label> <input type="text"
					name="eventname" id="eventname" value="">
				<label for="startdatum"> Bitte das Start-Datum waehlen</label> <input type="date" name="startdatum" id="startdatum" value="">
				<label for="start"> Bitte Startuhrzeit auswaehlen</label> <input type="time" name="start" id="start" value="">	
				<label for="enddatum"> Bitte das End-Datum waehlen</label> <input type="date" name="enddatum" id="enddatum" value="">						
				<label for="ende"> Bitte Enduhrzeit auswaehlen </label> <input type="time" name="ende" id="ende" value="">
				<!-- TODO: Aktuelles Datum vorschlagen https://stackoverflow.com/questions/6982692/html5-input-type-date-default-value-to-today -->				
			</div>
			<br>
			<textarea name="ebeschreibung" id="ebeschreibung" placeholder="Beschreiben Sie ihr Event." rows="5" cols="100"></textarea>
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