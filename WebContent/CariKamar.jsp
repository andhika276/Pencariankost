<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos- Cari Kamar</title> 
</head>
<body>
	<form action="ActionController" method="post">
		<p>Cari berdasarkan: <p/>
		<button type="submit"  name="action" value="room_by_city">Kota</button><BR>
		<button type="submit"  name="action" value="room_by_barang">Barang yang tersedia</button><BR>
		<button type="submit"  name="action" value="room_by_cost">Harga</button><BR>
		<button type="submit"  name="action" value="room_by_toilet">Jenis kamar mandi</button><BR>
	</form>
</body>
</html>