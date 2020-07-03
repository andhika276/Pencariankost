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
                <th>Luas Kamar</th>
                <th>Harga</th>
                <th>Fasilitas (Column Family facilty)</th>
                <th>Daftar Barang (Column Family stuff)</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach items="${roomList}" var="room">
            	<form action="ActionController" method="post">
			        <tr>
			            <td>${room.address}</td>
			            <td>${room.totalRoomArea}</td>
			            <td>${room.rentalCost}</td>
			            <td>${room.vsalary}</td>
			            <td>
						<c:forEach items="${dataItem.getObjectList()}" var="ListItem">
							<li style="list-style-type:none;">${ListItem.vmerk}, ${ListItem.vwarna}</li>
						</c:forEach>
						</td>
						<td>${dataItem.revision}</td>
			            <td><input type="submit" name="action" value="delete"></td>
			            <td><input type="hidden" name="action" value="before_update"><input type="submit" value="update"/></td>
			        </tr>
			        <input type="hidden" name="row" value="${dataItem.kode}">
			        <input type="hidden" name="name" value="${dataItem.vname}">
			        <input type="hidden" name="city" value="${dataItem.vcity}">
			        <input type="hidden" name="designation" value="${dataItem.vdesignation}">
			        <input type="hidden" name="salary" value="${dataItem.vsalary}">
			        <input type="hidden" name="salary" value="${dataItem.objectList}">
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>