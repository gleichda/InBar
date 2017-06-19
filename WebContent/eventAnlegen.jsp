<!DOCTYPE html>
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

		<form id="barRegistrierung" action="BarRegisterServlet" method="post"
			enctype="multipart/form-data">

			<div>
				<label for="eventname">Name des Events: </label> <input type="text"
					name="eventname" id="eventname" value="">
				<label for="datum"> Bitte Datum waehlen</label> <input type="date" name=datum id="datum" value="">
				<!-- TODO: Aktuelles Datum vorschlagen https://stackoverflow.com/questions/6982692/html5-input-type-date-default-value-to-today -->				
			</div>
			<br>
			<textarea name="eventBeschreibung" id="eventBeschreibung" placeholder="Beschreiben Sie ihr Event." rows="5" cols="100"></textarea>
			<br>
			<button id="event_anlegen" type="submit">Event anlegen</button>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>