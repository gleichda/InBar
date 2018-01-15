<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Validation OK 
@author David -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<title>Erfolgreich geändert</title>
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
		<h1>Ihre Daten wurden erfolgreich geaendert</h1>
	</article>
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>