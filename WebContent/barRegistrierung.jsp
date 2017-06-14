<!DOCTYPE html>
<html>
<head>
<link href="default.css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Registrierung für Bars</title>
</head>
<body>
	<header>
		<h1 id="logo">INBars</h1>
	</header>

	<nav>
		<%@include file="navigation.jsp" %>
	</nav>

	<article>
		<h2>Registrierung für Bars</h2>
		<p>Das ist die Registrierungs-Seite, auf der Sie ihre Bar anmelden können.<br> Bitte beachten Sie das dieser Dienst kostenpflichtig ist.</p>
		
		<form class=" registrierung" action="BarRegisterServlet" method="post">
			<p>
				Name der Bar: <input type="text" name="barname" value="">
				<br> Der Name der Bar wird automatisch zum Login-Namen
			</p>
			<p>
				Geschäftsführer: <input type="text" name="vorname" value="vorname"> <input type="text" name="nachname" value="nachname">
			</p>
			<p>
				Email des Geschäftsführers: <input type="text" name="chefmail" value="">
			</p>
				<br>
			<p>
				Straße: <input type="text" name="strasse" value="">
			</p>
			<p>
				Hausnummer: <input type="text" name="hausnummer" value="">
			</p>
			<p>
				PLZ: <input type="text" name="plz" value="">
			</p>
			<p>
				Ort: <input type="text" name="ort" value="">
			</p>
			<p>
				E-Mail: <input type="text" name="barmail" value="">
			</p>
			<p>
				<br>
			</p>
			<p>
				Geben Sie hier die Beschreibung für Ihre Bar ein. Der Text wird auf dem Profil ihrer Bar erscheinen: <br>
				<input type="text" name="text" value="Barbeschreibung"> <br>
				<br>
				Beschreiben Sie die Musik, welche bei Ihnen gespielt wird: <br>
				<input type="text" name="mbeschreibung" value="Musikbeschreibung"> <br>
				Beschreiben Sie, wie Ihre Bar erreicht werden kann: <br>
				<input type="text" name="lbeschreibung" value="Lagebeschreibung"> <br>
				
			</p>	

			<button id="reg_daten" type="submit">
				<!-- formaction muss noch angepasst werden -->
				Registrieren
			</button>

		</form>
	</article>

	<footer>
		<table class="foot">
			<tr>
				<td>Datenschutz</td>
				<td>Kontakt</td>
				<td>Impressum</td>
			</tr>
		</table>

	</footer>
</body>
</html>