<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp" %>
	</header>
	<nav>
		<jsp:include page="./fragments/navigation.jsp"/>
	</nav>
	
	<form action="ProfilBearbeiten" method="post">
		<p>Vorname: <input type="text" name="vorname" value="${selfUser.vorname}" > </p>
		<p>Nachname: <input type="text" name="nachname" value="${selfUser.nachname}" > </p>
		<p>E-Mail Adresse: <input type="text" name="email"value="${selfUser.email}"> </p>
		<p>Passwort: <input type="password" name="passwort"> </p>
		<button id="aendern" type="submit">Absenden</button>
	</form>
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>