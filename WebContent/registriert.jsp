<!DOCTYPE html>
<!--  Validation OK 
@author David-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
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
		<h1>Registrierung erfolgreich</h1>
		<h2>Herzlich Willkommen ${selfUser.vorname}</h2>
		<br> Vorname: ${selfUser.vorname}
		<br> Nachname ${selfUser.nachname}
		<br> E-Mail: ${selfUser.email}
		<br> Benutzername: ${selfUser.username}
		<br> Passwort: ${selfUser.password}
	</article>
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>