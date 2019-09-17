<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Accordion - Sortable</title>
	<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
	<script src="jquery/jquery-1.8.2.js"></script>
	<script src="jquery/ui/jquery.ui.core.js"></script>
	<script src="jquery/ui/jquery.ui.widget.js"></script>
	<script src="jquery/ui/jquery.ui.mouse.js"></script>
	<script src="jquery/ui/jquery.ui.sortable.js"></script>
	<script src="jquery/ui/jquery.ui.accordion.js"></script>

	<style>
	/* IE has layout issues when sorting (see #5413) */
	.group { zoom: 1 }
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

<div id="accordion">
	<div class="group">
		<h3><a href="#"><center><img src="images/birthday.png" height="5%" width="20%"/></center></a></h3>
		<div>
			<iframe src="notification.jsp" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
	<div class="group">
		<h3><a href="#"><center><img src="images/announcements.png" height="5%" width="20%"/></center></a></h3>
		<div>
			<iframe src="notification.jsp" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
	<div class="group">
		<h3><a href="#"><center><img src="images/other.png" height="5%" width="20%"/></center></a></h3>
		<div>
			
			<iframe src="notification.jsp" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
	<div class="group">
		<h3><a href="#"><center><img src="images/section4.png" height="5%" width="20%"/></center></a></h3>
		<div>
			<iframe src="notification.jsp" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
	<div class="group">
		<h3><a href="#"><center><img src="images/section5.png" height="5%" width="20%" /></center></a></h3>
		<div>
			<iframe src="notification.jsp" frameborder="0" height="400px" width="100%"></iframe>
		</div>
	</div>
</div>

</div><!-- End demo -->




</body>
</html>