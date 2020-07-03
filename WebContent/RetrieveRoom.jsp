<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Daftar Kamar Kost</title>
</head>
<body>
	<div align="center">
		<form action="ActionController" method="post">
			<input type="Daftar Kamar" name="action" value="ShowRoom">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>Daftar Kamar Kost</h2></caption>
            <tr>
                <th>Nama</th>
                <th>Alamat</th>
                <th>Nomor Telepon</th>
                <th>Kamar Kost</th>
                <th>Delete</th>
                <th>Update</th>
                <th>Daftar Kamar</th>
            </tr>
            <c:forEach items="${ownerInfo}" var="info">
            	<form action="ActionController" method="post">
			        <tr>
			            <td>${info.name}</td>
			            <td>${info.address}</td>
			            <td>${info.contact}</td>
			            <td><input type="submit" name="action" value="roomInfo"></td>
			            <td><input type="submit" name="action" value="delete"></td>
			            <td><input type="hidden" name="action" value="before_update"><input type="submit" value="update"/></td>
			    		<input type="submit" name="action" value="InsertNewOwner">
			        </tr>
			        <input type="hidden" name="id" value="${info.ownerID}">
			        <input type="hidden" name="name" value="${info.name}">
			        <input type="hidden" name="address" value="${info.address}">
			        <input type="hidden" name="contact" value="${info.contact}">
			        <input type="hidden" name="roomList" value="${info.roomList}">
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>