<!DOCTYPE html>
<!-- Validation OK 
@author David-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/passwortValidieren.js"></script>
<meta charset="UTF-8">
<title>IN-Bars Registrierung</title>
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
		<h2>Registrierung</h2>
		<p>Das ist die Registrierungs-Seite</p>
		<form id="userRegistrierung" action="RegisterServlet" method="post" enctype="multipart/form-data" >
			<label for="uservorname">Vorname: </label> 
				<input type="text" name="vorname" id="uservorname" required>  
				<span></span>
			<label for="usernachname">Nachname:</label> 
				<input type="text" name="nachname" id="usernachname" required>  
				<span></span>
			<label	for="usermail">E-Mail: </label> 
				<input type="email" name="email" id="usermail" required>   
				<span></span>
			<label for="benutzer">Benutzername: </label>
				<input type="text" name="benutzer" id="benutzer" required>  
				<span></span>
			<label for="passwort">Passwort: </label> 
				<input type="password" name="passwort" id="passwort" required> 
				<span></span> 
			<label for="passwort_wiederholen">Passwort wiederholen: </label> 
				<input type="password" name="passwort" id="passwort_wiederholen" required> 
				<span></span>
			<br>
			<p>
				<label for="bild">Laden sie das Profilbild ihrer Bar hoch (max 5MB):</label>
				<br>
				<input type="file" name="bild" id="bild" accept="image/*" required> 
				<span></span>
			</p>
			<button id="reg_daten" type="submit">Registrieren</button>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>