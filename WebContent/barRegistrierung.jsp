<!DOCTYPE html>
<!--  Validation OK 
@author Sabine-->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/fileSize.js"></script>
<meta charset="UTF-8">
<title>Registrierung für Bars</title>
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
		<h2>Registrierung für Bars</h2>
		<p>
			Das ist die Registrierungs-Seite, auf der Sie ihre Bar anmelden
			können.
		</p>

		<form id="barRegistrierung" action="BarRegisterServlet" method="post" enctype="multipart/form-data">
			<p>
				<label for="barname">Name der Bar: </label> 
				<input type="text" name="barname" id="barname" required> 
				<span></span>
			</p>
			<br>
			<br>
			<fieldset><legend>Informationen zum Geschäftsführer</legend>
			<p>
				<label for="vorname"> Geschäftsführer: </label> 
				<input type="text" name="vorname" id="vorname" placeholder="Vorname"  required>  
				<span></span>
				<input type="text" name="nachname" id="nachname" placeholder="Nachname"  required> 
				<span></span>
			</p>
			<p>
				<label for="chefmail"> E-Mail-Adresse des Geschäftsführers:</label> 
				<input type="email" name="chefmail" id="chefmail" required> 
				<span></span>
			</p>
			</fieldset>
			<br>
			<fieldset><legend>Informationen über die Bar</legend>
			<p>
				<label for="strasse">Strasse: </label> 
				<input type="text" name="strasse" id="strasse" required> 
				<span></span>
			</p>
			<p>
				<label for="hausnummer">Hausnummer: </label> 
				<input type="text" name="hausnummer" id="hausnummer" required> 
				<span></span>
			</p>
			<p>
				<label for="plz">PLZ: </label> 
				<input type="text" name="plz" id="plz" required> 
				<span></span>
			</p>
			<p>
				<label for="ort">Ort: </label> 
				<input type="text" name="ort" id="ort" required> 
				<span></span>
			</p>
			<p>
				<label for="barmail">Mail-Adresse der Bar: </label> 
				<input type="email" name="barmail" id="barmail" required> 
				<span></span>
			</p>
			<p> Musikart:
				<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/inbar" user="root" password="master42" />
				<sql:query dataSource="${ds}" var="musikarten">SELECT * FROM musikarten;</sql:query>
				
				<select name="musikart">
					<option value="-1">Alle</option> 
					<c:forEach var ="art" items="${musikarten.rows}" >
						<option value="${art.musikid}">${art.name }</option>
					</c:forEach>
				</select>
				</p>
			</fieldset>
			<fieldset><legend>Profiltext</legend>
			<div id="beschreibung">
				<p>Die folgenden Texte werden zu dem Profil-Text der Bar:</p>
				<p>
					<label for="bbeschreibung">Beschreiben Sie ihre Bar. Hinweis: Sie können auch Standard HTML Tags verwenden um Ihren Text zu formatieren (z.B. &lt;br&gt; für neue Zeile):</label>
					<textarea name ="text" id="bbeschreibung" placeholder="Beschreiben Sie ihre Bar." rows="5" cols="100" required></textarea> 
				<span></span>
				</p>
				<br>
				<p>
					<label for="mbeschreibung">Beschreiben Sie die Musik, die bei Ihnen gespielt wird Hinweis: Sie können auch Standard HTML Tags verwenden um Ihren Text zu formatieren (z.B. &lt;br&gt; für neue Zeile): </label>
					<textarea name="mbeschreibung" id="mbeschreibung" placeholder="Beschreiben Sie die Musik die bei Ihnen gespielt wird." rows="5" cols="100" required></textarea> 
					<span></span>
				</p>
				<p>
					<label for="lbeschreibung">Beschreiben Sie, wie ihre Bar erreicht werden kann Hinweis: Sie können auch Standard HTML Tags verwenden um Ihren Text zu formatieren (z.B. &lt;br&gt; für neue Zeile): </label>
					<textarea name="lbeschreibung" id="lbeschreibung" placeholder="Beschreiben Sie die Lage Ihrer Bar und wie man sie erreichen kann." rows="5" cols="100" required></textarea> 
					<span></span>
				</p>
			</div>
			</fieldset>
			<p>
				<label for="bild">Laden sie das Profilbild ihrer Bar hoch (max 5MB):</label>
				<br>
				<input type="file" name="bild" id="bild" accept="image/*"> 
				<span></span>
			</p>
			<p>
				<button id="reg_daten" type="submit">Registrieren</button>
			</p>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>