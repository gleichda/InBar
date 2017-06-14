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
	<h2>Herzlich Willkkommen ${selfUser.vorname}</h2>
	<br> Vorname: ${selfUser.vorname}
	<br> Nachname ${selfUser.nachname}
	<br> E-Mail: ${selfUser.email}
	<br> Benutzername: ${selfUser.selfUsername}
	<br> Passwort: ${selfUser.password}
	
	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>