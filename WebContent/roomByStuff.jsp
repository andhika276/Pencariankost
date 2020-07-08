<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Filter Room Stuff</title>
</head>
<body>
	<form action="ActionController" method="post">
			Daftar Barang (pisahkan dengan ',') : <BR><input type="text" name="stuff"><BR>
			<button type="submit"  name="action" value="roomByStuff">Cari</button>
		</form>
</body>
</html>