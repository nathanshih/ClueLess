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
	
	<div id="container">
	
		<h1>ClueLess</h1>
		<p>Welcome to the game of ClueLess!</p>
		
		<h2>Choose to play as:</h2>
		<input type="radio" name="player" id="Miss Scarlet" value="Miss Scarlet">Miss Scarlet<br>
		<input type="radio" name="player" id="Colonel Mustard" value="Colonel Mustard">Colonel Mustard<br>
		<input type="radio" name="player" id="Mrs. White" value="Mrs. White">Mrs. White<br>
		<input type="radio" name="player" id="Mr. Green" value="Mr. Green">Mr. Green<br>
		<input type="radio" name="player" id="Mrs. Peacock" value="Mrs. Peacock">Mrs. Peacock<br>
		<input type="radio" name="player" id="Professor Plum" value="Professor Plum">Professor Plum<br>
		<button id="joinGame">Join Game</button>
		<div id="joinGameResponse"></div>
		
		<h2>Players:</h2>
		<textarea id="currentPlayers" rows="6"></textarea>
		
		<br>
		
		<button id="playClueLess" disabled>Play ClueLess</button>
		<p>Minimum 3 players needed.</p>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			poll();			
			
			// Join game
			$("#joinGame").click(function() {
				var player = $("input:radio[name=player]:checked").val();
				if (typeof player != "undefined") {
					$.ajax({type: "POST",
							url: "${pageContext.request.contextPath}/v1",
							data: "action=join" + "&player=" + player,
							success: function(response) {
								$("#joinGameResponse").text("You will play as " + player + ".").css("color", "green");
							},
							dataType: "json"
						});
				} else {
					$("#joinGameResponse").text("You must select a character to play.").css("color", "red");
				}
			});
			
			// Begin game
			$("#playClueLess").click(function() {
				window.location.href = "/ClueLess/";
			});
			
			// Poll continuously poll server every second for updated game state
			function poll() {
				var display = "";
				setTimeout(function() {
			    	$.ajax({url: "${pageContext.request.contextPath}/v1",
			    			type: "GET",
			    			success: function(response){
			    				$("input:radio[name=player]").removeAttr("disabled");
			    				$.each(response["players"], function(id, name) {
									display = display + name + "\n";
									document.getElementById(name).disabled=true;
								});
			    				//$("input:radio[name=player]:not(:disabled):first").attr("checked", true);
								$("#currentPlayers").text(display).css("color", "green");
								if (response.gameReady===true) {
									$("#playClueLess").removeAttr("disabled");
									$("p").hide();
								}
			  				}, 
			  				dataType: "json", 
			  				complete: poll, 
			  				timeout: 30000 
			  			});
				}, 1000);
			};
			
		});
	
	</script>
	
  </body>
</html>