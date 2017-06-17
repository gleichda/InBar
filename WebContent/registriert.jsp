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
	<h2>Herzlich Willkkommen ${selfUser.vorname}</h2>
	<br> Vorname: ${selfUser.vorname}
	<br> Nachname ${selfUser.nachname}
	<br> E-Mail: ${selfUser.email}
	<br> Benutzername: ${selfUser.selfUsername}
	<br> Passwort: ${selfUser.password}
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>