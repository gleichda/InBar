<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ProfilBearbeiten" method="post">
		<p>Vorname: <input type="text" name="vorname" value=${selfUser.vorname} > </p>
		<p>Nachname: <input type="text" name="nachname" value=${selfUser.nachname} > </p>
		<p>E-Mail Adresse: <input type="text" name="email"value=${selfUser.email}> </p>
		<p>Passwort: <input type="password" name="passwort"> </p>
		<button id="aendern" type="submit">Absenden</button>
	</form>
</body>
</html>