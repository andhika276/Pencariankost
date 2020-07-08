<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Filter Minimal Jumlah Kamar Owner</title>
</head>
<body>
	<form action="ActionController" method="post">
			Minimal Jumlah Kamar : <input type="number" name="jumlahKamar"><BR>
			<input type="hidden" name="action" value="filter_jumlah_kamar">
			<input type="submit" />
		</form>
</body>
</html>