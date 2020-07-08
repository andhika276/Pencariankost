<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Daftar Pemilik Kost</title>
</head>
<body>
	<div align="center">
		<form action="ActionController" method="post">
			<button type="submit"  name="action" value="retrieve_all_room">Tampilkan Semua Kamar</button>
			<button type="submit"  name="action" value="retrieve_owner">Tampilkan Semua Owner</button>
			<button type="submit"  name="action" value="to_input_owner">Input Owner Baru</button>
			<button type="submit"  name="action" value="to_filter_owner">Filter</button>
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>Daftar Pemilik Kost</h2></caption>
            <tr>
                <th>Nama</th>
                <th>Alamat</th>
                <th>Nomor Telepon</th>
                <th>Jumlah Kamar Kost</th>
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
			            <td>${info.roomTotal}</td>
			            <td><button type="submit"  name="action" value="delete_owner">Hapus</button></td>
			            <td><button type="submit"  name="action" value="update_owner">Update</button></td>
			    		<td align="center"><button type="submit"  name="action" value="roomInfo">Daftar Kamar</button></td>
			        </tr>
			        <input type="hidden" name="id" value="${info.ownerId}">
			        <input type="hidden" name="name" value="${info.name}">
			        <input type="hidden" name="address" value="${info.address}">
			        <input type="hidden" name="contact" value="${info.contact}">
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>