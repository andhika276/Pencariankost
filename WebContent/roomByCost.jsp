<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pencarian Indekos - Filter Room Cost</title>
</head>
<body>
	<form action="ActionController" method="post">
			Harga minimum (perbulan) : <input type="number" name="minCost"><BR>
			Harga maksimum (perbulan) : <input type="number" name="maxCost"><BR>
			<button type="submit"  name="action" value="roomByCost">Cari</button>
		</form>
</body>
</html>