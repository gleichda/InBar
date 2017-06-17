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
		<h2>${baruser.barname}</h2>
		<p>Hier kommt die Beschreibung für die Bar rein.</p>


		<form class="login" method="post" action="BarLoginServlet">
			<p>
				Barname: <input type="text" name="barname">
			</p>
			<p>
				Passwort: <input type="password" name="passwort">
			</p>
			<button id="barProfil_aendern" type="submit">Login</button>
		</form>

	</article>

	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>