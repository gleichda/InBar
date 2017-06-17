<!DOCTYPE html>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Suche</title>
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
	
	<h1>Das ist die Suchseite</h1>

	<form id="suchForm" method="post" action="Suchservlet">
		<!-- Textfeld zur Suche nach dem Barnamen -->
		<p>Name der Bar:</p>
		<input type="text" name="suchbegriff" placeholder="Barname" autofocus>
		<br>
		<p>Suchst du eine Bar oder ein Event?</p>
		<!-- Radio-Button zur Auswahl der Suchart. -->
		<input type="radio" name="suchart" value="bar" id="bar_radio" checked>
		<label for="bar">Bar</label><br> <input type="radio"
			name="suchart" value="event" id="event_radio">Event <br> <label
			for="event">Event</label><br>

		<p>Musikart:</p>
		<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/inbar" user="root" />
		<sql:query dataSource="${ds}" var="musikarten">SELECT * FROM musikarten;</sql:query>
		
		<p><select name="musikart">
			<c:forEach var ="art" items="${musikarten.rows}" >
				<option value="${art.musikid}">${art.name }</option>
			</c:forEach>
		</select> <p>

		<button name="suchen" type="submit">Suchen</button>
	</form>
	<footer>
		<jsp:include page="./fragments/fusszeile.jsp"/>
	</footer>
</body>
</html>