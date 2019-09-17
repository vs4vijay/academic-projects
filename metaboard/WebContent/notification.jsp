<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="generalSectionStyle.css" type="text/css">
<title>jQuery UI Dialog - Modal form</title>
	<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
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

	<style>
		body { font-size: 62.5%; }
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
	</style>
	<script>
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		var name = $( "#name" ),
			email = $( "#email" ),
			password = $( "#password" ),
			allFields = $( [] ).add( name ).add( email ).add( password ),
			tips = $( ".validateTips" );

		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}

		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}
		
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			width: 350,
			modal: true,
			buttons: {
				"Post": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "username", 3, 16 );
					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkLength( password, "password", 5, 16 );

					bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

					if ( bValid ) {
						$( "#users tbody" ).append( "<tr>" +
							"<td>" + name.val() + "</td>" + 
							"<td>" + email.val() + "</td>" + 
							"<td>" + password.val() + "</td>" +
						"</tr>" ); 
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
		
		$( "#create-noti" )
			.button()
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
		$( "#update-noti" )
		.button()
		.addClass("button")
		.click(function() {
			$( "#dialog-form" ).dialog( "open" );
		});
	});
	</script>
</head>
<body>


  <div id ="main">
	
	<div>
		   <input type="button" value="Add"  id="create-noti" />
		   <input type="button" value="<<"  class="upperButton"/>
		   <input type="button" value=">>" class="upperButton" />
	</div>
	<div  class="horiNotice">
		<div class="header" >
		<input type="button" value="update" class="button"  id="update-noti" />
		<input type="button" value="remove" class="button" />
		</div>
		<div class="topic">
			heading
		</div>
		<div class="containt">
			this is containt.
		</div>
		<div class="footer">
			creater name and date
		</div>
	</div>
	<div   class="horiNotice">
	<div class="header" >
		<input type="button" value="update"class="button" id="update-noti" />
		<input type="button" value="remove" class="button" />
		</div>
	<div class="topic">
	heading
	</div>
	<div class="containt">
	this is containt.
	</div>
	<div class="footer">
			creater name and date
		</div>
	</div>
	<div  class="horiNotice">
	<div class="header" >
		<input type="button" value="update"class="button" id="update-noti" />
		<input type="button" value="remove" class="button" />
		</div>
	<div class="topic">
	heading
	</div>
	<div class="containt">
	this is containt.
	</div>
	<div class="footer">
			creater name and date
		</div>
	</div>
	
</div>
<div class="demo">

<div id="dialog-form" title="Post Notification">
	<form>
    <table>
    <tr>
    	<td>
    	Title 
    	</td>
    	<td>
    	<input type="text" name="title" class="text ui-widget-content ui-corner-all">
    
    	</td>
    </tr>
    <tr>
    	<td>
    	Description
    	</td>
    	<td>
    	<textarea name="description" rows="3" cols="20" class="text ui-widget-content ui-corner-all"></textarea>
    	</td>
    </tr>	
    
    <tr>
    	<td>
    	Validity
    	</td>
    	<td>
    	<input type="text" name="validity" class="text ui-widget-content ui-corner-all">
    	</td>
    </tr>
    <tr>
    	<td>
    	Send TO
    	</td>    
    	<td>
   <select name="sendTo" multiple="multiple" class="text ui-widget-content ui-corner-all">
   <option> Public</option>
   <option> Group1</option>
   <option> Group2</option>
   <option> Group3</option>
   </select>
    	</td>
    </tr>
    </table>
       
	</form>
</div>
</div>	
</body>
</html>