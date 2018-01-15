<!-- @author Sabine -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="./css/navStyle.css" type="text/css" rel="stylesheet">

<!-- Navigationsleiste, wenn der User NICHT eingeloggt ist -->
<div id="main_nav">
	<ul>
		<li class="nav1"><a href="index.jsp"> Startseite</a></li>
		<li class="submenu"><a class="anav" href="barSuche.jsp"> Suche</a>
			<ul>
				<li class="nav2"><a class="anav" href="barSuche.jsp"> Bars suchen</a></li>
				<li class="nav2"><a class="anav" href="eventSuche.jsp"> Events suchen</a></li>
				<li class="nav2"><a class="anav" href="userSuche.jsp"> User suchen</a></li>
			</ul></li>
		<li class="submenu"><a>Profil</a>
			<ul>
				<li class="nav2"><a class="anav" href="ProfilAnzeigen"> MeinProfil</a></li>
				<li class="nav2"><a class="anav" href="profilBearbeiten.jsp">Profil bearbeiten</a></li>
				<li class="nav2"><a class="anav" href="MeineBars">Meine Bars</a></li>
				<li class="nav2"><a class="anav" href="./Logout">Logout</a></li>
			</ul>
		</li>
		<li class="nav1"><a class="anav" href="barRegistrierung.jsp">Bar anlegen</a></li>
	</ul>
</div>