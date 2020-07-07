<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos- Input Data Pemilik</title> 
</head>
<body>
	<form action="ActionController" method="post">
		<input type="hidden" name="idRoom" value="${roomId}">
		Nama Barang : <input type="text" name="namaBarang"> <BR>
		Jumlah Barang : <input type="text" name="jumlah"> <BR>
		<input type="hidden" name="action" value="insert_barang">
		<input type="submit" />
	</form>
</body>
</html>