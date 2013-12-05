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
        #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
        .green { font-weight: bold; color: green; }
        .message { margin-bottom: 10px; }
        label { width:70px; display:inline-block;}
        .hide { display: none; }
        .error { color: red; font-size: 0.8em; }
        .inlineTable { display: inline-block;}
      </style>
  </head>
  <body>
<table cellspacing="0" cellpadding="0" border="0" background="http://i.imgur.com/mGJ85IX.jpg" class="inlineTable">
    <tr>
      <td width="100" height="96" align="center"><canvas id="study" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway1" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hall" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway2" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="lounge" width="100" height="96"/></td>
    </tr>
    <tr>
      <td width="100" height="96" align="center"><canvas id="hallway3" width="100" height="96"/></td>
      <td width="100" height="96" align="center"></td>
      <td width="100" height="96" align="center"><canvas id="hallway4" width="100" height="96"/></td>
      <td width="100" height="96" align="center"></td>
      <td width="100" height="96" align="center"><canvas id="hallway5" width="100" height="96"/></td>
    </tr>
    <tr>
      <td width="100" height="96" align="center"><canvas id="library" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway6" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="billardRoom" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway7" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="diningRoom" width="100" height="96"/></td>
    </tr>
    <tr>
      <td width="100" height="96" align="center"><canvas id="hallway8" width="100" height="96"/></td>
      <td width="100" height="96" align="center"></td>
      <td width="100" height="96" align="center"><canvas id="hallway9" width="100" height="96"/></td>
      <td width="100" height="96" align="center"></td>
      <td width="100" height="96" align="center"><canvas id="hallway10" width="100" height="96"/></td>
    </tr>
    <tr>
      <td width="100" height="96" align="center"><canvas id="conservatory" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway11" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="ballroom" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="hallway12" width="100" height="96"/></td>
      <td width="100" height="96" align="center"><canvas id="kitchen" width="100" height="96"/></td>
    </tr>
  </table>
  <canvas id="tokenNotInPlay" width="100" height="96" ></canvas>
<!-- <table cellspacing="0" cellpadding="0" border="0" background="http://i.imgur.com/mGJ85IX.jpg" class="inlineTable">

</table> -->
<%-- 
<canvas id="boardCanvas" width="725" height="646" style="background: url(http://i.imgur.com/xFkG89v.png) no-repeat center center;"></canvas>
 --%>
<div id="playerInput">
    <input type="radio" name="TurnActionsGroup" id="move"  value="move" onclick="moveEvent()" disabled />Move
    <input type="radio" name="TurnActionsGroup" id="suggest" value="suggest" disabled /> Suggest
    <input type="radio" name="TurnActionsGroup" id="accuse" value="accuse" disabled/> Accuse<br/>
    
    <select id="location" name="location" disabled>
        <option value="SelectALocation">-Select a Location-</option>
