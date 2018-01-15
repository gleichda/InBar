<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- validation OK 
@author Sabine -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<title>Profil Anzeigen</title>
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
		<c:if test="${not empty param.id }">
			<h2>Profil von ${user.vorname }</h2>
		</c:if>
		<c:if test="${empty param.id }">
			<h2>Dein Profil</h2>
		</c:if>
		Username: ${user.username} <br>
		Name: ${user.vorname } ${user.nachname } <br>
		Email: ${user.email } <br>
		
		
		<br> <img src="./Bild?id=${user.bildId}" alt="Profilbild" class="small">
		
		
	</article>
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>