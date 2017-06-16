<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
	<%@include file="navigation.jsp" %>
	</nav>
	
	<h1>Registrierung erfolgreich</h1>
	<h2>Herzlich Willkkommen ${baruser.vorname}</h2>
	<br> Vorname: ${baruser.vorname}
	<br> Nachname ${baruser.nachname}
	<br> E-Mail: ${baruser.chefmail}
	<br> Benutzername: ${baruser.barname}
	<br> Passwort: ${baruser.passwort}
	<br> ${baruser.bbeschreibung}
	<br> ${baruser.mbeschreibung}
	<br> ${baruser.lbeschreibung}

	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>