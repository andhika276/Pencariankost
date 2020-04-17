<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HBASE WEB-Input</title>
</head>
<body>
	<form action="ActionController" method="post">
		Enter name : <input type="text" name="name"> <BR>
		Enter city : <input type="text" name="city"> <BR>
		Enter designation : <input type="text" name="designation"> <BR>
		Enter salary : <input type="text" name="salary"> <BR>
		<input type="hidden" name="action" value="insert">
		<input type="submit" />
	</form>
</body>
</html>