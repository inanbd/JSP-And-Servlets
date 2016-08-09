<%@page import="beans.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>List of Students</h1>
	
	<table>
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>CGPA</th>
		</tr>
		<c:forEach  var="s" items="${slist}">
			<tr>
				<td>${s.id}</td>
				<td>${s.name}</td>
				<td>${s.cgpa}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>