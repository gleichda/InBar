<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suche</title>
</head>

<body>
	<nav>
	<%@include file="navigation.jsp" %>
	</nav>
	
	<h1>Das ist die Suchseite</h1>

	<form id="suchForm" method="post" action="Suchservlet">
		<!-- Textfeld zur Suche nach dem Barnamen -->
		<p>Name der Bar:</p>
		<input type="text" name="suchbegriff" placeholder="Barname" autofocus>
		<br>
		<p>Suchst du eine Bar oder ein Event?</p>
		<!-- Radio-Button zur Auswahl der Suchart. -->
		<input type="radio" name="suchart" value="Bar" id="sa1" checked>
		<label for="sa1">Bar</label><br> <input type="radio"
			name="suchart" value="Event" id="sa2">Event <br> <label
			for="sa2">Event</label><br>

		<p>Musikart:</p>
		<!-- Checkbox zur Auswahl der Musikart -->
		<input type="checkbox" name="musikart" id="m1" value="Metal">
		<label for="m1">Metal </label><br> <input type="checkbox"
			name="musikart" id="m2" value="Techno"> <label for="m2">Techno
		</label><br> <input type="checkbox" name="musikart" id="m3"
			value="HipHop"> <label for="m3">HipHop </label><br>

		<button name="suchen" type="submit">Suchen</button>
	</form>
	<footer>
		<jsp:include page="fusszeile.jsp"/>
	</footer>
</body>
</html>