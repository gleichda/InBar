<!DOCTYPE html>
<!--  Validation OK 
@author david -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
		<h2>Bearbeitung für Bars</h2>
		<p>
			Hier können Sie das Profil Ihrer Bar bearbeiten.
		</p>
		

		<form id="barBearbeiten" action="BarBearbeiten" method="post">
			<p>
				<label for="barname">Name der Bar: </label> 
				<input type="text" name="barname" id="barname" value="${bar.barname }"> 
				<input type="text" name="barid" id="barid" value="${bar.barid }">
			</p>
			<br>
			<br>
			<fieldset><legend>Informationen zum Geschäftsführer</legend>
			<p>
				<label for="vorname"> Geschäftsführer: </label> 
				<input type="text" name="vorname" id="vorname" value="${bar.vorname}" required> 
				<input type="text" name="nachname" id="nachname" value="${bar.nachname}" required>
			</p>
			<p>
				<label for="chefmail"> E-Mail-Adresse des Geschäftsführers:</label> 
				<input type="email" name="chefmail" id="chefmail" value="${bar.chefmail}" required>
			</p>
			</fieldset>
			<br>
			<fieldset><legend>Informationen über die Bar</legend>
			<p>
				<label for="strasse">Strasse: </label> 
				<input type="text" name="strasse" id="strasse" value="${bar.strasse}" required>
			</p>
			<p>
				<label for="hausnummer">Hausnummer: </label> 
				<input type="text" name="hausnummer" id="hausnummer" value="${bar.hausnummer}" required>
			</p>
			<p>
				<label for="plz">PLZ: </label> 
				<input type="text" name="plz" id="plz" value="${bar.plz}" required>
			</p>
			<p>
				<label for="ort">Ort: </label> 
				<input type="text" name="ort" id="ort" value="${bar.ort}" required>
			</p>
			<p>
				<label for="barmail">Mail-Adresse der Bar: </label> 
				<input type="email" name="barmail" id="barmail" value="${bar.barmail}" required>
			</p>
			</fieldset>
			<fieldset><legend>Profiltext</legend>
			<div id="beschreibung">
				<p>Die folgenden Texte werden zu dem Profil-Text der Bar:</p>
				<p>
					<label for="bbeschreibung">Beschreiben Sie ihre Bar.</label>
					<textarea name="bbeschreibung" id="bbeschreibung" rows="5" cols="100" required>${bar.bbeschreibung}</textarea>
					
				</p>
				<br>
				<p>
					<label for="mbeschreibung">Beschreiben Sie die Musik, die bei Ihnen gespielt wird: </label>
					<textarea name="mbeschreibung" id="mbeschreibung" rows="5" cols="100" required>${bar.mbeschreibung}</textarea>
				</p>
				<p>
					<label for="lbeschreibung">Beschreiben Sie, wie ihre Bar erreicht werden kann: </label>
					<textarea name="lbeschreibung" id="lbeschreibung" rows="5" cols="100" required>${bar.lbeschreibung}</textarea>
				</p>
			</div>
			</fieldset>
			<!-- TODO: Bild Ändern -->
			<p>
				<button id="bar_aendern" type="submit">Speichern</button>
			</p>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>