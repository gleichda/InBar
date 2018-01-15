<!DOCTYPE html>
<!-- Validation OK 
@author David -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" type="text/css" rel="stylesheet">
<link href="./css/formStyle.css" type="text/css" rel="stylesheet">
<title>Suche nach Bars</title>
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
		<h1>Bar Suche</h1>
	
		<form id="suchForm" method="post" action="BarSuche">
			<div>
			<!-- Textfeld zur Suche nach dem Barnamen -->
			<label for="suchbegriff">Name der Bar: </label>
			<input type="text" name="suchbegriff" id="suchbegriff" placeholder="Barname" autofocus>
			</div>
			<br>
			<div>
				<p> Musikart:
				<!-- TODO: DB in JSP Seite?? -->
				<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/inbar" user="root" password="master42"/>
				<sql:query dataSource="${ds}" var="musikarten">SELECT * FROM musikarten;</sql:query>
				
				<select name="musikart">
					<option value="-1">Alle</option> 
					<c:forEach var ="art" items="${musikarten.rows}" >
						<option value="${art.musikid}">${art.name }</option>
					</c:forEach>
				</select>
				</p>
			</div>
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