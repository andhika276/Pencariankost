<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="ActionController" method="post">
			Edit name : <input type="text" name="name" value="${dataList.vname}" ><BR>
			Edit city : <input type="text" name="city" value="${dataList.vcity}" ><BR>
			Edit designation : <input type="text" name="designation" value="${dataList.vdesignation}" ><BR>
			Edit salary : <input type="text" name="salary" value="${dataList.vsalary}" ><BR>
			<input type="hidden" name="row" value="${dataList.vRow}">
			<input type="hidden" name="action" value="edit">
			<input type="submit" />
		</form>
</body>
</html>