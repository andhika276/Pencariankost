<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos- Input Data Kamar</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="text" name="id" value="${ownerId}"> <BR>
		Masukan kota : <input type="text" name="city"> <BR>
		Masukan alamat : <input type="text" name="address"> <BR>
		Masukan luas kamar (m^2) : <input type="number" name="totalRoomArea"> <BR>
		Masukan harga sewa (perbulan) : <input type="number" name="rentalCost"> <BR>
		<button type="submit"  name="action" value="insert_room">Input Room Data</button>
	</form>

</body>
</html>