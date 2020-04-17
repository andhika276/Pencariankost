<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HBASE WEB-Edit</title>
</head>
<body>
	<form action="ActionController" method="post">
		Enter name :<input type="text" name="name" value=<%= request.getParameter("name") %>> <BR>
		Enter city :<input type="text" name="city" value=<%= request.getParameter("city") %> > <BR>
		Enter designation :<input type="text" name="designation"value=<%= request.getParameter("designation") %>> <BR>
		Enter salary :<input type="text" name="salary"value=<%= Integer.parseInt(request.getParameter("salary")) %>> <BR>
		<input type="hidden" name="row" value="${dataList.vRow}">
		<input type="hidden" name="action" value="edit">
		<input type="submit" />
	</form>
</body>
</html>