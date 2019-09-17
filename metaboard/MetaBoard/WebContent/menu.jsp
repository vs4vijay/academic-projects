<%@page errorPage="error.jsp" %>
<head>
<link rel="stylesheet" href="css/menuStyle.css" type="text/css" />
<link rel="stylesheet" href="theme/jquery.ui.all.css">
<script src="jquery-1.8.2.js"></script>
<script src="ui/jquery.ui.core.js"></script>
<script src="ui/jquery.ui.widget.js"></script>
<script src="ui/jquery.ui.datepicker.js"></script>

<script>
function adminOperation(t) {
	var nameValue = prompt("Enter Name");
	
	var exp = /^[a-zA-Z]([0-9a-z_ ,.:?])+$/i;
	var errorMessage;
	var flag = true;
	
	
	if (!exp.test(nameValue))
	{
		errorMessage = 'Wrong Section/Group name';
		flag = false;
	}
	var len = nameValue.length;
	
	if (len == 0) {
		errorMessage = 'Section/Group name is require';
		flag = false;
	}
	if (len > 20) {
		errorMessage = 'Section/Group name size must be less then 20';
		flag = false;
	}
	if (flag) {
		document.getElementById("opType").value = t.value;
		document.getElementById("name").value = nameValue;
		document.getElementById("adminOperationForm").submit();
	} else {
		document.getElementById("errorDiv").innerHTML = errorMessage;
		document.getElementById("errorDiv").style.display = "block";
	}
}
</script>

</head>
<body style="background-color: #EBEBEB">
	<div>
		<form action="adminOperationAction.do" id="adminOperationForm">
			<input type="hidden" value="" name="opType" id="opType" /> <input
				type="hidden" value="" name="name" id="name" />

		</form>
		<ul id="menu" class="topmenu" style="width: 100%;color:white;">

			<%
				if (session.getAttribute("emailID") != null) {
			%>

			<div style="float: right">
				<li class="topExtra"><a style="width: 140px;color:white; height: 25px;">

						<form action="/logoutAction" name="form1" method="get" id="form1"
							onclick="javascript: window.location='logoutAction.do'">
							Logout</form> </a></li>
			</div>

			<div style="float: left;">
				<li class="topExtra"><a style="height: 25px; color:white;cursor: default;">
						Welcome <%=session.getAttribute("userName")%> </a></li>
				<%
					if (Integer.parseInt(session.getAttribute("userType")
								.toString()) == 1) {
				%>
				<li><select name="adminOptions" onchange="adminOperation(this)"
					style="background-color: #79bbff;color:white; padding-left: 10px; padding-right: 1px; margin-left: 10px; margin-right: 10px; height: 35px; border-radius: 6px;">
						<option value="select">Select</option>
						<option value="addGroup">Add Group</option>
						<option value="removeGroup">Remove Group</option>
						<option value="addSection">Add Section</option>
						<option value="removeSection">Remove Section</option>
				</select></li>
				<li class="topExtra"><a
					style="height: 25px;width:400px; cursor: default; color: red; display: none; text-shadow: #FFF 0 0 5px;"
					id="errorDiv" ></a></li>
				<%
					}
				%>
			</div>

			<%
				} else {
			%>

			<div style="float: right">
				<li class="topExtra"><a style="width: 140px; height: 25px;"
					onclick="javascript: document.getElementById('form1').submit()">
						<form action="socialAuth.do" name="form1" method="get" id="form1">
							<input type="hidden" value="google" name="id" />Login
						</form> </a></li>
			</div>

			<%
				}
			%>

		</ul>
	</div>

</body>
</html>
