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
		<input type="hidden" name="id" value="${ownerId}">
		Masukan Nama : <input type="text" name="name"> <BR>
		Masukan Alamat : <input type="text" name="address"> <BR>
		Masukan Nomor Telepon : <input type="text" name="contact"> <BR>
		<input type="hidden" name="action" value="insert_owner">
		<input type="submit" />
	</form>

</body>
</html>