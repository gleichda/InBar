<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
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
	<article>
		<h1>Suchergebnisse</h1>
		<div>
			<h3>Ihre Sucheingaben:</h3>
			<br> <b>Gesuchter Begriff </b>${param.suchbegriff} <br> <b>Du
				suchst nach:</b>${param.suchart} <br>Musikart: ${param.musikart}
		</div>
		<table id="suchergebnisse">
			<tr>
				<th>BarID</th>
				<th>Name</th>
				<th>Adresse</th>
				<th>Beschreibung</th>
				<th>Lage</th>
				<th>Musik</th>
			</tr>
			<c:forEach var="bar" items="${suchergebnisse}">
				<tr>
					<td>${bar.barid}</td>
					<td>"${bar.barname}"</td>
					<td>"${bar.strasse}" "${bar.hausnummer}" <br>
						"${bar.plz}" "${bar.ort}"
					</td>
					<td>"${bar.bbeschreibung}"</td>
					<td>"${bar.lbeschreibung}"</td>
					<td>"${bar.mbeschreibung}"</td>
				</tr>
			</c:forEach>
		</table>
	</article>
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp"%>
	</footer>
</body>

</html>