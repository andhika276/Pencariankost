<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos- Update Data Kamar</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="text" name="ownerId" value="${ownerId}"> <BR>
		<input type="text" name="roomID" value="${room.roomId}"> <BR>
		Update kota : <input type="text" name="city" value="${room.city}"> <BR>
		Update alamat : <input type="text" name="address" value="${room.address}"> <BR>
		Update luas kamar (m^2) : <input type="text" name="totalRoomArea" value="${room.totalRoomArea}"> <BR>
		Update harga sewa (perbulan) : <input type="text" name="rentalCost" value="${room.rentalCost}"> <BR>
		<input type="hidden" name="action" value="edit_room">
		<input type="submit"/>
	</form>
</body>
</html>