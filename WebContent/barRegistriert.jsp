<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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