<%@page errorPage="error.jsp" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>

<div>
	<tiles:insert name="header"  />
</div>

<div>
	<tiles:insert name="menu" />
</div>

<div>
	<tiles:insert name="body" />
</div>

<%
	if(request.getAttribute("logout")!=null) { 
%>
	<iframe src="https://mail.google.com/mail/u/0/?logout&hl=en"  name="logoutFrame" style="display: none"></iframe>
<% 
}
%>

<div>
	<tiles:insert name="footer" />
</div> 