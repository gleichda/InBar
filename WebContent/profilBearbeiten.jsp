<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<script src="./js/passwortValidieren.js" type="text/javascript"></script>
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
	<article>
		<form action="ProfilBearbeiten" method="post">
			<p><label>Vorname:</label> <input type="text" name="vorname" value="${selfUser.vorname}" required > </p>
			<p><label>Nachname:</label> <input type="text" name="nachname" value="${selfUser.nachname}" required> </p>
			<p><label>E-Mail Adresse:</label> <input type="email" name="email"value="${selfUser.email}" required> </p>
			<p><label>Passwort:</label> <input type="password" name="passwort" id="passwort"> </p>
			<!-- TODO: Passwort zweimal abfragen und vergleichen (javascript) -->
			<p><label>Passwort wiederholen:</label> <input type="password" name="passwort_wiederholen" id=passwort_wiederholen> </p>
			<button id="aendern" type="submit">Absenden</button>
		</form>
	</article>
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>