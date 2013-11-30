<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>ClueLess</title>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.3.1/jquery.cookie.min.js"></script>
    <style>
    	body { background-color: #eee; font: helvetica; margin: 0px; padding: 0px; font-family: Calibri;}
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label { width:70px; display:inline-block;}
    	.hide { display: none; }
    	.error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>

<table cellspacing="0" cellpadding="0" border="0" background="http://i.imgur.com/mGJ85IX.jpg">
  <tr>
    <td width="100" height="96" align="center"><canvas id="study" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall1" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall2" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="lounge" width="100" height="96"/></td>
  </tr>
  <tr>
    <td width="100" height="96" align="center"><canvas id="hall3" width="100" height="96"/></td>
    <td width="100" height="96" align="center"></td>
    <td width="100" height="96" align="center"><canvas id="hall4" width="100" height="96"/></td>
    <td width="100" height="96" align="center"></td>
    <td width="100" height="96" align="center"><canvas id="hall5" width="100" height="96"/></td>
  </tr>
  <tr>
    <td width="100" height="96" align="center"><canvas id="library" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall6" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="billiard" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall7" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="dinning" width="100" height="96"/></td>
  </tr>
  <tr>
    <td width="100" height="96" align="center"><canvas id="hall8" width="100" height="96"/></td>
    <td width="100" height="96" align="center"></td>
    <td width="100" height="96" align="center"><canvas id="hall9" width="100" height="96"/></td>
    <td width="100" height="96" align="center"></td>
    <td width="100" height="96" align="center"><canvas id="hall10" width="100" height="96"/></td>
  </tr>
  <tr>
    <td width="100" height="96" align="center"><canvas id="conservatory" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall11" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="ball" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="hall12" width="100" height="96"/></td>
    <td width="100" height="96" align="center"><canvas id="kitchen" width="100" height="96"/></td>
  </tr>
</table>
<%-- <div>
	<canvas id="boardCanvas" width="725" height="646" style="background: url(http://i.imgur.com/xFkG89v.png) no-repeat center center;"></canvas>
	<canvas id="padCanvas" width="184" height="646" style="background: url(http://i.imgur.com/hKxU6Kh.png) no-repeat center center;"></canvas>
</div> --%>
	<form>
  		<div id="radio">
    		<input type="radio" id="move" name="radio" onclick="toggle_visibility('locations');"><label for="move">Move</label>
    		<input type="radio" id="suggest" name="radio"><label for="suggest">Suggest</label>
    		<input type="radio" id="accuse" name="radio"><label for="accuse">Accuse</label>
  		</div>
	</form>
    
	<input type="submit" value="End Turn">	
	<div id="container">
	
		<h1>ClueLess</h1>
		
		<br>
		<button id="leaveGame">Leave Game</button>
		
	</div>
	<script type="text/javascript">
    
	$( "#study" ).click(function(event) { alert(event.target.id); });
	$( "#hall1" ).click(function(event) { alert(event.target.id); });
	$( "#hall" ).click(function(event) { alert(event.target.id); });
	$( "#hall2" ).click(function(event) { alert(event.target.id); });
	$( "#lounge" ).click(function(event) { alert(event.target.id); });
	$( "#hall3" ).click(function(event) { alert(event.target.id); });
	$( "#hall4" ).click(function(event) { alert(event.target.id); });
	$( "#hall5" ).click(function(event) { alert(event.target.id); });
	$( "#library" ).click(function(event) { alert(event.target.id); });
	$( "#hall6" ).click(function(event) { alert(event.target.id); });
	$( "#billiard" ).click(function(event) { alert(event.target.id); });
	$( "#hall7" ).click(function(event) { alert(event.target.id); });
	$( "#dinning" ).click(function(event) { alert(event.target.id); });
	$( "#hall8" ).click(function(event) { alert(event.target.id); });
	$( "#hall9" ).click(function(event) { alert(event.target.id); });
	$( "#hall10" ).click(function(event) { alert(event.target.id); });
	$( "#conservatory" ).click(function(event) { alert(event.target.id); });
	$( "#hall11" ).click(function(event) { alert(event.target.id); });
	$( "#ball" ).click(function(event) { alert(event.target.id); });
	$( "#hall12" ).click(function(event) { alert(event.target.id); });
	$( "#kitchen" ).click(function(event) {alert(event.target.id); });
	
		$(document).ready(function() {
			poll();
			// Leave game
			$("#leaveGame").click(function() {
				$.ajax({type: "POST",
						url: "${pageContext.request.contextPath}/v1",
						data: "action=leave",
						success: function(response) {
							window.location.href = "/ClueLess/";
						},
						dataType: "json"
				});
			});
			
			// Poll continuously poll server every second for updated game state
			function poll() {	
				drawTokens();
				setTimeout(function() {
			    	$.ajax({url: "${pageContext.request.contextPath}/v1",
			    			type: "GET",
			    			success: function(response){
								// TODO: Add response handling
								var whoseTurn = response.whoseTurn;
			    				var playerName = $.cookie("playerName");
			    				if (whoseTurn == playerName) {
			    					alert("It is your turn!");
			    				}
			  				}, 
			  				dataType: "json", 
			  				complete: poll, 
			  				timeout: 30000 
			  		});
				}, 1000);
				
			};
			function turn() {	};
			
			function toggle_visibility(id) {
		    	var thelist = document.getElementsByClassName("alist");
		    	for (var i = 0; i < thelist.length; i++) {
		    		thelist[i].style.display = 'none';
		    	}
		    	var e = document.getElementById(id);
		    	if(e.style.display == 'block') {
		    		e.style.display = 'none';
		    	} else {
		    		e.style.display = 'block';
		    	}
		    }
			    
	var tokenSize=25;

	function drawTokens( ) {

		});

	</script>
	
  </body>
</html>
</html>