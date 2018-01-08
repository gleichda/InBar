<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--  Validation OK -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/starRating.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<script src="js/ajax_bewertung.js"></script>
<!-- Rating mit Sternen übernommen von https://www.cssscript.com/simple-5-star-rating-system-with-css-and-html-radios/ -->
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
		<h1>${bar.barname}</h1>
		<form action="BarEvents">
			<input type="hidden" name="id" value="${bar.barid}">
			<input type="hidden" name="eventListe" value="1">
    		<button type="submit">Events</button>
		</form>
		<section >
			<h2>Herzlich Willkommen ${bar.barname}</h2> 
			<p>${bar.bbeschreibung}</p>
			<p>${bar.mbeschreibung}</p>
			<p>${bar.lbeschreibung}</p>
			<br> <img src="./Bild?id=${bar.bildId}" class="medium" alt="Bild der Bar">
		</section>
		
		<h2>Bewertungen</h2>
		<c:choose>
		<c:when test="${not empty selfUser.vorname}">
			<div>
				<h3>Ihre Bewertung</h3>
				<br>
				<form id="bewertungsForm">
				<input type="hidden" id="barid" name="barid" value="${bar.barid }">
					<div class="stars">
					    <input class="star star-5" id="star-5" type="radio" name="star" value="5"/>
					    <label class="star star-5" for="star-5"></label>
					    <input class="star star-4" id="star-4" type="radio" name="star" value="4"/>
					    <label class="star star-4" for="star-4"></label>
					    <input class="star star-3" id="star-3" type="radio" name="star" value="3"/>
					    <label class="star star-3" for="star-3"></label>
					    <input class="star star-2" id="star-2" type="radio" name="star" value="2"/>
					    <label class="star star-2" for="star-2"></label>
					    <input class="star star-1" id="star-1" type="radio" name="star" value="1"/>
					    <label class="star star-1" for="star-1"></label>
					</div>
				    <br>
				    <textarea rows="5" cols="100" name="bewertungstext" id="bewertungstext" placeholder="Ihr Kommentar"></textarea>
				    <br> <br>
				    <button type="button" id="bewertungAbgeben">Absenden</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<p>Bitte <a href="./login.jsp">loggen</a> sie sich ein um die Bar zu bewerten</p>
			</div>
		</c:otherwise>
		</c:choose>
		<div>
			<c:choose>
				<c:when test="${empty bewertungen}">
					<p>Es ist noch keine Bewertung vorhanden <br>
					Schreiben Sie doch die erste
					</p>
				</c:when>
				<c:otherwise>
					<table id="bewertungen">
						<tr>
							<th>Bewertung</th>
							<th>Kommentar</th>
							<th>Benutzername</th>
						</tr>
						<c:forEach var="bewertung" items="${bewertungen}">
							<tr>
								<td class="sterne">${bewertung.bewertung } </td>
								<td>${bewertung.kommentar }</td>
								<td>${bewertung.user.username }</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</article>
	
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>