<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="default.css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Login</title>
</head>
<body>
	<header>
		<h1 id="logo">INBars</h1>
	</header>

	<nav>
	<jsp:include page="navigation.jsp"/>
	</nav>

	<article>
		<h2>Login</h2>
		<p>Das ist die Login-Seite</p>
		<c:if test="${fehlgeschlageneLoginVersuche != null}">
			<p>Ihr Login war nicht erfolgreich bitte versuchen Sie es erneut.</p>
		</c:if>

		<form class="login" method="post" action="LoginServlet">
			<p>
				Benutzername: <input type="text" name="benutzer" autofocus>
			</p>
			<p>
				Passwort: <input type="password" name="passwort">
			</p>
			<button id="login_btn" type="submit">Login</button>
		</form>
		<p>
			<a href="registrierung.html"> Registrieren</a>
		</p>
	</article>

	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>