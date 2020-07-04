<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Daftar Kamar Kost Pemilik</title>
</head>
<body>
	<div align="center">
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Retrieve">
			<input type="submit" name="action" value="Input">
			<input type="submit" name="action" value="Filter">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>Alamat Kost</th>
                <th>Kota</th>
                <th>Luas Kamar</th>
                <th>Harga</th>
                <th>Daftar Barang (Column Family stuff)</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach items="${roomList}" var="room">
            	<form action="ActionController" method="post">
			        <tr>
			            <td>${room.address}</td>
			            <td>${room.city}</td>
			            <td>${room.totalRoomArea}</td>
			            <td>${room.rentalCost}</td>
			            <td>
						<c:forEach items="${room.getKelengkapan()}" var="barang">
							<li style="list-style-type:none;">${barang}, ${barang}</li>
						</c:forEach>
						</td>
						<td>${room.revision}</td>
			            <td><input type="submit" name="action" value="delete"></td>
			            <td><input type="hidden" name="action" value="before_update"><input type="submit" value="update"/></td>
			        </tr>
			        <input type="hidden" name="roomId" value="${room.roomId}">
			        <input type="hidden" name="ownerId" value="${room.ownerId}">
			        <input type="hidden" name="address" value="${room.address}">
			        <input type="hidden" name="city" value="${room.city}">
			        <input type="hidden" name="totalRoomArea" value="${room.totalRoomArea}">
			        <input type="hidden" name="rentalCost" value="${room.rentalCost}">
			        <input type="hidden" name="daftarBarang" value="${room.barang}">
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>