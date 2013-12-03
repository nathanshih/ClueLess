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
<!-- <table cellspacing="0" cellpadding="0" border="0" background="http://i.imgur.com/mGJ85IX.jpg" class="inlineTable">

</table> -->
<%-- 
<canvas id="boardCanvas" width="725" height="646" style="background: url(http://i.imgur.com/xFkG89v.png) no-repeat center center;"></canvas>
 --%>
<div id="playerInput">
    <input type="radio" name="TurnActionsGroup" id="move"  value="move" />Move
    <input type="radio" name="TurnActionsGroup" id="suggest" value="suggest" /> Suggest
    <input type="radio" name="TurnActionsGroup" id="accuse" value="accuse" /> Accuse<br/>

    <label>Location:</label>
    <select id="location" name="location">
        <option value="000">-Select Location-</option>
    </select>
    <label>Cards in hand:</label>
    <select id="cardsInHand" name="cardsInHand">
        <option value="000">-Cards in Hand-</option>
    </select>
    <br />

    <button id="endTurn">End Turn</button>
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
	  alert ("Will call draw players");
   	  drawPlayers();
      setTimeout(function() {
        $.ajax({url: "${pageContext.request.contextPath}/v1",
          type: "GET",
          success: function(response){
            // TODO: Add response handling
            var whoseTurn = response.whoseTurn;
            var playerName = $.cookie("playerName");
            if (whoseTurn == playerName) {
             //alert( "poll determined that it's " + playerName + " turn" );
             //turn(playerName);
             $('input[type=radio]').click( function() {
      			var action = $("input:radio[name=TurnActionsGroup]:checked").val();
      			alert( action);
     		});
            }
          }, 
          dataType: "json", 
          complete: poll, 
          timeout: 30000 
        });
      }, 1000);
    };
   
    function drawPlayers() {

/*     	var c=document.getElementById("study");
    	var ctx=c.getContext("2d");
    	ctx.fillStyle="#FF0000";
    	ctx.fillRect(0,0,25,25); */

    	alert("draw called");
        $.ajax({url: "${pageContext.request.contextPath}/v1",
            type: "GET",
            success: function(response){
            	$.each(response["players"], function (index, value) {
                    var whichSuspect = value.suspect;  //returns character name
                    //var whichRooms = value.location;   //returns null at present
                    alert( whichSuspect ); 
                	
                	if (whichSuspect == "Colonel Mustard"){
                		whichRooms = "study";
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                    	ctx.fillStyle="#FFCC11";
                	}
                	else if (whichSuspect == "Miss Scarlet"){
                		whichRooms = "lounge"
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                		ctx.fillStyle= "#8C1717";
                	}
                	else if (whichSuspect == "Mrs. White"){
                		whichRooms = "ballroom"
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                		ctx.fillStyle= "#EEE9E9";
                	}
                	else if (whichSuspect == "Mr. Green"){
                		whichRooms = "hallway1"
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                		ctx.fillStyle= "#99CC32";
                	}
                	else if (whichSuspect == "Mrs. Peacock"){
                		whichRooms = "library"
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                		ctx.fillStyle= "#016795";
                	}
                	else if (whichSuspect == "Professor Plum"){ 
                		whichRooms = "hall";
                        var c=document.getElementById(whichRooms);
                    	var ctx=c.getContext("2d");
                		ctx.fillStyle= "8E4585";
                		}
                	
                	ctx.fillRect(0,0,25,25);
            	});
            }, 
            dataType: "json", 
            complete: poll, 
            timeout: 30000 
          });
    };
  
  
  });
  </script>  
  </body>
</html>