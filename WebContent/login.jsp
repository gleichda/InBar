<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="./css/navStyle.css" type = "text/css" rel="stylesheet">
<link href="./css/style.css" type="text/css" rel="stylesheet">
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
				<p>Ihr Login war nicht erfolgreich bitte versuchen Sie es
					erneut.</p>
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
				Sie sind noch nicht Registriert? Jetzt <a href="registrierung.jsp">
					Registrieren</a>
			</p>
		</article>
	</div>
	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>
</html>