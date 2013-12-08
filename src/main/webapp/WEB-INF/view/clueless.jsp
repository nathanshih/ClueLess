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
        .inlineTable { display: inline-block;}
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
		<canvas id="boardCanvas" width="152" height="500" style="background: url(http://i.imgur.com/B1K3vHh.png) no-repeat center center;"></canvas>
	</div>
	<br/>	
	<div id="cardsAndLobby">
		<p>Cards in hand:<p>
		<textarea id="cardsInHand" rows="6" cols="15" readonly></textarea>
    	<p>Players still in lobby:<p>
  		<canvas id="tokensNotInPlay" width="100" height="96" ></canvas>
  	</div>
  		
	<div id="radioButtons">
		<input type="radio" name="turnActionGroup" id="moveRB" value="moveRB" disable>Move
		<input type="radio" name="turnActionGroup" id="accuseRB" value="accuseRB" disable>Accuse
		<input type="radio" name="turnActionGroup" id="suggestRB" value="suggestRB" disable>Suggest<br/>
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
	        <option id="Ballroom" value="Ballroom">Ball Room</option>
        	<option id="Kitchen" value="Kitchen">Kitchen</option> -->   
    	</select>
    	
	    <select id="suspectList" name="suspectList">
    	    <option id="aaa" value="aaa">-Select a Suspect-</option>   
			<option id="Miss Scarlet" value="Miss Scarlet">Miss Scarlet</option>
			<option id="Colonel Mustard" value="Colonel Mustard">Colonel Mustard</option>
      	  	<option id="Mrs. White" value="Mrs. White">Mrs. White</option>
      	  	<option id="Mr. Green" value="Mr. Green">Mr. Green</option>
      	  	<option id="Mrs. Peacock" value="Mrs. Peacock">Mrs. Peacock</option>
       	 	<option is="Professor Plum" value="Professor Plum">Professor Plum</option>    
    	</select>
   
		<select id="weaponList" name="weaponList">
        	<option id="aaa" value="aaa">-Select a Weapon-</option>   
			<option id="Rope" value="Rope">Rope</option>
        	<option id="Lead Pipe"value="Lead Pipe">Lead Pipe</option>
        	<option id="Knife"value="Knife">Knife</option>
        	<option id="Wrench"value="Wrench">Wrench</option>
        	<option id="Candlestick" value="Candlestick">Candlestick</option>
        	<option id="Revolver" value="Revolver">Revolver</option>    
    	</select>
    
