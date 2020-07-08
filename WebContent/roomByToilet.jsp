<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Filter Room Toilet</title>
</head>
<body>
	<form action="ActionController" method="post">
			Jenis kamar mandi : <BR>
			<input type="radio" name="toilet" value="dalam">Kamar Mandi Dalam<BR>
			<input type="radio" name="toilet" value="luar">Kamar Mandi Luar<BR>
			<button type="submit"  name="action" value="roomByToilet">Cari</button>
		</form>
</body>
</html>