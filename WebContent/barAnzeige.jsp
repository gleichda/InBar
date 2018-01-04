<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/starRating.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
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
		<h2>Herzlich Willkommen ${bar.barname}</h2> 
		<section >
			<p>${bar.bbeschreibung}</p>
			<p>${bar.mbeschreibung}</p>
			<p>${bar.lbeschreibung}</p>
			<br> <img src="./Bild?id=${bar.bildId}" class="medium">
		</section>
		
		<c:choose>
		<c:when test="${not empty selfUser.vorname}">
			<div>
				<h2>Bewertung abgeben</h2>
				<br>
				<form method="post" >
				<div class="stars">
				    <input class="star star-5" id="star-5" type="radio" name="5"/>
				    <label class="star star-5" for="star-5"></label>
				    <input class="star star-4" id="star-4" type="radio" name="4"/>
				    <label class="star star-4" for="star-4"></label>
				    <input class="star star-3" id="star-3" type="radio" name="3"/>
				    <label class="star star-3" for="star-3"></label>
				    <input class="star star-2" id="star-2" type="radio" name="2"/>
				    <label class="star star-2" for="star-2"></label>
				    <input class="star star-1" id="star-1" type="radio" name="1"/>
				    <label class="star star-1" for="star-1"></label>
			    </div>
				    <br>
				    <textarea rows="5" cols="100" name="bewertungstext" id="bewertungstext" placeholder="Ihr Kommentar"></textarea>
				    <br> <br>
				    <button type=submit id="bewertungAbgeben">Absenden</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<p>Bitte <a href="login.jsp">loggen</a> sie sich ein um die Bar zu bewerten</p>
			</div>
		</c:otherwise>
		</c:choose>
		
	</article>
	
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>
</body>
</html>