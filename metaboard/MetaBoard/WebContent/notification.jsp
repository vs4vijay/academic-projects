<%@page errorPage="error.jsp" %>
<%@page
	import="com.metagurukul.metaboard.model.notification.*,com.metagurukul.metaboard.model.group.*"%>
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
	
	
	$( ".horiNotice" ).draggable({ scroll: false });
	
});

	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$("#dialog:ui-dialog").dialog("destroy");

		var title = $("#titleBox"), description = $("#description"), validity = $("#validity"), groupID = $("#groupID"), allFields = $(
				[]).add(title).add(description).add(validity).add(groupID), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}
		
		function isSelected(o) {
			if ($("#groupID :selected").length == 0)
			{
				o.addClass("ui-state-error");
				updateTips("please select atleast one group");
				return false;
			}
			else
			{
				return true;
			}
		}

		$("#add-dialog-form").dialog({
			autoOpen : false,
			width : 350,
			modal : true,
			buttons : {
				"Post" : function() {
					var bValid = true;
					allFields.removeClass("ui-state-error");

					bValid = bValid && checkLength( title, "title", 3, 30 );
					bValid = bValid && checkRegexp( title, /^[a-zA-Z]([0-9a-z_ ,.:?])+$/i, "Wrong Title" );
					
					bValid = bValid && checkLength( description, "description", 6, 250 );
					//bValid = bValid && checkRegexp( description, /^[a-zA-Z]([0-9a-z_ ,.:?])+$/i, "Wrong Description" );
					
					bValid = bValid && checkLength( validity, "validity", 10, 25 );
					bValid = bValid && checkRegexp( validity, /[0-9]{4}-[0-9]{2}-[0-9]{2}/, "Wrong validity" );
					
					bValid = bValid && isSelected(groupID);

					if (bValid) 
					{
						$("#postForm").submit();
					}
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});
		
		$( "#update-dialog-form" ).dialog({
			autoOpen: false,
			width: 350,
			modal: true,
			buttons: {
				"Post": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
				
					if ( bValid ) {
						$("#updateForm").submit();
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
		$( "#mail-dialog-form" ).dialog({
			autoOpen: false,
			width: 350,
			modal: true,
			buttons: {
				"Send": function() {
					var bValid = true;
							
					if ( bValid ) {
						$("#mailForm").submit();
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
		$( "#mailForward-dialog-form" ).dialog({
			autoOpen: false,
			width: 350,
			modal: true,
			buttons: {
				"Send": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
					if ( bValid ) {
						$("#mailForwardForm").submit();
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
	});
	
	$(function() {
		$( "#validity" ).datepicker({ dateFormat: 'yy-mm-dd 00:00:00' });
		$( "#validityUpdate" ).datepicker({ dateFormat: 'yy-mm-dd 00:00:00' });
	});

	function changeMailDialogContent(emailID)
	{
		document.getElementById("creatorEmailID").value=emailID;
	}
	
	function changeMailForwardDialogContent(subject,content)
	{
		document.getElementById("subject").value=subject;
		document.getElementById("content").value=content;
		
	}
	
	function changeDialogContent(titleId,descriptionId,expiryDateId,notificationId)
	{
		
		document.forms[1].elements[0].value=(document.getElementById(titleId).innerHTML).trim();
		document.forms[1].elements[1].value=(document.getElementById(descriptionId).innerHTML).trim();
		document.forms[1].elements[2].value=(document.getElementById(expiryDateId).innerHTML).trim();
		document.forms[1].elements[4].value=(document.getElementById(notificationId).innerHTML).trim();
	}
	function change(label) {
		var searchSpan = document.getElementById("searchSpan");
		searchSpan.innerHTML = '<img src="images/bsearch2.png" alt=""/>'
				+ label;
		
	}
	function addCalendar(t)
	{
		if((t.value)=="date")
		{
			
			$( "#searchVal" ).datepicker({ dateFormat: 'yy-mm-dd' });
		}
		else
			{
			$('#searchVal').datepicker("destroy");
			
			}
	}

</script>
</head>
<body>


	<div id="main">

		<%
			NotificationDAO nDAO = new NotificationDAO();
			ArrayList<Notification> notiList = null;
			String searchVal = null;
			String searchBy = null;
			String myPost = "";
			String archive = null;
			if (request.getParameter("myPost") != null) {
				if (request.getParameter("myPost").equals("true")) {
					notiList = (ArrayList<Notification>) request
							.getAttribute("result");
				}
				myPost = request.getParameter("myPost");
			} else {

				if (request.getParameter("searchVal") != null) {
					searchVal = request.getParameter("searchVal");
					searchBy = request.getParameter("searchBy");
					System.out.println("ssssssssssss");
				}

				if (request.getParameter("sectionID") != null) {
					sectionID = Integer.parseInt(request
							.getParameter("sectionID"));
				}

				if (request.getParameter("sectionID") != null
						&& request.getParameter("searchFlag") == null) {
					sectionID = Integer.parseInt(request
							.getParameter("sectionID"));
					System.out.println("hello");
					notiList = nDAO.show(session.getAttribute("groupID")
							.toString(), sectionID);
					System.out.println("hellos");
				}
			}
			int count = 0;
			if (request.getParameter("count") == null)
				count = 0;
			else {
				count = Integer.parseInt(request.getParameter("count"));
				sectionID = Integer.parseInt(request.getParameter("sectionID"));
				notiList = (ArrayList<Notification>) request
						.getAttribute("result");
			}
			if (request.getParameter("searchFlag") != null) {
				notiList = (ArrayList<Notification>) request
						.getAttribute("result");
			}
		%>

		<div style="height: 50px;">
			<ul>
				<li><input type="button" value="My Post" class="upperButton"
					onclick="javascript:window.location='showAction.do?sectionID=<%=sectionID%>&myPost=true&searchBy=a&searchVal=a'" />
				</li>

				<li><input type="button" value="Add"
					onclick='$( "#add-dialog-form" ).dialog( "open" )'
					class="upperButton" /></li>
				<li>
					<%
						if (count > 0) {
					%> <input type="button" value="<<"  class="
					upperButton" onclick="javascript:window.location='showAction.do?count=<%=count - 3%>&sectionID=<%=sectionID%>&searchBy=<%=searchBy%>&searchVal=<%=searchVal%>&myPost=<%=myPost%>'" />
					<%
						}
					%>
				</li>
				<li>
					<%
					
						if (notiList != null) {
							System.out.println("notification not null");
							if ((count + 3) < notiList.size()) {
					%> <input type="button" value=">>" class="upperButton"
					onclick="javascript:window.location='showAction.do?count=<%=count + 3%>&sectionID=<%=sectionID%>&searchBy=<%=searchBy%>&searchVal=<%=searchVal%>&myPost=<%=myPost%>'" />
					<%
						}
					%>
				</li>
				<li></li>
				<li><input type="button" id="goButton" name="goButton"
					value="Search" class="dropDown"
					onclick="javascript:window.location='showAction.do?searchFlag=true&sectionID=<%=sectionID%>&myPost=false&searchBy=' + document.getElementById('searchBy').value + '&searchVal=' + document.getElementById('searchVal').value" />
				</li>

				<li><select name="searchBy" class="dropDown" id="searchBy"
					onchange="addCalendar(this)">
						<option value="">Criteria</option>
						<option value="title">By Title</option>
						<option value="date">By Date</option>
						<option value="name">By Creator Name</option>
				</select></li>

				<li><input type="text" id="searchVal" name="searchVal"
					class="rightSearch" onfocus="javascript:this.value=''" /></li>
			</ul>

		</div>

		<%
			for (int j = 1, i = count; i < notiList.size() && i < count + 3; i++, j++) {
					if (notiList.get(i) != null) {
						System.out.println("ssss size= " + notiList.size());
		%>
		<div class="horiNotice">
			<div class="header">
				<%
					if (Integer.parseInt(session.getAttribute("userType")
										.toString()) == 1) {
				%>
				<input type="button" value="delete" class="button"
					onclick="javascript: window.location='deleteAction.do?notificationID=<%=notiList.get(i).getNotificationID()%>&sectionID=<%=sectionID%>'" />
				<%
					}
								if (Integer.parseInt(session.getAttribute("memberID")
										.toString()) == (notiList.get(i).getCreatorID())
										|| Integer.parseInt(session.getAttribute(
												"userType").toString()) == 1) {
				%>

				<input type="button" value="update"
					onclick='$( "#update-dialog-form" ).dialog( "open" )'
					onfocus="changeDialogContent('title<%=j%>','description<%=j%>','expiryDate<%=j%>','notificationId<%=j%>')"
					class="button" />
				<%
					if (notiList.get(i).getArchived() == 0) {
				%>
				<input type="button" value="archive" class="button"
					onclick="javascript: window.location='removeAction.do?notificationID=<%=notiList.get(i).getNotificationID()%>&sectionID=<%=sectionID%>'" />

				<%
					} else {
				%>
				<input type="button" value="Restore" class="button"
					onclick="javascript: window.location='restoreAction.do?notificationID=<%=notiList.get(i).getNotificationID()%>&sectionID=<%=sectionID%>'" />

				<%
					}
								}
				%>
			</div>
			<div class="topic" id="title<%=j%>"><%=notiList.get(i).getTitle()%>
			</div>
			<div class="content" id="description<%=j%>"><%=notiList.get(i).getDescription().trim()%>
			</div>
			<div class="footer">
				posted:<%=notiList.get(i).getPostedTime()
								.toLocaleString()%>
				<br> by:<%=nDAO.getCreatorName(notiList.get(i)
								.getCreatorID())%><a href="#"
					onmouseover="changeMailDialogContent('<%=nDAO.getCreatorEmailID(notiList.get(i)
								.getCreatorID())%>')"
					onclick='$( "#mail-dialog-form" ).dialog( "open" )'>[Mail Me]</a> <a
					href="#"
					onmouseover="changeMailForwardDialogContent('<%=notiList.get(i).getTitle()%>','<%=notiList.get(i).getDescription()%>')"
					onclick='$( "#mailForward-dialog-form" ).dialog( "open" )'>[Forward]</a>
			</div>
			<div style="visibility: hidden;" id="expiryDate<%=j%>"><%=notiList.get(i).getExpiryDate()%></div>
			<div style="visibility: hidden;" id="notificationId<%=j%>"><%=notiList.get(i).getNotificationID()%></div>
		</div>


		<%
			}
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
		
			if (request.getParameter("searchFlag") != null || myPost.equals("true")) {
		%>
		<input type="button" class="upperButton" value="Back"
			onclick="history.back()" />

		<%
			}
		%>
	</center>
	<%
		}
	%>

	<div class="demo" style="display: none">

		<div id="add-dialog-form" title="Post Notification">
			<p class="validateTips">All form fields are required.</p>
			<form action="postAction.do" id="postForm">
				<table>
					<tr>
						<td>Title</td>
						<td><input type="text" name="title" id="titleBox"
							class="text ui-widget-content ui-corner-all">
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td><textarea name="description" id="description" rows="3"
								cols="20" class="text ui-widget-content ui-corner-all"></textarea>
						</td>
					</tr>

					<tr>
						<td>Validity</td>
						<td><input type="text" name="expiryDate" id="validity"
							class="text ui-widget-content ui-corner-all" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>Send TO</td>
						<td><select id="groupID" name="groupID" multiple="multiple"
							class="text ui-widget-content ui-corner-all">
								<%
									GroupDAO groupDAO = new GroupDAO();
									ArrayList<Group> groups = groupDAO.getGroups();
								%>

								<%
									for (Group group : groups) {
								%>
								<option value="<%=group.getGroupID()%>"><%=group.getGroupName()%></option>
								<%
									}
								%>

						</select>
						</td>
					</tr>
				</table>

				<input type="hidden" name="notificationID" value="" /> <input
					type="hidden" name="sectionID" value="<%=sectionID%>" /> <input
					type="hidden" name="archived" value="0" /> <input type="hidden"
					name="creatorID"
					value="<%=session.getAttribute("memberID").toString()%>" />


			</form>
		</div>
	</div>

	<div class="demo" style="display: none">

		<div id="update-dialog-form" title="update Notification">
			<p class="validateTips">All form fields are required.</p>
			<form action="updateAction.do" id="updateForm">
				<table>
					<tr>
						<td>Title</td>
						<td><input type="text" name="title" id="titleBox"
							class="text ui-widget-content ui-corner-all" />
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td><textarea name="description" id="description" rows="3"
								cols="20" class="text ui-widget-content ui-corner-all"></textarea>
						</td>
					</tr>

					<tr>
						<td>Validity</td>
						<td><input type="text" name="expiryDate" id="validityUpdate"
							class="text ui-widget-content ui-corner-all" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
						<input type="hidden" id="groupID" name="groupID"/>
						</td>
					</tr>
				</table>

				<input type="hidden" name="notificationID" /> <input type="hidden"
					name="sectionID" value="<%=sectionID%>" /> <input type="hidden"
					name="archived" value="0" /> <input type="hidden" name="creatorID"
					value="<%=session.getAttribute("memberID").toString()%>" />
					
			</form>
		</div>
	</div>
	<div class="demo" style="display: none">

		<div id="mail-dialog-form" title="Send Mail">
			<p class="validateTips">All form fields are required.</p>
			<form action="sendMail.do" id="mailForm" target="_parent">
				<input type="hidden" name="emailID" id="creatorEmailID"></input>
				<table>

					<tr>
						<td>Subject </td>
						<td><input type="text" name="subject"></input>
						</td>
					</tr>
					<tr>
						<td>Message </td>
						<td><textarea rows="5" cols="20" name="message"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="demo" style="display: none">

		<div id="mailForward-dialog-form" title="Forward Mail">
			<p class="validateTips"></p>
			<form action="sendMail.do?forward=true" id="mailForwardForm"
				target="_parent">

				<table>
					<tr>
						<td>To </td>
						<td><input type="text" name="emailID" id="creatorEmailID"></input>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="hidden" name="subject" id="subject"></input>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="hidden" name="message" id="content" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>