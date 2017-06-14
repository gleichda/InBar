<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navigation.jsp"%>
	<c:if test="${not empty param.id }">
		<h1>Profil von ${user.vorname }</h1>
	</c:if>
	<c:if test="${empty param.id }">
		<h1>Dein Profil</h1>
	</c:if>
	Username: ${user.username}
	
	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>