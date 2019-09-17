<%@page errorPage="error.jsp" %>
<%@page import="com.metagurukul.metaboard.model.notification.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.metagurukul.metaboard.dao.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaBoard-A Notice Board System</title>
<link rel="stylesheet" href="generalSectionStyle.css" type="text/css">
<link rel="stylesheet" href="menuStyle.css">
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

<%
	int sectionID = 1;
	if (request.getParameter("sectionID") != null) {
		sectionID=Integer.parseInt(request.getParameter("sectionID"));
	}
%>
<script>
	$(function() {

		$(".horiNotice").draggable({scroll : false
		});

	});

	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	
	
	function addCalendar(t) {
		if ((t.value) == "date") {

			$("#searchVal").datepicker({
				dateFormat : 'yy-mm-dd'
			});
		} else {
			$('#searchVal').datepicker("destroy");

		}
	}
</script>
</head>
<body bgcolor="#c5e2f4">


	<div id="main">

		<%
			int count = 0;
			NotificationDAO nDAO = new NotificationDAO();
			ArrayList<Notification> notiList = null;
			if (request.getParameter("sectionID") != null) {
				sectionID = Integer.parseInt(request.getParameter("sectionID"));
				notiList = nDAO.show(sectionID);
			}

			if (request.getParameter("count") == null)
				count = 0;
			else {
				count = Integer.parseInt(request.getParameter("count"));
				sectionID = Integer.parseInt(request.getParameter("sectionID"));
				notiList = (ArrayList<Notification>) request
						.getAttribute("result");
			}
		%>

		<div style="height: 50px;">
			<ul>
				<li>
					<%
						if (count > 0) {
					%> <input type="button" value="<<"  class="
					upperButton" onclick="javascript:window.location='showPublicNotificationAction.do?sectionID=<%=sectionID%>&count=<%=count - 3%>'" />
					<%
						}
					%>
				</li>
				<li>
					<%
						if (notiList != null) {
							if ((count + 3) < notiList.size()) {
					%> <input type="button" value=">>" class="upperButton"
					onclick="javascript:window.location='showPublicNotificationAction.do?sectionID=<%=sectionID%>&count=<%=count + 3%>'" />
					<%
						}
					%>
				</li>

			</ul>

		</div>

		<%
			for (int j = 1, i = count; i < notiList.size() && i < count + 3; i++, j++) {
					if (notiList.get(i) != null) {
						System.out.println("size= " + notiList.size());
		%>
		<div class="horiNotice">
			<div class="header"></div>
			<div class="topic" id="title<%=j%>"><%=notiList.get(i).getTitle()%>
			</div>
			<div class="content" id="description<%=j%>"><%=notiList.get(i).getDescription()%>
			</div>
			<div class="footer">
				posted:<%=notiList.get(i).getPostedTime()
								.toLocaleString()%>
				<br> by:<%=nDAO.getCreatorName(notiList.get(i)
								.getCreatorID())%>
			</div>
			<div style="visibility: hidden;" id="expiryDate<%=j%>"><%=notiList.get(i).getExpiryDate()%></div>
			<div style="visibility: hidden;" id="notificationId<%=j%>"><%=notiList.get(i).getNotificationID()%></div>
		</div>

		<%
			}
		%>

		<%
			}
		%>
	</div>
	<%
		} else {
	%>
	<center>
		<div style="font-size: 20px; color: blue;">Notification not
			available</div>
		<br>
		<%
			if (request.getParameter("searchFlag") != null) {
		%>
		<input type="button" value="Back" onclick="history.back()">

		<%
			}
		%>
	</center>
	<%
		}
	%>



</body>
</html>