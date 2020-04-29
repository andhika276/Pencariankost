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
			Update Alamat : <input type="text" name="city" value="${info.address}" ><BR>
			Update Nomor Telepon : <input type="text" name="designation" value="${info.contact}" ><BR>
			<input type="hidden" name="row" value="${info.ownerID}">
			<input type="hidden" name="action" value="edit">
			<input type="submit" />
		</form>
</body>
</html>