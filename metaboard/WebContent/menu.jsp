<head>
	<link rel="stylesheet" href="menuStyle.css" type="text/css" />
	<link rel="stylesheet" href="theme/jquery.ui.all.css">
	<script src="jquery-1.8.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>

	<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			changeMonth: true,
			changeYear: true
		});
	});
	</script>
	<script>

		function check(search)
		{
			var search = document.getElementById("search");
			if(search.value == "")
			{
				alert("Value is NULL" + search.value)
			}
			else
			{
				alert("Not Null :: " + search.value);
				document.getElementById("form1").submit();
			}
		}
		
		function change(label)
		{
			var searchSpan = document.getElementById("searchSpan");
			searchSpan.innerHTML = '<img src="images/bsearch2.png" alt=""/>'+label;
		}
		$(function() {
			$( "#datepicker" ).datepicker({
				changeMonth: true,
				changeYear: true
			});
		});

	</script>
</head>
<body style="background-color:#EBEBEB">
<div>

<ul id="menu" class="topmenu" style="width: 100%;">
<div style="float:left">
	<li class="topfirst"><a style="width:140px; height:25px;">
	<form action="##form" id="form1">   <input type="text" name="search" id="datepicker"/>    </form></a></li>
	<li class="toplast" >
		<a class="pressed" onclick="check(search)" style="width:113px;height:25px;line-height:25px;">
		<span id="searchSpan"> <img src="images/bsearch2.png" alt=""/>Search</span></a>
		<ul>
			<li><a href="#1" onclick="change('By Title')"><img src="images/256base-3.png" alt=""/>By Title</a></li>
			<li><a href="#2" onclick="change('By Date')"><img src="images/256base-4.png" alt=""/>By Date</a></li>
			<li><a href="#2" onclick="change('By Creator')"><img src="images/256base-21.png" alt=""/>By Creator</a></li>
		</ul>
	</li>
	</div>
	
	<div style="float:right">
	<li class="topExtra"> 
		<a style="width:140px; height:25px;"> <form action="##form" id="form1"> Login</form> </a>
	</li>
	</div>
	
	<div style="float:right">
	<li class="topExtra"> 
		<a style="width:140px; height:25px;"> Welcome Vizay </a>
	</li>
	</div>
	
</ul>
</div>

</body>
</html>
