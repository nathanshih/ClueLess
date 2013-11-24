<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>ClueLess</title>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <style>
    	body { background-color: #eee; font: helvetica; }
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label { width:70px; display:inline-block;}
    	.hide { display: none; }
    	.error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>
  
<canvas id="boardCanvas" width="725" height="646" style="background: url(http://i.imgur.com/xFkG89v.png) no-repeat center center;"></canvas>
	
	<div id="container">
	
		<h1>ClueLess</h1>
		
		<br>
		<button id="leaveGame">Leave Game</button>
		
	</div>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			poll();
			drawTokens();
			// Leave game
			$("#leaveGame").click(function() {
				$.ajax({type: "POST",
						url: "${pageContext.request.contextPath}/v1",
						data: "action=leave",
						success: function(response) {
							window.location.href = "/ClueLess/join";
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
			  				}, 
			  				dataType: "json", 
			  				complete: poll, 
			  				timeout: 30000 
			  			});
				}, 1000);
				
			};
			//function turn() {	};
			
			function drawTokens( ) {
					var c=document.getElementById("boardCanvas");
		    		var ctx=c.getContext("2d");
		    		ctx.fillStyle="#FF00FF";
		    		ctx.fillRect(0,0,150,75);
			};
		});
	
	</script>
	
  </body>
</html>