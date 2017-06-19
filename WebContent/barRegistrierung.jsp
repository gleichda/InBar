<!DOCTYPE html>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Registrierung fÃ¼r Bars</title>
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
		<h2>Registrierung fÃ¼r Bars</h2>
		<p>
			Das ist die Registrierungs-Seite, auf der Sie ihre Bar anmelden
			kÃ¶nnen.
		</p>

		<form id="barRegistrierung" action="BarRegisterServlet" method="post"
			enctype="multipart/form-data">

			<div>
				<label for="barname">Name der Bar: </label> <input type="text"
					name="barname" id="barname" value=""> <br> Der Name
				der Bar wird automatisch zum Login-Namen
			</div>
			<br>
			<fieldset><legend>Informationen zum Geschäftsführer</legend>
			<div>
				<label for="vorname"> Geschäftsführer: </label> <input type="text"
					name="vorname" id="vorname" value="vorname"> <input
					type="text" name="nachname" id="nachname" value="nachname">
			</div>
			<div>
				<label for="chefmail"> E-Mail-Adresse des Geschäftsführers:
				</label> <input type="text" name="chefmail" id="chefmail" value="">
			</div>
			</fieldset>
			<br>
			<fieldset><legend>Informationen über die Bar</legend>
			<div>
				<label for="strasse">Strasse: </label> <input type="text"
					name="strasse" id="strasse" value="">
			</div>
			<div>
				<label for="hausnummer">Hausnummer: </label> <input type="text"
					name="hausnummer" id="hausnummer" value="">
			</div>
			<div>
				<label for="plz">PLZ: </label> <input type="text" name="plz"
					id="plz" value="">
			</div>
			<div>
				<label for="ort">Ort: </label> <input type="text" name="ort"
					id="ort" value="">
			</div>
			<div>
				<label for="barmail">Mail-Adresse der Bar: </label> <input
					type="text" name="barmail" id="barmail" value="">
			</div>
			<!-- TODO: Musikrichtung waehlen -->
			</fieldset>
			<fieldset><legend>Profiltext</legend>
			<div id="beschreibung">
				<p>Die folgenden Texte werden zu dem Profil-Text der Bar:</p>
				<div>
					<label for="bbeschreibung">Beschreiben Sie ihre Bar.<br></label>
					<!--   <input type="text" name="text" id="bbeschreibung"
						value="Barbeschreibung"> <br>  -->
					<textarea name ="text" id="bbeschreibung" placeholder="Beschreiben Sie ihre Bar." rows="5" cols="100"></textarea>
					<!--TODO: Name anpassen an bbeschreibung? -->
				</div>
				<br>
				<div>
					<label for="mbeschreibung">Beschreiben Sie die Musik, die
						bei Ihnen gespielt wird: <br></label> 
						<!-- <input type="text" name="mbeschreibung" id="mbeschreibung"  
						value="Musikbeschreibung">  -->
						<textarea name="mbeschreibung" id="mbeschreibung" placeholder="Beschreiben Sie die Musik die bei Ihnen gespielt wird." rows="5" cols="100"></textarea>
				</div>
				<div>
					<label for="lbeschreibung">Beschreiben Sie, wie ihre Barerreicht werden kann: </label> <br>
					<!--   <input type="text" name="lbeschreibung" id="lbeschreibung"value="Lagebeschreibung"> -->
					<textarea name="lbeschreibung" id="lbeschreibung" placeholder="Beschreiben Sie die Lage Ihrer Bar und wie man sie erreichen kann." rows="5" cols="100"></textarea>
				</div>
			</div>
			</fieldset>
			<div>
				<label for="bild">Laden sie das Profilbild ihrer Bar hoch: <br></label>
				<input type="file" name="bild" id="bild" accept="image/*" required>
			</div>
			<div>
				<button id="reg_daten" type="submit">Registrieren</button>
				<!-- formaction muss noch angepasst werden -->
			</div>
		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>