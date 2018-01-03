<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/passwortValidieren.js" type="text/javascript"></script>
<meta charset="UTF-8">
<title>IN-Bars Registrierung</title>
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
		<h2>Registrierung</h2>
		<p>Das ist die Registrierungs-Seite</p>
		<form id="userRegistrierung" action="RegisterServlet" method="post">
			<label for="uservorname">Vorname: </label> 
				<input type="text" name="vorname" id="uservorname" required> 
			<label for="usernachname">Nachname:</label> 
				<input type="text" name="nachname" id="usernachname" required> 
			<label	for="usermail">E-Mail: </label> 
				<input type="email" name="email"id="usermail" required>  
			<label for="benutzer">Benutzername: </label>
				<input type="text" name="benutzer" id="benutzer" required> 
			<label for="passwort">Passwort: </label> 
				<input type="password" name="passwort" id="passwort" required> 
			<label for="passwort_wiederholen">Passwort wiederholen: </label> 
				<input type="password" name="passwort" id="passwort_wiederholen" required>
			<br>
			<p>
				<label for="bild">Laden sie das Profilbild ihrer Bar hoch:</label>
				<br>
				<input type="file" name="bild" id="bild" accept="image/*" required>
			</p>
			<button id="reg_daten" type="submit">Registrieren</button>
			<!-- formaction muss noch angepasst werden -->
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>