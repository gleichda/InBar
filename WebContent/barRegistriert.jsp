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
		<c:choose>
		<c:when test="${not empty selfUser.vorname}"> 
			<jsp:include page="./fragments/navigationEingeloggt.jsp"/>
		</c:when>
		<c:otherwise>
					<jsp:include page ="./fragments/navigation.jsp"/>
		</c:otherwise>
		</c:choose>		
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
	<br> ${baruser.bild}

	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>