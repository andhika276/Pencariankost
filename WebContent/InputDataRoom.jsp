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
		<input type="text" name="id" value="${ownerId}"> <BR>
		Masukan kota : <input type="text" name="city"> <BR>
		Masukan alamat : <input type="text" name="address"> <BR>
		Masukan luas kamar (m^2) : <input type="text" name="totalRoomArea"> <BR>
		Masukan harga sewa (perbulan) : <input type="text" name="rentalCost"> <BR>
		<input type="hidden" name="action" value="insert_room">
		<input type="submit"/>
	</form>

</body>
</html>