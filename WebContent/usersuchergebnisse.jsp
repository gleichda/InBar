<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<title>Ergebnisse der Suche</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp"%>
	</header>
	<nav>
		<c:choose>
			<c:when test="${not empty selfUser.vorname}">
				<jsp:include page="./fragments/navigationEingeloggt.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="./fragments/navigation.jsp" />
			</c:otherwise>
		</c:choose>
	</nav>
<!--  04.01 Sabine Kopier von suchergebnisse.jsp und auf User-Suche angepasst	 -->
	<article>
		<h1>User-Suchergebnisse</h1>
		<div>
			<h3>Ihre Sucheingaben:</h3>
			<br> <b>Gesuchter Begriff </b>${param.suchbegriff} <br> <b>Du
				suchst nach:</b>${param.suchart} 
				<br> Vorname: ${param.vorname}
				<br> Nachname: ${param.nachname}
				<br> E-Mail: ${param.email}
		</div>
		<table id="suchergebnisse">
			<tr>
				<th>UserID</th>
				<th>User-Name</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>E-Mail</th>

			</tr>
			<c:forEach var="user" items="${suchergebnisse}">
				<tr>
					<td>${user.userid}</td>
					<td><a href="./ProfilAnzeigen?id=${user.userid}">${user.username}</a></td>
					<td>${user.vorname}</td>
					<td>${user.nachname}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>
		</table>
	</article>
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>

</html>