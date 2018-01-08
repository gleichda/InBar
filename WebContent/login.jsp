<!DOCTYPE html>
<!-- Validation OK -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Login</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp"%>
	</header>

	<nav class="navbereich">
		<c:choose>
			<c:when test="${not empty selfUser.vorname}">
				<jsp:include page="./fragments/navigationEingeloggt.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="./fragments/navigation.jsp" />
			</c:otherwise>
		</c:choose>
	</nav>
	<div class="main">
		<article>
			<h2>Login</h2>
			<p>Das ist die Login-Seite</p>
			<c:if test="${fehlgeschlageneLoginVersuche != null}">
				<div>
					<p>Ihr Login war nicht erfolgreich bitte versuchen Sie es erneut.</p>
				</div>
			</c:if>

			<form id="login" method="post" action="LoginServlet">
				<p>
					<label for="name"> Benutzer: </label> 
					<input type="text" name="benutzer" id="name" autofocus required>
				</p>
				<p>
					<label for="passwort"> Passwort: </label> 
					<input type="password" name="passwort" id="passwort" required>
				</p>
				<p>
					<button id="login_btn" type="submit">Login</button>
				</p>
			</form>
			<p>
				Sie sind noch nicht Registriert? Jetzt <a href="registrierung.jsp"
					id="regLink"> Registrieren</a>
			</p>
		</article>
	</div>
	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>