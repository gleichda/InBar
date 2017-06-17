<!DOCTYPE html>
<html>
<head>
<link href="default.css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Registrierung</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp" %>
	</header>
	<nav>
		<jsp:include page="./fragments/navigation.jsp"/>
	</nav>

	<article>
		<h2>Registrierung</h2>
		<p>Das ist die Registrierungs-Seite</p>
		<form class=" registrierung" action="RegisterServlet" method="post">
			<p>
				Vorname: <input type="text" name="vorname" value="">
			</p>
			<p>
				Nachname: <input type="text" name="nachname" value="">
			</p>
			<p>
				E-Mail: <input type="text" name="email" value="">
			</p>
			<p>
				<br>
			</p>
			<p>
				Benutzername: <input type="text" name="benutzer" value="">
			</p>
			<p>
				Passwort: <input type="password" name="passwort">
			</p>

			<button id="reg_daten" type="submit">
				<!-- formaction muss noch angepasst werden -->
				Registrieren
			</button>

		</form>
	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>