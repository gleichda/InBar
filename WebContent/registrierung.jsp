<!DOCTYPE html>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
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
			<div>
				<label for="uservorname">Vorname: </label> <input type="text"
					name="vorname" id="uservorname" value="">
			</div>
			<div>
				<label for="usernachname">Nachname: </label> <input type="text"
					name="nachname" id="usernachname" value="">
			</div>
			<div>
				<label for="usermail">E-Mail: </label> <input type="text"
					name="email" id="usermail" value="">
			</div>
			<p>
				<br>
			</p>
			<div>
				<label for="benutzer">Benutzername: </label> <input type="text"
					name="benutzer" id="benutzer" value="">
			</div>
			<div>
				<label for="userpasswort">Passwort: </label> <input type="password"
					name="passwort" id="userpasswort">
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