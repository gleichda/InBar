<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="default.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<nav>
	<%@include file="navigation.jsp" %>
	</nav>
	
	<h1>Ihr Login war erfolgreich</h1>
	<h2>Herzlich Willkommen ${baruser.barname}</h2>
	
	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>