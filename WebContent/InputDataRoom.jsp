<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ActionController" method="post">
		Masukan IDPemilik : <input type="text" name="name"> <BR>
		Masukan Alamat : <input type="text" name="address"> <BR>
		Masukan Jumlah komentar : <input type="text" name="totalComment"> <BR>
		Masukan Harga Kamar : <input type="text" name="rentalCost"> <BR>
		Masukan Jumlah Watt listrik : <input type="text" name="totalWatt"> <BR>
		Masukan Lantai Kamar : <input type="text" name="floorNumber"> <BR>
		Masukan Jumlah Ruangan yang tersedia : <input type="text" name="totalRoomArea"> <BR>
		Masukan Tenpat toilet : <input type="text" name="totalToiletArea"> <BR>
		<input type="hidden" name="action" value="insert_room">
		<input type="submit" />
	</form>
</body>
</html>