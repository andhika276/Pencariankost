<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Edit Data Pemilik</title>
</head>
<body>
	<form action="ActionController" method="post">
			Update Nama : <input type="text" name="name" value="${info.name}" ><BR>
			Update Alamat : <input type="text" name="address" value="${info.address}" ><BR>
			Update Nomor Telepon : <input type="number" name="contact" value="${info.contact}" ><BR>
			<input type="hidden" name="id" value="${info.ownerId}">
			<input type="hidden" name="action" value="edit_owner">
			<input type="submit" />
		</form>
</body>
</html>