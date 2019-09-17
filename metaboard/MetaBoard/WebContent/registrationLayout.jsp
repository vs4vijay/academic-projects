<%@page errorPage="error.jsp" %>
<%@page import="com.metagurukul.metaboard.model.group.Group"%>
<%@page import="com.metagurukul.metaboard.dao.GroupDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>

<%@ taglib prefix="html" uri="/tags/struts-html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="jquery/demos.css">
<script src="jquery/jquery-1.8.2.js"></script>
<script src="jquery/external/jquery.bgiframe-2.1.2.js"></script>
<script src="jquery/ui/jquery.ui.core.js"></script>
<script src="jquery/ui/jquery.ui.widget.js"></script>
<script src="jquery/ui/jquery.ui.mouse.js"></script>
<script src="jquery/ui/jquery.ui.button.js"></script>
<script src="jquery/ui/jquery.ui.draggable.js"></script>
<script src="jquery/ui/jquery.ui.position.js"></script>
<script src="jquery/ui/jquery.ui.resizable.js"></script>
<script src="jquery/ui/jquery.ui.dialog.js"></script>
<script src="jquery/ui/jquery.effects.core.js"></script>
<script src="jquery/ui/jquery.ui.datepicker.js"></script>
<script src="registrationScript.js"></script>

<script>

$(function() {
	$( "#dob" ).datepicker({
		changeMonth: true,
		changeYear: true,dateFormat: 'yy-mm-dd'
	});
	//$( "#dob" ).datepicker({ dateFormat: 'yy-mm-dd' });
});

</script>
	
	<link rel="stylesheet" href="registrationStyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>

<div>
	<tiles:insert name="header" />
</div>

<font align="center" color="blue">
	<center>
		<h2>Create Your Account</h2>
	</center> </font>
<hr />
<body bgcolor="#c5e2f4">
    <% 
    
    Map<String,String> userInfo=(HashMap<String,String>)session.getAttribute("userInfo");
    
    String userName=userInfo.get("firstName")+" "+userInfo.get("lastName");
    String emailID=userInfo.get("emailID");
    
    %>
	<html:form action="registrationAction.do" onsubmit="return validate()" >
		<table align="center" width="80%" height="80%"
			style="margin: 60px 0px 10px 180px;">
			
			<tr height=40px style="vertical-align: top">
				<td align="right"><font color="red">*</font>Name</td>
				<td align="left">
				<html:text property="name" styleId="name" value="<%=userName %>" onblur="validateName()" readonly="true"/>
					<div id="nameError" style="color: #FF0000;"></div></td>
			</tr>
			
			<tr height=40px style="vertical-align: top">
				<td align="right"><font color="red">*</font>Email Id:</td>
				<td><html:text property="emailID" styleId="emailID" value="<%=emailID %>"
					onblur="validateEmailId()" readonly="true"/>
					<div id="mailIDError" style="color: #FF0000;"></div></td>
			</tr>

			<tr height=40px style="vertical-align: top">
				<td align="right"><font color="red">*</font>Date Of Birth:</td>
				<td><html:text property="dob" styleId="dob" readonly="true" onblur="validateDOB()"/>
					<div id="dobError" style="color: #FF0000;"></div></td>
			</tr>

			<tr height=40px style="vertical-align: top">
				<td align="right" style="vertical-align: top"><font color="red">*</font>Contact
					:</td>
				<td><html:text property="contact" styleId="contactNumber"
					onblur="validateContact()" />
					<div id="mobileError" style="color: #FF0000;"></div></td>
			</tr>

			<tr height=40px style="vertical-align: top">
				<td align="right"><font color="red">*</font>Group Name:</td>
				<td><html:select property="groupID"  onblur="validateGroupName()" styleId="groupID">
						<html:option value="0">Select</html:option>
						<%GroupDAO groupDAO=new GroupDAO();
								ArrayList<Group> groups=groupDAO.getGroups();
								
								%>
								
								<%for(Group group:groups) {%>
								<html:option value='<%=""+group.getGroupID()%>'><%=group.getGroupName()%></html:option>
								<%
								 } %>
				</html:select>
					<div id="groupIDError" style="color: #FF0000;"></div></td>
			</tr>
			
			<html:hidden property="memberID" value="" />
			<html:hidden property="catID" value="2" />
			
			<tr height=40px>
				<td></td>
				<td colspan="2">
				<html:submit value="Register" styleClass="upperButton"/>
				</td>
			</tr>
			
		</table>
	</html:form>

</body>

<div>
	<tiles:insert name="footer" />
</div>

</html>