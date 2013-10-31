<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>ClueLess</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
		<input type="radio" name="player" value="Miss Scarlet" checked>Miss Scarlet<br>
		<input type="radio" name="player" value="Colonel Mustard">Colonel Mustard<br>
		<input type="radio" name="player" value="Mrs. White">Mrs. White<br>
		<input type="radio" name="player" value="Mr. Green">Mr. Green<br>
		<input type="radio" name="player" value="Mrs. Peacock">Mrs. Peacock<br>
		<input type="radio" name="player" value="Professor Plum">Professor Plum<br>
		<input type="button" id="joinGame" value="Join Game" />
		<div id="joinGameResponse" class="green"> </div>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			// Join game
			$("#joinGame").click(function() {
				var player = $("input:radio[name=player]:checked").val();
				var display = "";
				$.ajax({url: "${pageContext.request.contextPath}/v1/join?player=" + player,
						type: "POST",
						success: function(response) {
							$.each(response["players"], function(id, name) {
								display = display + name + "<br>";
							});
							$("#joinGameResponse").text(display);
						},
						dataType: "json",
						complete: poll
					});
			});
			
			function poll() {
				var display = "";
				setTimeout(function() {
			    	$.ajax({url: "${pageContext.request.contextPath}/v1",
			    			type: "GET",
			    			success: function(response){
			    				$.each(response["players"], function(id, name) {
									display = display + name + " ";
								});
								$("#joinGameResponse").text(display);
			  				}, 
			  				dataType: "json", 
			  				complete: poll, 
			  				timeout: 30000 
			  			});
				}, 2000);
			};
			
		});
	
	</script>
	
  </body>
</html>