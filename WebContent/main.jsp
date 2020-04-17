<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HBASE WEB</title>
</head>
<body>
	<div align="center">
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="retrieve">
			<input type="submit" name="action" value="to_input">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>Name</th>
                <th>City</th>
                <th>Designation</th>
                <th>Salary</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <c:forEach items="${dataList}" var="dataItem">
            	<form action="ActionController" method="post" action="edit" method="post">
			        <tr>
			        
			            <td>${dataItem.vname}</td>
			            <td>${dataItem.vcity}</td>
			            <td>${dataItem.vdesignation}</td>
			            <td>${dataItem.vsalary}</td>
			           
			            <td><input type="submit" name="action" value="delete"></td>
			            <td><input type="submit" name="action" value="update"></td>
			        </tr>
			        <input type="hidden" name="row" value="${dataItem.vRow}">
			        <input type="hidden" name="name" value="${dataItem.vname}">
			        <input type="hidden" name="city" value="${dataItem.vcity}">
			        <input type="hidden" name="designation" value="${dataItem.vdesignation}">
			        <input type="hidden" name="salary" value="${dataItem.vsalary}">
			     </form>
		    </c:forEach>
        </table>
    </div>
</body>
</html>