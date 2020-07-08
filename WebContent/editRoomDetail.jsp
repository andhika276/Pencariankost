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
		Update luas kamar (m^2) : <input type="number" name="totalRoomArea" value="${room.totalRoomArea}"> <BR>
		Update Jenis kamar mandi : <BR>
			<input type="radio" name="jenisToilet" value="dalam">Kamar Mandi Dalam<BR>
			<input type="radio" name="jenisToilet" value="luar">Kamar Mandi Luar<BR>
		Update luas toilet (m^2)  : <input type="number" name="totalToiletArea" value="${room.totalToiletArea}"> <BR>
		Update harga sewa (perbulan) : <input type="number" name="rentalCost" value="${room.rentalCost}"> <BR>
		<input type="hidden" name="action" value="edit_room">
		<input type="submit" value="Selesai"/>
	</form>
</body>
</html>