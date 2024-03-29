<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Validation 
@author Sabine -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<title>Event Suchergebnisse</title>
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
	<article>
		<h1>Events der Bar</h1>
		<table id="barEvents">
			<tr>
				<th>Bar</th>
				<th>Name</th>
				<th>Startdatum</th>
				<th>Startzeit</th>
				<th>Enddatum</th>
				<th>Endzeit</th>
				<th>Eventbeschreibung</th>
				<th></th>
			</tr>


					<c:forEach var="event" items = "${suchergebnisse}">	
						<tr>
							<td><a href="./BarAnzeigen?id=${event.barid}"> ${event.barname} </a></td>		
							<td>${event.eventname}</td>
							<td>${event.startdatum} </td>
							<td>${event.startzeit}</td>
							<td>${event.enddatum}</td> 
							<td>${event.endzeit}</td>
							<td>${event.ebeschreibung}</td>
					
						</tr>					
					</c:forEach>			
		</table>
	</article>
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>

</html>