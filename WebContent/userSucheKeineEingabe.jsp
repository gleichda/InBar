<!DOCTYPE html>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<title>Suche</title>
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
	
<!-- 04.01 Sabine kopiert von suche.jsp und auf UserSuche angepasst -->

	<article>
		<h1>Das ist die Suchseite für User</h1>
		<div>Bitte füllen Sie mindestens ein Feld aus.</div>
	
		<form id="suchForm" method="post" action="UserSuchServlet">
			<div>
			<!-- Textfeld zur Suche nach dem Usernamen -->
			<label for="username">Username: </label>
			<input type="text" name="username" id="username" placeholder="Username" autofocus>
			</div>
			<br>
			<div>
			<!-- Textfeld zur Suche nach dem Vornamen -->
			<label for="vorname">Vorname: </label>
			<input type="text" name="vorname" id="vorname" placeholder="Vorname">
			</div>
			<br>
			<div>
			<!-- Textfeld zur Suche nach dem Nachnamen -->
			<label for="nachname">Nachname: </label>
			<input type="text" name="nachname" id="nachname" placeholder="Nachname">
			</div>
			<br>

			<div>
				<button name="suchen" type="submit">Suchen</button>
			</div>
		</form>
	</article>
	<footer>
		<jsp:include page="./fragments/fusszeile.jsp"/>
	</footer>
</body>
</html>