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
<!--         <option value="Study">Study</option>
        <option value="Hallway1">Hallway 1</option>
        <option value="Hall">hall</option>
        <option value="Hallway2">Hallway 2</option>
        <option value="Lounge">Lounge</option>
        <option value="Hallway3">Hallway 3</option>
        <option value="Hallway4">Hallway 4</option>
		<option value="Hallway5">Hallway 5</option>
        <option value="Library">library</option>
        <option value="Hallway6">Hallway 6</option>
        <option value="BillardRoom">Billard Room</option>
        <option value="Hallway7">Hallway 7</option>
        <option value="DiningRoom">Dining Room</option>
        <option value="Hallway8">Hallway 8</option>
        <option value="Hallway9">Hallway 9</option>
		<option value="Hallway10">Hallway 10</option>
        <option value="Conservatory">Conservatory</option>
        <option value="Hallway11">Hallway 11</option>
        <option value="Ballroom">Ball Room</option>
        <option value="Hallway12">Hallway 12</option>
        <option value="Kitchen">kitchen</option> -->		        
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
    	canvases[12]="tokenNotInPlay";
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
			data: "action=move" + "&location=" + strUser, // for a move action=move&location=Hallway5
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