<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>ClueLess</title>
    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.3.1/jquery.cookie.min.js"></script>
      <style>
        body { background-color: #eee; font: helvetica; }
        #container { width: 1000px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
        .green { font-weight: bold; color: green; }
        .message { margin-bottom: 10px; }
        label { width:70px; display:inline-block;}
        .hide { display: none; }
        .error { color: red; font-size: 0.8em; }
        .inlineTable { display: inline-block; }
        #boardAndPad { display:inline-block; } 
        #lobbyAndCards { display:inline-block; }
        #messagesAndActions { display:inline-block; }
      </style>
  </head>
  <body>
	<div id="container">
    <h1>ClueLess</h1>
    <br/>
    <div id="boardAndPad">
		<table cellspacing="0" cellpadding="0" border="0" background="http://i.imgur.com/mGJ85IX.jpg" class="inlineTable">
    		<tr>
      			<td width="100" height="96" align="center"><canvas id="Study" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway1" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hall" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway2" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Lounge" width="100" height="96"/></td>
    		</tr>
    		<tr>
      			<td width="100" height="96" align="center"><canvas id="Hallway3" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway4" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway5" width="100" height="96"/></td>
    		</tr>
    		<tr>
     		 	<td width="100" height="96" align="center"><canvas id="Library" width="100" height="96"/></td>
     			<td width="100" height="96" align="center"><canvas id="Hallway6" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="BillardRoom" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway7" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="DiningRoom" width="100" height="96"/></td>
    		</tr>
    		<tr>
      			<td width="100" height="96" align="center"><canvas id="Hallway8" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway9" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway10" width="100" height="96"/></td>
    		</tr>
    		<tr>
      			<td width="100" height="96" align="center"><canvas id="Conservatory" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway11" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Ballroom" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Hallway12" width="100" height="96"/></td>
      			<td width="100" height="96" align="center"><canvas id="Kitchen" width="100" height="96"/></td>
    		</tr>
  	  	</table>
	<%-- 	<canvas id="padCanvas" width="152" height="500" style="background: url(http://i.imgur.com/B1K3vHh.png) no-repeat center center;"></canvas> --%>
	</div>
	<div id="lobbyAndCards">
		<p>Cards in hand:<p>
		<textarea id="cardsInHand" rows="6" cols="15" readonly></textarea>
		<br>
    	<p>Players still in lobby:<p>
  		<canvas id="tokensNotInPlay" width="100" height="96" ></canvas>
  	</div>
  	<br>
  	<div id="messagesAndActions">
  	<p>Messages:<p>
	<textarea id="gameMessages" rows="6" cols="90" readonly></textarea>
  		
  		<br>
  		You are playing as: <input type="text" name="playerSuspectToken" id="playerSuspectToken" readonly>
  		You are currently in: <input type="text" name="playerLocation" id="playerLocation" size="30" readonly>
  		<br>
  		
		<div id="radioButtons">
			<input type="radio" name="turnActionGroup" id="moveRB" value="moveRB" >Move
			<input type="radio" name="turnActionGroup" id="suggestRB" value="suggestRB" >Suggest
			<input type="radio" name="turnActionGroup" id="accuseRB" value="accuseRB" >Accuse<br/>
		</div>
	
		<select id="movablelocationList" name="movablelocationList">
       		<option id="aaa" value="aaa">-Movable Locations-</option>   
		</select>

   		<select id="locationList" name="locationList">
    	    <option id="aaa" value="aaa">-Select a Location-</option>          
            <option id="Study" value="Study">Study</option>
    	    <option id="Hall" value="Hall">Hall</option>
     	 	<option id="Lounge" value="Lounge">Lounge</option>
        	<option id="Library" value="Library">Library</option>
       		<option id="BillardRoom" value="BillardRoom">Billard Room</option>
	        <option id="DiningRoom" value="DiningRoom">Dining Room</option>
	        <option id="Conservatory" value="Conservatory">Conservatory</option>
	        <option id="Ballroom" value="Ballroom">Ballroom</option>
        	<option id="Kitchen" value="Kitchen">Kitchen</option>   
    	</select>
    	
	    <select id="suspectList" name="suspectList">
    	    <option id="aaa" value="aaa">-Select a Suspect-</option>   
			<option id="Miss Scarlet" value="Miss Scarlet">Miss Scarlet</option>
			<option id="Colonel Mustard" value="Colonel Mustard">Colonel Mustard</option>
      	  	<option id="Mrs. White" value="Mrs. White">Mrs. White</option>
      	  	<option id="Mr. Green" value="Mr. Green">Mr. Green</option>
      	  	<option id="Mrs. Peacock" value="Mrs. Peacock">Mrs. Peacock</option>
       	 	<option id="Professor Plum" value="Professor Plum">Professor Plum</option>    
    	</select>
   
		<select id="weaponList" name="weaponList">
        	<option id="aaa" value="aaa">-Select a Weapon-</option>   
			<option id="Rope" value="Rope">Rope</option>
        	<option id="LeadPipe" value="LeadPipe">Lead Pipe</option>
        	<option id="Knife" value="Knife">Knife</option>
        	<option id="Wrench" value="Wrench">Wrench</option>
        	<option id="Candlestick" value="Candlestick">Candlestick</option>
        	<option id="Revolver" value="Revolver">Revolver</option>    
    	</select>
    
		<select id="disprovableCardsList" name="disprovableCardsList">
        	<option id="aaa" value="aaa">-Select a card-</option>
    	</select>

    <br/>
    
    	<button id="moveButton" >Move</button>
		<button id="suggestButton" >Suggest</button>
    	<button id="accuseButton" >Accuse</button>
    	<button id="disproveButton" >Disprove</button>
    	<br/>
    	<button id="endTurnButton" >End Turn</button>
    	<button id="leaveGameButton" >Leave Game</button>
    </div>
	</div>
  <script type="text/javascript">
  /* alert("In script"); */
  $(document).ready(function() {
	cardsInHand();
    poll();
    
    // Leave game
	//cuases an expetion.... thats new
    $("#leaveGameButton").click(function() {
    if(confirm("Are you sure you want to leave the game?"))
    {
      $.ajax({type: "POST",
        url: "${pageContext.request.contextPath}/v1",
        data: "action=leave",
        success: function(response) { 
                window.location.href = "/ClueLess/";},
        dataType: "json"
      });
    }
    else
    {
        e.preventDefault();
    }
    });


	// Poll continuously poll server every second for updated game state
	function poll() {
		
		clearBoard();
		drawTokens();
		setTimeout(function() {
	    	$.ajax({url: "${pageContext.request.contextPath}/v1",
	    			type: "GET",
	    			success: function(response){
	    	            var response = response;
	    	            var playerName = $.cookie("playerName");
	    	            
	    	            var playerSuspectToken = response.players[playerName].suspect;
	    	            $("#playerSuspectToken").val(playerSuspectToken);
	    	            var playerLocation = response.players[playerName].location;
	    	            if (playerLocation == null) {
	    	            	$("#playerLocation").val(playerSuspectToken + "'s starting square");
	    	            } else {
	    	            	$("#playerLocation").val(playerLocation);
	    	            }
	    	            
	    	            //check to see if game has been won
	    	            if (response.solvedBy != null)
	    	            {
	    	            	var solvedBy = "";
	    	            	solvedBy = solvedBy + response.solvedBy; 
	    	            	endGame(solvedBy);
	    	            }
	    	            
	    	            //check for accusation messages
	    	            if (response.makingAccusation != null){
	    	            	var accusation = response.accusation;
	    	            	var msg = "";
	    	            	msg = msg + response.makingAccusation + " made the accusation";
	    	            	
	    	            	$.each(accusation, function(key, value){
	    	            		msg = msg + " " + value;
	    	            	});
	    	            	msg = msg + "\n";
		    	            $("#gameMessages").text(msg).css("color", "black");	
	    	            }
	    	            
	    	            //check for suggestions
	    	    	    if (response.makingSuggestion != null){
	    	    	    	
	    	    	    	//we need to get the solution object here
	    	    	    	//and check to see if disprovig card has been set
	    	    	    	//otehrwise as soon as a disproveing card is posted
	    	    	    	//whoCanDisprove is reset to null and then it appears
	    	    	    	//that no one can disprove the suggestion
	    	    	    	$.ajax({url: "${pageContext.request.contextPath}/v1",
								type: "GET",
								data: "action=suggestion",
								success: function(suggestionResponse)
								{
 									var suggestionResponse = suggestionResponse;
									var disprovingCard = suggestionResponse.disprovingCard;
			    	    	    	
									var suggestion = response.suggestion;
			    	    	        var msg = "";
			    	    	        msg = response.makingSuggestion + " suggests the crime was commited in the " + suggestion[0] + " by " + suggestion[1] + " using the " + suggestion[2] + ".";
			    	    	        msg = msg + "\n";
									
			    	    	        if (disprovingCard != null){
			    		    	    	if (response.makingSuggestion == playerName){
			    		    	    		msg = response.whoCanDisprove + " showed you the card " + disprovingCard;
			    		    	    		$("#gameMessages").text( msg ).css("color", "black");
			    				    		$("#endTurnButton").attr("disabled", false); 
			    		    	    	}
									}
									else {
				    	    	    	//If the suggestion cant be disproven
				    		    	    if (suggestionResponse.canBeDisproven == false){
				    		    	    	//let everyone know what the suggestion was and by whom
				    		    	    	msg = msg + "\nIt could not be disproven";
				    		    	    	$("#gameMessages").text( msg ).css("color", "black");
				    		    	    	
				    		    	    	//and unlock the suggestors end turn button
				    		    	    	if (response.makingSuggestion == playerName){
				    				    		$("#endTurnButton").attr("disabled", false); 
				    		    	    	}
				    		    	    }
				    	    	        //or if it can be disproven
				    		    	    else {
				    		    	    	//let everyone know who can disrpove it
				    		    	    	msg = msg + "\nIt can be disproven by " + response.whoCanDisprove;
				    		    	    	$("#gameMessages").text( msg ).css("color", "black");
				    		    	    	//and then take the player that can disrpove it to the disprove method
				    		    	    	if (response.whoCanDisprove == playerName){
				    		    	    		disprove();
				    		    	    	}
				    		    	    }	
									}

								}, 
								dataType: "json", 
								timeout: 30000 
	  						});
	   	    	    	}
	    	            
	    	            
	    	            var whoseTurn = response.whoseTurn;
	    	            //check for won game (ie check each failedaccusation for each player for )
	    	            //hmmm how will that work? they are always false
	    	            
	    	            //if we got here then the game is still active if it's our players turn
	    	            //wait for thier action
	    	            //var playerName = $.cookie("playerName");
	    	            //to get here ther game is still active, this player can not disprove (or nothing to disprove)
	    	            //and it's now this players turn
	    	            if (whoseTurn == playerName) {
	    	            	    	       
	    				    $("#radioButtons").show();
	    				    //check json object to see if player was moved to a room
	    				    //if so then immeditaly uncheck the suggestion radio button
	    				    //also the player can accuse at any time so unlock that adio button
	    				    //regardless
	    				    
	    				    //move should always be selected by default. Makes the logic easier
	    				    //and baring an accusation or being in a room becuase of a suggestion
	    				    //they'll likely want to move
							$('input[name=turnActionGroup]').removeAttr('disabled');
	    	            }
	    	            else {
	    	            	$("#radioButtons").hide();
	    	            	hideUI();
	    	            }
	  				}, 
	  				dataType: "json", 
	  				complete: poll, 
	  				timeout: 30000 
	  		});
		}, 1000);
	};

    function clearBoard() {
    	var canvases = new Array(); 
    	canvases[0]="Hallway1";       
    	canvases[1]="Hallway2";
    	canvases[2]="Hallway3";
    	canvases[3]="Hallway4";       
    	canvases[4]="Hallway5";
    	canvases[5]="Hallway6";
    	canvases[6]="Hallway7";
    	canvases[7]="Hallway8";
    	canvases[8]="Hallway9";
    	canvases[9]="Hallway10";
    	canvases[10]="Hallway11";
    	canvases[11]="Hallway12";
    	canvases[12]="tokensNotInPlay";
    	canvases[13]="Study";
    	canvases[14]="Hall";
    	canvases[15]="Lounge";
    	canvases[16]="Library";
    	canvases[17]="BillardRoom";
    	canvases[18]="DiningRoom";
    	canvases[19]="Conservatory";
    	canvases[20]="Ballroom";
    	canvases[21]="Kitchen";
    	
    	$.each(canvases, function(index,value){
            var c=document.getElementById(value);
        	var ctx=c.getContext("2d");
        	ctx.clearRect(0,0,100,96);
    	});
    };

    function drawTokens() {
        $.ajax({
            url: "${pageContext.request.contextPath}/v1",
            type: "GET",
            success: function (response) {
                var rooms = response.rooms;
                $.each(rooms, function (outterIndex, outterValue) {
                    var room = outterIndex;
                    var tokens = response.rooms[room].tokens;
                    if (tokens !== 0) {
                        $.each(tokens, function (innerIndex, innerValue) {
                            var token = innerValue;
                            //alert(room + " " + token);
                            var c = document.getElementById(room);
                            var ctx = c.getContext("2d");
                            ctx.font = "20px Arial";
                            switch (token) {
                                case "Colonel Mustard":
                                    ctx.fillStyle = "#FFCC11";
                                    ctx.fillRect(0, 0, 25, 25);
                                    break;
                                case "Miss Scarlet":
                                    ctx.fillStyle = "#8C1717";
                                    ctx.fillRect(25, 0, 25, 25);
                                    break;
                                case "Mrs. White":
                                    ctx.fillStyle = "#FFE9E9";
                                    ctx.fillRect(50, 0, 25, 25);
                                    break;
                                case "Mr. Green":
                                    ctx.fillStyle = "#99CC32";
                                    ctx.fillRect(0, 25, 25, 25);
                                    break;
                                case "Mrs. Peacock":
                                    ctx.fillStyle = "#016795";
                                    ctx.fillRect(25, 25, 25, 25);
                                    break;
                                case "Professor Plum":
                                    ctx.fillStyle = "#8E4585";
                                    ctx.fillRect(50, 25, 25, 25);
                                    break;
                                case "Rope":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("r", 25, 60);
                                    break;
                                case "Revolver":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("R", 50, 60);
                                    break;
                                case "Candlestick":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("C", 75, 60);
                                    break;
                                case "LeadPipe":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("L", 25, 80);
                                    break;
                                case "Knife":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("K", 50, 80);
                                    break;
                                case "Wrench":
                                    ctx.strokeStyle = "black";
                                    ctx.strokeText("W", 75, 0);
                                    break;
                                default:
                                    ctx.strokeStyle = "black";
                            }
                        });

                        var theHallways = response.hallways;
            			$.each(theHallways, function (outterIndex, outterValue)
                        {
                        	var hallway = outterIndex;
                         		if (response.hallways[hallway].token != null)
                         		{
                    				var playerTokens = response.hallways[hallway].token;
                         	        var d=document.getElementById(hallway);
                    	            var ctx=d.getContext("2d");
                    	            ctx.font="25px Arial";
            						switch(playerTokens)
                    	            {
                                    	case "Colonel Mustard":
                                           ctx.fillStyle="#FFCC11";
                                           ctx.fillRect(0,0,25,25);
                                           break;
                                        case "Miss Scarlet":
                                        		ctx.fillStyle= "#8C1717";
                                            	ctx.fillRect(25,0,25,25);
                                        		break;
                                  	     case "Mrs. White":
                                        		ctx.fillStyle= "#FFE9E9";
                                            	ctx.fillRect(50,0,25,25);
                                        		break;
                                        case "Mr. Green":
                                        		ctx.fillStyle= "#99CC32";
                                            	ctx.fillRect(0,25,25,25);
                                        		break;
                                        case "Mrs. Peacock":
                                        		ctx.fillStyle= "#016795";
                                            	ctx.fillRect(25,25,25,25);
                                        		break;
                                        case "Professor Plum": 
                                        		ctx.fillStyle= "#8E4585";
                                            	ctx.fillRect(50,25,25,25);
                                        		break;
                                    	default:
                                        		var foo = "test";
                    	               }
                    			}
                    		});
                    }
                });
            },
            dataType: "json",
            timeout: 30000
        });
    };

    $("#moveButton").click(function() {
		  var e = document.getElementById("movablelocationList");
		  var selectedLocation = e.options[e.selectedIndex].value;
		  if (selectedLocation == "aaa"){
			  alert("You need to select a location before you can move there");
		  }
		  else { $.ajax({type: "POST",
					url: "${pageContext.request.contextPath}/v1",
					data: "action=move" + "&location=" + selectedLocation,
					success: function(response) {
						var move = 'moveRB';
						$("input[type=radio][value=" + move + "]").prop("disabled",true);
						resetUI();
						
					},
					dataType: "json"
				});
		  }
		  //after a move unlock accuse
		  //if player moves to room (not a hallway) unock suggest
	});

	$("#accuseButton").click(function() {
		  var e = document.getElementById("locationList");
		  var selectedLocation = e.options[e.selectedIndex].id;
		  var f = document.getElementById("suspectList");
		  var selectedSuspect = f.options[f.selectedIndex].id;
		  var g = document.getElementById("weaponList");
		  var selectedWeapon = g.options[g.selectedIndex].id;
		  if (selectedLocation == "aaa" || selectedSuspect == "aaa" || selectedWeapon == "aaa"){
			  alert("You need to select a room, suspect, and a weapon before you can make an accusation");
		  }
		  else {
		  if(confirm("Are you sure you want to accuse " +selectedSuspect + " in the " + selectedLocation + " with the " + selectedWeapon + "?"))
		    {
				$.ajax({type: "POST",
					url: "${pageContext.request.contextPath}/v1",
					data: "action=accusation" + "&room=" + selectedLocation + "&suspect=" + selectedSuspect  + "&weapon=" + selectedWeapon , // for a move action=move&location=hallway5
					success: function(response) {
					},
					dataType: "json",
			  		timeout: 30000 
				});
				
				alert("Accusation made");
		    	  $.ajax({url: "${pageContext.request.contextPath}/v1",
		    		type: "GET",
		    		success: function(response){
		    	    	var response = response;
		    	        var playerName = $.cookie("playerName");
		    			var failedAccusation = response.players[playerName].failedAccusation;
						if (failedAccusation == true)
						{
							alert("You lost the game!");
							
						}
		    	    }, 
		  			dataType: "json", 
		  			timeout: 30000 
		  		});
				
			
		    }
		    else
		    {
		        e.preventDefault();
		    }
		  }
	});

	$("#disproveButton").click(function() {
		var f = document.getElementById("disprovableCardsList");
		var selectedCard = f.options[f.selectedIndex].value;
		alert("You selected the card " + selectedCard + " to disprove the suggestion");
		if (selectedCard == "aaa") {
			alert("You need to select a one of the disprovable cards");
		}
	   	else{
			$.ajax({type: "POST",
				url: "${pageContext.request.contextPath}/v1",
				data: "action=disprove" + "&card=" + selectedCard,
				success: function(response) {
				},
				dataType: "json"
			});
	   	}
		
		//clear the UI once disprove method is completed
		$("#disprovableCardsList").empty().append('<option id="aaa" value="aaa">-Select a card-</option>');
		 $("#disprovableCardsList").hide();
		 $("#disproveButton").hide();

	});

		$("#suggestButton").click(function(){
			  var f = document.getElementById("suspectList");
			  var selectedSuspect = f.options[f.selectedIndex].id;
			  var g = document.getElementById("weaponList");
			  var selectedWeapon = g.options[g.selectedIndex].id;

 			  $.ajax({url: "${pageContext.request.contextPath}/v1",
					type: "GET",
					success: function(response)
					{
						  var playerName = $.cookie("playerName");
						  var location = response.players[playerName].location;
						  var canSuggest = response.players[playerName].canSuggest;
						  
						  if (selectedSuspect == "aaa" || selectedWeapon == "aaa"){
							  alert("You need to select a suspect and a weapon before you can make an suggestion");
						  }
						  
						else if( location == "Hallway1" || location == "Hallway2" || location == "Hallway3" ||
							  location == "Hallway4" || location == "Hallway5" || location == "Hallway6" ||
							  location == "Hallway7" || location == "Hallway8" || location == "Hallway9" ||
							  location == "Hallway10" || location == "Hallway11" || location == "Hallway10")
						  {
							alert("You can not suggest from the hallways"); 
						  }
						  //cant suggest becuase of flag in object
						  else if (canSuggest != true) {
							alert("You can not make a suggestion at this time, you need to exit this room");
						  }
						  
						  else{
							$.ajax({type: "POST",
								url: "${pageContext.request.contextPath}/v1",
								data: "action=suggestion" + "&suspect=" + selectedSuspect  + "&weapon=" + selectedWeapon,
								success: function(response) {
									var suggest = 'suggestRB';
									$("input[type=radio][value=" + suggest + "]").prop("disabled",true);
									if (response.disprovingCard == null){
										$("#gameMessages").text("No one could disprove your suggestion").css("color", "black");
									}
									else{
							    		var msg = "";
							    		msg = msg + "A player can disprove your suggestion\n";
							    		$("#gameMessages").text(msg).css("color", "black");
							    		//unlock this in the cofunction under poll
							    		 
									}
								},
					  			timeout: 30000,
								dataType: "json"
							});
						  }
						
					}, 
					dataType: "json", 
					timeout: 30000 
			  	});
		});

	$("input:radio[name=turnActionGroup]").click(function(){
	      var value = $(this).attr("id");
	      if (value == "moveRB"){
	          $("#movablelocationList").show();
	          $("#moveButton").show();
	    	  $("#locationList").hide();
	    	  $("#suspectList").hide();
	    	  $("#weaponList").hide();
	    	  $("#accuseButton").hide();
	    	  $("#suggestButton").hide();
	    	  
	    	  $.ajax({url: "${pageContext.request.contextPath}/v1",
	    			type: "GET",
	    			success: function(response){
	    	            var response = response;
	    	            var whoseTurn = response.whoseTurn;
	    	            var playerName = $.cookie("playerName");
	    	            if (whoseTurn == playerName) {
	    					var movableLocations = response.players[playerName].movableLocations;
	    					var duplicateFound = false;
	    					$.each(movableLocations, function(outterKey, outterValue) {
	    					    $("#movablelocationList > option").each( function(innerKey, innerValue) {
	    					        if (outterValue == innerValue.value){
	    					            duplicateFound = true;
	    					        }
	    					    });
	    					    if (duplicateFound == false){        
	    					        $("#movablelocationList").append('<option value="'+outterValue+'">'+outterValue+'</option>');
	    					    }
	    					});
	    	            }
	  				}, 
	  				dataType: "json", 
	  				timeout: 30000 
	  		});
	      } 
	      if (value == "accuseRB"){
	    	  $("#locationList").show();
	    	  $("#suspectList").show();
	    	  $("#weaponList").show();
	    	  $("#accuseButton").show();
	    	  
	          $("#movablelocationList").hide();
	          $("#moveButton").hide();
	          
	    	  $("#suggestButton").hide();
	      } 
	      if (value == "suggestRB"){
	    	  $("#suspectList").show();
	    	  $("#weaponList").show();
	    	  $("#suggestButton").show();
	    	  
	          $("#movablelocationList").hide();
	          $("#moveButton").hide();
	          
	    	  $("#locationList").hide();
	    	  $("#accuseButton").hide();
	      }
	});
	
	$("#endTurnButton").click(function() {
		  $.ajax({type: "POST",
		  		url: "${pageContext.request.contextPath}/v1",
		    	data: "action=endTurn",
		    	success: function(response) {
		    		alert("Turn ended");
					resetUI();
		    	},  //do we need a success parameter?
		    	dataType: "json"
		    });
	});

	function cardsInHand(){
		$.ajax({url: "${pageContext.request.contextPath}/v1",
			type: "GET",
			success: function(response){
		    	var response = response;
		    	var playerName = $.cookie("playerName");
		    	var cardsInHand = response.players[playerName].cardsInHand;
				var msg = "";
				$.each(cardsInHand, function(key, value) {
					msg = msg + value + "\n";
				});
		    	$("#cardsInHand").text(msg).css("color", "black");
		    }, 
			dataType: "json",
			timeout: 30000 
		});
	}
	
	function disprove()
	{
	    $("#disprovableCardsList").show();
	  	$("#disproveButton").show();
	  	$.ajax({url: "${pageContext.request.contextPath}/v1",
			type: "GET",
			data: "action=suggestion",
			success: function(response)
			{
				/*alert("I am in disprove"); */
 				var response = response;
				var disprovableCards = response.disprovableCards;
				var duplicateFound = false;
				$.each(disprovableCards, function(outterKey, outterValue) {
				    $("#disprovableCardsList > option").each( function(innerKey, innerValue) {
				        if (outterValue == innerValue.value){
				            duplicateFound = true;
				        }
				    });
				    if (duplicateFound == false){        
				        $("#disprovableCardsList").append('<option value="'+outterValue+'">'+outterValue+'</option>');
				   }
				});
			}, 
			dataType: "json", 
			timeout: 30000 
	  	});
	};
	
	function resetUI(){
	     //the movable location and the cards to disprove will have 
	     //to be emptied and repopualated with default values
	     $("input:radio[name='turnActionGroup']").each(function(i) {
       		this.checked = false;
		 });
	     $("#movablelocationList").empty().append('<option id="aaa" value="aaa">-Movable Locations-</option>');
		 hideUI();
	}
    
	function hideUI(){
	     $("#movablelocationList").hide();
	     $("#moveButton").hide();
	     $("#locationList").hide();
	  	 $("#suspectList").hide();
	  	 $("#weaponList").hide();
	     $("#accuseButton").hide();
	     $("#suggestButton").hide();
	}
	
	function endGame(solvedBy){
    	var wonBy = "";
    	wonBy = wonBy + solvedBy + "has won the game!";
    	$("#gameMessages").text(wonBy).css("color", "green");
    	if (solvedBy ==  $.cookie("playerName")){
    		alert("You have won the game!");    		
    	}
    	else{
    		alert("You have lost!\nThe murder was solved by + " + solvedBy);
    	}

    	//jump to end game function, lock the board, something
	}
  //end bracket for ready	
  });
  </script>  
  </body>
</html>