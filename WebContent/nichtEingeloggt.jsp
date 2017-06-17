<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@include file="./fragments/header.jsp" %>
	</header>
	<nav>
		<jsp:include page="./fragments/navigation.jsp"/>
	</nav>
	
	<h1>Sie sind nicht eingeloggt</h1>
	<p> Bitte loggen Sie sich ein, um mit der Aktion fortzufahren.</p>
	
	<footer>
		<%@include file="./fragments/fusszeile.jsp" %>
	</footer>	
</body>
</html>