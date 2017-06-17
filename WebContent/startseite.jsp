<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="default.css" rel="stylesheet">
<meta charset="UTF-8">
<title>IN-Bars Login</title>
</head>
<body>
	<header>
		<h1 id="logo">INBars</h1>
	</header>

	<nav>
	<jsp:include page="navigation.jsp"/>
	</nav>

	<article>
		<h2>Willkommen bei IN*Bars</h2>
		<p>Wir bieten eine Plattform über die Sie Bars, Kneipen und Lokale in Ihrer Nähe finden und die Ihren Interessen entsprechen.</p>
		

	</article>

	<footer>
		<%@include file="fusszeile.jsp" %>
	</footer>
</body>
</html>