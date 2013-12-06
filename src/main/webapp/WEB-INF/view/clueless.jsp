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
    <button id="move" onclick="moveEvent()" disabled>Move to location:</button>
        <select id="location" name="location" disabled>
        <option value="aaa">-Select a Location-</option>
		<!--         
		<option value="study">Study</option>
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
    
    <select id="suspect" name="suspect" disabled>
        <option value="aaa">-Select a Suspect-</option>   
		<option value="Miss Scarlet">Miss Scarlet</option>
        <option value="Colonel Mustard">Colonel Mustard</option>
        <option value="Mrs. White">Mrs. White</option>
        <option value="Mr. Green">Mr. Green</option>
        <option value="Mrs. Peacock">Mrs. Peacock"</option>
        <option value="Professor Plum">Professor Plum</option>    
    </select>
    
    <select id="weapon" name="weapon" disabled>
        <option value="aaa">-Select a Weapon-</option>   
		<option value="Rope">Rope</option>
        <option value="Lead Pipe">Lead Pipe</option>
        <option value="Knife">Knife</option>
        <option value="Wrench">Wrench</option>
        <option value="Candlestick">Candlestick</option>
        <option value="Revolver">Revolver</option>    
    </select>
    
    
    <button id="suggest" onclick="suggestEvent()" disabled>Suggest</button>
    <button id="accuse" onclick="accuseEvent()" disabled>Accuse</button>
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
  
  function moveEvent(){
	  var e = document.getElementById("location");
	  var strUser = e.options[e.selectedIndex].value;
	  
	$.ajax({type: "POST",
		url: "${pageContext.request.contextPath}/v1",
		data: "action=move" + "&location=" + strUser, // for a move action=move&location=hallway5
		success: function(response) {
			alert(response.suspect + " moved to " + strUser);
		},
		dataType: "json"
	});
  }
  
  function accuseEvent(){
	  
  }
  
  function suugestEvent(){
	  
  }
  
  function endTurnEvent() {
	  $.ajax({type: "POST",
	  		url: "${pageContext.request.contextPath}/v1",
	    	data: "action=endTurn",
	    	//success: function(response) {},  //do we need a success parameter?
	    	dataType: "json"
	    });
	  
	  //lock the boxes if the turn is finished
      document.getElementById("endTurn").disabled = true;
      document.getElementById("move").disabled = true;
      document.getElementById("suggest").disabled = true;
      document.getElementById("accuse").disabled = true;
      document.getElementById("location").disabled = true;
      document.getElementById("weapon").disabled = true;
      document.getElementById("suspect").disabled = true;
      //reset radio buttons
      poll();
  }

  

  </script>  
  </body>
</html>