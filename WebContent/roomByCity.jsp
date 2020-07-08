<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Filter Room City</title>
</head>
<body>
	<form action="ActionController" method="post">
			Masukan kota : <input type="text" name="city"><BR>
			<button type="submit"  name="action" value="roomByCity">Cari</button>
		</form>
</body>
</html>