<%@page errorPage="error.jsp" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<!DOCTYPE html>

<%@page import="com.metagurukul.metaboard.dao.SectionDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.metagurukul.metaboard.model.section.Section"%><html lang="en">
<head>
	<meta charset="utf-8">
	<title>MetaBoard-A Notice Board System</title>
	<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
	<link rel="stylesheet" href="menuStyle.css">
	<script src="jquery/jquery-1.8.2.js"></script>
	<script src="jquery/ui/jquery.ui.core.js"></script>
	<script src="jquery/ui/jquery.ui.widget.js"></script>
	<script src="jquery/ui/jquery.ui.mouse.js"></script>
	<script src="jquery/ui/jquery.ui.sortable.js"></script>
	<script src="jquery/ui/jquery.ui.accordion.js"></script>

	<style>
	/* IE has layout issues when sorting (see #5413) */
	.group { zoom: 1;
	background-color:lightblue;
	font-family: sans-serif;
	font-size: 25px;
	font-weight: bold; }
	</style>
	<script>
	$(function() {
		$( "#accordion" )
			.accordion({
				header: "> div > h3"
			})
			.sortable({
				axis: "y",
				handle: "h3",
				stop: function( event, ui ) {
					// IE doesn't register the blur when sorting
					// so trigger focusout handlers to remove .ui-state-focus
					ui.item.children( "h3" ).triggerHandler( "focusout" );
				}
			});
	});
	
	</script>
</head>
<body>

<div class="demo">

<%SectionDAO sectionDAO=new SectionDAO();
ArrayList<Section> sections=sectionDAO.getSections();
%>
<%if(session.getAttribute("emailID") != null){ %>
<div id="accordion">
	<%for(Section section:sections) {%>
	<div class="group">
	<h3><center><font color=#154c9c><%=section.getSectionName()%></font></center></h3>
		<div>
			<iframe src="notification.jsp?sectionID=<%=section.getSectionID() %>" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
  <%
	}
  %>
</div>
<%}
else
{
%>
<div id="accordion">

<%for(Section section:sections) {%>
	<div class="group">
	<h3><center><font color=#154c9c><%=section.getSectionName()%></font></center></h3>
		<div>
			<iframe src="publicNotification.jsp?sectionID=<%=section.getSectionID() %>" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
  <%
	}
  %>

</div>

<%} %>
</div>

</body>
</html>