<!--     	<select id="disprovableCardsList" name="disprovableCardsList">
        	<option id="aaa" value="aaa">-Select a card-</option>
    	</select> -->
    <br/>
    
    	<button id="moveButton">Move to location:</button>
		<button id="suggestButton" >Suggest</button>
    	<button id="accuseButton" >Accuse</button>
    	<br/>
    	<button id="endTurnButton" >End Turn</button>
    	<button id="leaveGameButton" >Leave Game</button>

	</div>
  <script type="text/javascript">
  /* alert("In script"); */
  $(document).ready(function() {
    poll();
    
    // Leave game
	//cuases an expetion.... thats new
    $("#leaveGameButton").click(function() {
      $.ajax({type: "POST",
        url: "${pageContext.request.contextPath}/v1",
        data: "action=leave",
        success: function(response) { 
                window.location.href = "/ClueLess/";},
        dataType: "json"
      });
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
	    	            var whoseTurn = response.whoseTurn;
	    	            //check for won game (ie check each failedaccusation for each player for )
	    	            //hmmm how will that work? they are always false
	    	            		
	    	            //check for can disprove
	    	            //if canDisprove == true
	    	            //populate disprovable card list
	    	            //unlock disprovable card list and button
	    	            //when player clicks button fire off the disprove method
	    	            
	    	            //if we got here then the game is still active if it's our players turn
	    	            //wait for thier action
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
	    				    $("#radioButtons").show();
	    				    if (!$("input[name='turnActionGroup']:checked").val()){
	    					     $("#movablelocationList").hide();
	    					     $("#moveButton").hide();
	    					     $("#locationList").hide();
	    					  	 $("#suspectList").hide();
	    					  	 $("#weaponList").hide();
	    					     $("#accuseButton").hide();
	    					     $("#suggestButton").hide();
	    				   	}
							/*	$('input[name=turnActionGroup]').removeAttr('disabled'); */
	    	            }
	    	            else {
	    	            	$("#radioButtons").hide();
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
        $.ajax({url: "${pageContext.request.contextPath}/v1",
        	type: "GET",
            success: function(response){
            	$.each(response["players"], function (index, value) {
                	var whichSuspect = value.suspect;  //returns character name
                    if (value.location === null){
                    	var whichRooms = "tokensNotInPlay";
                    } else {
                    	var whichRooms = value.location;
                    }   
                    var c=document.getElementById(whichRooms);
                    var ctx=c.getContext("2d");
                    //I don't know why but I can't get or set color by function returns or variables.
                    //It's little things like this that really make you hate a language.
					
                    switch(whichSuspect){
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
                    		alert("player not found");
                   	}
                });
                //need a loop to also draw weapons....
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
					data: "action=move" + "&location=" + selectedLocation, // for a move action=move&location=hallway5
					success: function(response) {
						alert(response.suspect + " moved to " + selectedLocation);
					},
					dataType: "json"
				});
		  }
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
			  poll();
		  }
		  else{
			$.ajax({type: "POST",
				url: "${pageContext.request.contextPath}/v1",
				data: "action=accusation" + "&room=" + selectedLocation + "&suspect=" + selectedSuspect  + "&weapon=" + selectedWeapon , // for a move action=move&location=hallway5
				success: function(response) {
					if(responce.solvedBy===null){
						alert("You lost the game!");
					}
				},
				dataType: "json"
			});
		  }
	});    
 
	
	$("#suggestButton").click(function() {
		  var e = document.getElementById("locationList");
		  var selectedLocation = e.options[e.selectedIndex].id;
		  var f = document.getElementById("suspectList");
		  var selectedSuspect = f.options[f.selectedIndex].id;
		  var g = document.getElementById("weaponList");
		  var selectedWeapon = g.options[g.selectedIndex].id;
		  if (selectedLocation == "aaa" || selectedSuspect == "aaa" || selectedWeapon == "aaa"){
			  alert("You need to select a room, suspect, and a weapon before you can make an accusation");
			  poll();
		  }
		  else{
			$.ajax({type: "POST",
				url: "${pageContext.request.contextPath}/v1",
				data: "action=accusation" + "&room=" + selectedLocation + "&suspect=" + selectedSuspect  + "&weapon=" + selectedWeapon , // for a move action=move&location=hallway5
				success: function(response) {
					if(responce.solvedBy===null){
						alert("You lost the game!");
					}
				},
				dataType: "json"
			});
		}
	}); 
    
	$("input:radio[name=turnActionGroup]").click(function(){
	      var value = $(this).attr("id");
	      if (value == "moveRB"){
	    	  alert("Move Radio Button Selected");
	          $("#movablelocationList").show();
	          $("#moveButton").show();
	    	  $("#locationList").hide();
	    	  $("#suspectList").hide();
	    	  $("#weaponList").hide();
	    	  $("#accuseButton").hide();
	    	  $("#suggestButton").hide();
	      } 
	      if (value == "accuseRB"){
	    	  $("#locationList").show();
	    	  $("#suspectList").show();
	    	  $("#weaponList").show();
	    	  $("#accuseButton").show();
	          $("#movablelocationList").hide();
	    	  $("#suggestButton").hide();
	          $("#moveButton").hide();
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
	
	function resetUI(){
	     //the movable location and the cards to disprove will have 
	     //to be emptied and repopualated with default values
	     $("input:radio[name='thename']").each(function(i) {
       		this.checked = false;
		 });
	     $("#movablelocationList").empty().append('<option id="aaa" value="aaa">-Movable Locations-</option>');
	     $("#movablelocationList").hide();
	     $("#locationList").hide();
	  	 $("#suspectList").hide();
	  	 $("#weaponList").hide();
	     $("#accuseButton").hide();
	     $("#suggestButton").hide();
	     $("#moveButton").hide();
	}
    
  //end bracket for ready	
  });
  </script>  
  </body>
</html>