<!--         <option value="study">Study</option>
        <option value="hallway1">hallway 1</option>
        <option value="hall">hall</option>
        <option value="hallway2">hallway 2</option>
        <option value="lounge">Lounge</option>
        <option value="hallway3">hallway 3</option>
        <option value="hallway4">hallway 4</option>
		<option value="hallway5">hallway 5</option>
        <option value="library">library</option>
        <option value="hallway6">hallway 6</option>
        <option value="billardRoom">Billard Room</option>
        <option value="hallway7">hallway 7</option>
        <option value="diningRoom">Dining Room</option>
        <option value="hallway8">hallway 8</option>
        <option value="hallway9">hallway 9</option>
		<option value="hallway10">hallway 10</option>
        <option value="conservatory">Conservatory</option>
        <option value="hallway11">hallway 11</option>
        <option value="ballroom">Ball Room</option>
        <option value="hallway12">hallway 12</option>
        <option value="kitchen">kitchen</option> -->		        
    </select>
    <select id="weapon" name="weapon" disabled>
        <option value="SelectAWeapon">-Select A Weapon-</option>
    </select>
    <select id="suspect" name="suspect" disabled>
        <option value="SelectASuspect">-Select A Suspect-</option>
    </select>
    <label>Cards in hand:</label>
    <select id="cardsInHand" name="cardsInHand" disabled>
        <option value="000">-Cards in Hand-</option>
    </select>
    <br />
    
    <button id="endTurnButton" onclick="endTurnEvent()"disabled>End Turn</button>
 </div> 
 
  <div id="container">
    <h1>ClueLess</h1>
    <br>
    <button id="leaveGame">Leave Game</button>
  </div>

  <script type="text/javascript">
  $(document).ready(function() {
    poll();
    // Leave game
    $("#leaveGame").click(function() {
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
		drawPlayers();
   		setTimeout(function() {
        $.ajax({url: "${pageContext.request.contextPath}/v1",
          type: "GET",
          success: function(response){
            // TODO: Add response handling
            var response = response;
            var whoseTurn = response.whoseTurn;
            var playerName = $.cookie("playerName");
            if (whoseTurn == playerName) {
				var movableLocations = response.players[playerName].movableLocations;
    	    	$.each(movableLocations, function(index, value) {
        	    	$("#location").append('<option value="'+value+'">'+value+'</option>');
            	});
             	document.getElementById("endTurnButton").disabled = false;
             	document.getElementById("move").disabled = false;
             	document.getElementById("suggest").disabled = false;
             	document.getElementById("accuse").disabled = false;
             	document.getElementById("location").disabled = false;
             	document.getElementById("weapon").disabled = false;
             	document.getElementById("suspect").disabled = false;
            }
            else
            	{
                document.getElementById("endTurnButton").disabled = true;
                document.getElementById("move").disabled = true;
                document.getElementById("suggest").disabled = true;
                document.getElementById("accuse").disabled = true;
                document.getElementById("location").disabled = true;
                document.getElementById("weapon").disabled = true;
                document.getElementById("suspect").disabled = true;
            	}
          }, 
          dataType: "json", 
          complete: poll, 
          timeout: 30000 
        });
      }, 1000);
    };
  
    function clearBoard() {
    	var canvases =new Array(); 
    	canvases[0]="hallway1";       
    	canvases[1]="hallway2";
    	canvases[2]="hallway3";
    	canvases[3]="hallway4";       
    	canvases[4]="hallway5";
    	canvases[5]="hallway6";
    	canvases[6]="hallway7";
    	canvases[7]="hallway8";
    	canvases[8]="hallway9";
    	canvases[9]="hallway10";
    	canvases[10]="hallway11";
    	canvases[11]="hallway12";
    	canvases[12]="tokenNotInPlay";
    	canvases[13]="study";
    	canvases[14]="hall";
    	canvases[15]="lounge";
    	canvases[16]="library";
    	canvases[17]="billardRoom";
    	canvases[18]="diningRoom";
    	canvases[19]="conservatory";
    	canvases[20]="ballroom";
    	canvases[21]="kitchen";
    	
    	$.each(canvases, function(index,value){
            var c=document.getElementById(value);
        	var ctx=c.getContext("2d");
        	ctx.clearRect(0,0,100,96);
    	});
    };
    
    function drawPlayers() {
        $.ajax({url: "${pageContext.request.contextPath}/v1",
            type: "GET",
            success: function(response){
            	$.each(response["players"], function (index, value) {
                    var whichSuspect = value.suspect;  //returns character name
                    
                    if (value.location === null){
                    	var whichRooms = "tokenNotInPlay";
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
            }, 
            dataType: "json", 
            timeout: 30000 
          });
        };
        
  });
  
  function endTurnEvent() {
      if (document.getElementById('move').checked) {
		  alert("I am here");
		  var validLocation = false;
		  var e = document.getElementById("location");
		  var strUser = e.options[e.selectedIndex].value;
		  
		$.ajax({type: "POST",
			url: "${pageContext.request.contextPath}/v1",
			data: "action=move" + "&location=" + strUser, // for a move action=move&location=hallway5
			success: function(response) {
				alert("You moved to " + strUser);
			},
			dataType: "json"
		});
          
	      $.ajax({type: "POST",
	          url: "${pageContext.request.contextPath}/v1",
	          data: "action=endTurn",
	          success: function(response) { 
				alert("end turn");
	          },
	          dataType: "json"
	        });
		   
	  } else if (document.getElementById('suggest').checked){
		  alert("suggest selected");
		  poll();
      } else if (document.getElementById('accuse').checked){
		  alert("accuse selected");
		  poll();
      } else{
		  alert("You can not end your turn without making a move");
		  poll();
      }
	  
	  //lock the boxes if the move is finished
      document.getElementById("endTurn").disabled = true;
      document.getElementById("move").disabled = true;
      document.getElementById("suggest").disabled = true;
      document.getElementById("accuse").disabled = true;
      document.getElementById("location").disabled = true;
      document.getElementById("weapon").disabled = true;
      document.getElementById("suspect").disabled = true;
      //reset radio buttons
      //post end turn
      poll();
  }

  

  </script>  
  </body>
</html>