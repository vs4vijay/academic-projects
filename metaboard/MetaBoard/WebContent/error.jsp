<%@page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errors</title>

<link rel="stylesheet" href="css/generalSectionStyle.css" type="text/css">

</head>
<body style="background-color: #c5e2f4">

<div style="float: left">

	<font color="red" size="4"> 
	<% if(request.getAttribute("errorMessage") != null) { %>
		<%= request.getAttribute("errorMessage") %>
	<br />
	<% } %>
	
	<%= exception.getMessage() %>
	
	<html:errors />
	
	</font>
</div>

<div style="float: right">

	<input type="button" value="Back" class="upperButton" onclick="history.back()">

</div>

</body>
</html>