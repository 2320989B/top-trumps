<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

      <!-- Custom styles for this template -->
<style>
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  background-color: #FCFFF5;
}

.container {
  width: 80%;
  padding-top: 50px;
}

h1 {
  color: #193441;
  font-size: 48;
  font-weight: 800;
  font-family: 'Montserrat', sans-serif;
}

#titlebar {
  padding: 10px 0px;
  margin: 0px;
  width:100%;
  background-color: #91AAB4;
  background-position: bottom;
    background-image: url("background.png");
  background-repeat: repeat-x;
}

.card {
  margin: 15px;
  max-width: 239px;
  padding: 0px;
  background-color: #91AAB4;
/*  min-height:340px;
*/  border-radius: 14px;
  border-style: solid;
  border-width: 4px;
  border-color: #91AAB4;
}

.cardheader {
  width:100%;
  margin:0px;
  padding:5px 10px 0px 10px;
  min-height: 40px;
  background-color: #91AAB4;
  border-radius: 14px 14px 0px 0px;
}

.cardheader h2 {
  font-size: 24;
  color: #495F67;
}

.shipinfo p {
  font-size: 18px;
  margin: 2px;
}

.shipimg img{
  width:100%;
  max-height: 100px;
}

.shipname {
  float: right;
  max-width:50%;
}
.playername {
  float: left;
  max-width:50%;
}

#active {
  color: #007F0E;
}

#menu {
  margin: 40px 0px 0px 5px;
}

#menubtn {
  min-height: 120px;
  max-width:60%;
  margin: 60px auto 60px auto;
  font-size: 44px;
  font-family: 'Montserrat', sans-serif;
}
</style>
  	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
  </head>
    <div class="row justify-content-md-center" id="titlebar">
      <h1>Top Trumps</h1>
    </div>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
      
  <div class="container">
    <div class="row justify-content-sm-center">
          <div class="col-sm card" id="Player 1">
            <div class="cardheader">
              <div class="playername">
                <h2>Player 1</h2>
              </div>
              <div class="shipname">
                <h2 id="p1_card_name">Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="p1_shipImg" src="" />
              </div>
                <p id="p1_cat1_name"></p>
                <p id="p1_cat2_name"></p>
                <p id="p1_cat3_name"></p>
                <p id="p1_cat4_name"></p>
                <p id="p1_cat5_name"></p>
              </div>
          </div>
          
          <div class="col-sm card" id="AI 1">
            <div class="cardheader">
              <div class="playername">
                <h2 >AI 1</h2>
              </div>
              <div class="shipname">
                <h2 id="ai1_card_name"></h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="ai1_shipImg" src="" />
              </div>
              	<p id="ai1_cat1_name"></p>
                <p id="ai1_cat2_name"></p>
                <p id="ai1_cat3_name"></p>
                <p id="ai1_cat4_name"></p>
                <p id="ai1_cat5_name"></p>
            </div>
          </div>
          <div class="col-sm card" id="AI 2">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 2</h2>
              </div>
              <div class="shipname">
                <h2 id="ai2_card_name"></h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="ai2_shipImg" src="" />
              </div>
              <p id="ai2_cat1_name"></p>
                <p id="ai2_cat2_name"></p>
                <p id="ai2_cat3_name"></p>
                <p id="ai2_cat4_name"></p>
                <p id="ai2_cat5_name"></p>
            </div>
          </div>
          <div class="col-sm card" id="AI 3">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 3</h2>
              </div>
              <div class="shipname">
                <h2 id="ai3_card_name"></h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="ai3_shipImg" src="" />
              </div>
              <p id="ai3_cat1_name"></p>
                <p id="ai3_cat2_name"></p>
                <p id="ai3_cat3_name"></p>
                <p id="ai3_cat4_name"></p>
                <p id="ai3_cat5_name"></p>
            </div>
          </div>
          <div class="col-sm card" id="AI 4">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 4</h2>
              </div>
              <div class="shipname">
                <h2 id="ai4_card_name"></h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img id="ai4_shipImg" src="" />
              </div>
              <p id="ai4_cat1_name"></p>
                <p id="ai4_cat2_name"></p>
                <p id="ai4_cat3_name"></p>
                <p id="ai4_cat4_name"></p>
                <p id="ai4_cat5_name"></p>
            </div>
          </div>
    </div>
	<div class="row" id="menu">
	  MENU ITEMS
		<p id="gameNumber"></p>
		<p id="cat1"></p>
	</div>
    <hr />
    <div>
        <p>To test API calls we should maybe output the values here for just now</p>
        <p id="gameIndex"></p>
        <p id="categories"></p>
        <p id="currentRound"></p>
        <p id="startNumberAIPlayers"></p>
        <p id="isHuman"></p>
        <p id="playerNames"></p>
        <p id="currentActivePlayer"></p>
        <p id="topCardTitles"></p>
        <p id="p1NumberOfCardsLeft"</p>
        <p id="p1CategoryValues"</p>
        <p id="ai1NumberOfCardsLeft"</p>
        <p id="ai1CategoryValues"</p>
        <p id="ai2NumberOfCardsLeft"</p>
        <p id="ai2CategoryValues"</p>
        <p id="ai3NumberOfCardsLeft"</p>
        <p id="ai3CategoryValues"</p>
        <p id="ai4NumberOfCardsLeft"</p>
        <p id="ai4CategoryValues"</p>
        <div>
        	<button id="categoryButton" onclick="categorySelection()">Category Selection</button>
        	<!-- <button id="beginButton" onclick="beginRound()">Begin Round</button> -->
        	<p id="message">Hello</p>
        	<!-- <button id="controlButton" onclick="compareCards()">Compare Cards</button> -->
        	<!-- <button id="roundWinnerButton" onclick="showWinner()">Show Winner</button> -->
        	<!-- <button id="NewRoundButton" onclick="setUpNewRound()">Set Up New Round</button> -->
        </div>
  </div>
		
		<script type="text/javascript">
			//set a global variable which holds game number then update the HTML element with id "gameNumber" to show this
			var gameIndex = 0;
            var categories = "";
            var numPlayersLeft = 5;
            var activePlayer = "";
            var activeCategory = "";
            var category1 = "";
            var category2 = "";
            var category3 = "";
            var category4 = "";
            var category5 = "";
            var roundWinner = "";
            
			//document.getElementById("AI 2").style.visibility = "hidden";
			
			
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				startNewGame();
            }
                
            function categorySelection() {
            	if (activePlayer != "Player 1") {
            		selectCategory(gameIndex);
            	}
            	else {
            		document.getElementById("message").innerHTML = "Player 1, please select a category on your card";
            		for (i = 0; i < 5; i++) {
            			userCategoriesActivate("p1_cat" + (i + 1) + "_name", categories[i]);
            		}
            	}
            }
			
			function chooseCategory(category){
				activeCategory = category;
				document.getElementById("message").innerHTML = "Player 1 chose " + activeCategory;
			}
			
			function compareCards(){
				if (activePlayer != "Player 1"){
					selectCategory(gameIndex);
				}
				else {
					
					var gameIndexCat = "" + gameIndex + "xxxxx" + activeCategory;
					alert(gameIndexCat);
					selectCategoryHuman(gameIndexCat);
				}
				
			}
			
			function showWinner() {
				computeResult(gameIndex);
				
			}
				
			function setUpNewRound() {
				newRound(gameIndex);
				getTopCardTitles(gameIndex);
				getTopCards(gameIndex);
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
				var xhr = new XMLHttpRequest();
				if ("withCredentials" in xhr) {

					// Check if the XMLHttpRequest object has a "withCredentials" property.
					// "withCredentials" only exists on XMLHTTPRequest2 objects.
					xhr.open(method, url, true);

				} else if (typeof XDomainRequest != "undefined") {

					// Otherwise, check if XDomainRequest.
					// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
					xhr = new XDomainRequest();
					xhr.open(method, url);

				 } else {

					// Otherwise, CORS is not supported by the browser.
					xhr = null;

				 }
				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
				
			
			
		//-------------------------------------------First Part of Game-----------------------------	
			
			
			
			function startNewGame() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startNewGame"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
                    gameIndex = JSON.parse(responseText);
					document.getElementById("gameIndex").innerHTML = "Game Number: " + gameIndex;
					getCategories(gameIndex);	
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            // This calls the getCategories REST method from TopTrumpsRESTAPI
			function getCategories(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCategories?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					categories = JSON.parse(responseText);
					categoryNames = Object.keys(categories); 
					category1 = categoryNames[0];
            		category2 = categoryNames[1];
            		category3 = categoryNames[2];
            		category4 = categoryNames[3];
            		category5 = categoryNames[4];
                    document.getElementById("categories").innerHTML = "Categories: " + category1 + ", " + category2 + ", " + category3 + ", " 
                    + category4 + ", " + category5;
                    categories = [category1, category2, category3, category4, category5];
                    newRound(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            // This calls the newRound REST method from TopTrumpsRESTAPI
			function newRound(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newRound?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					round = JSON.parse(responseText);
                    document.getElementById("currentRound").innerHTML = "Current Round: " + round 
                    + " - Players have drawn their cards.";
                    getPlayerNames(gameIndex);
                    updateButton("categoryButton", categorySelection, gameIndex, "Category Selection");
                    document.getElementById("gameIndex").innerHTML = "Game Number: " + gameIndex;
                    document.getElementById("message").innerHTML = "Players have drawn their cards";
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the getPlayerNames REST method from TopTrumpsRESTAPI
			function getPlayerNames(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayerNames?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					playerNames = JSON.parse(responseText);
                    document.getElementById("playerNames").innerHTML = "Players still in game: " + playerNames;
                    
					//isHuman(gameIndex); ----------Not sure but this seems to be causing issues on the 2nd round
					getHumanTopCardTitle(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the isHuman REST method from TopTrumpsRESTAPI
			function isHuman(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isHuman?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					alert("isHuman");
					isHuman = JSON.parse(responseText);
                    document.getElementById("isHuman").innerHTML = "Human Player Still in Game?: " + isHuman;
                    if (isHuman == true){
                    	getHumanTopCardTitle(gameIndex);
                    }
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the getHumanTopCardTitle REST method from TopTrumpsRESTAPI
			// This will return the getHumanTopCardTitle
			function getHumanTopCardTitle(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanTopCardTitle?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var humanTopCardTitle = JSON.parse(responseText);
					document.getElementById("p1_card_name").innerHTML = humanTopCardTitle;
					changeImage("p1_shipImg", humanTopCardTitle);
					getHumanTopCardCategories(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// This calls the getHumanTopCardCategories REST method from TopTrumpsRESTAPI
			// This will return the getHumanTopCardCategories
			function getHumanTopCardCategories(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanTopCardCategories?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var humanTopCardCategories = JSON.parse(responseText);
					
					var categoryNames = Object.keys(humanTopCardCategories);
					for (i = 0; i < categoryNames.length; i++) {
						updateCategories("p1_cat" + (i + 1) + "_name", categoryNames[i], 
						humanTopCardCategories[categoryNames[i]]);
				    }  
				    getActivePlayer(gameIndex);              
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            // This calls the getActivePlayer REST method from TopTrumpsRESTAPI
			function getActivePlayer(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activePlayer = JSON.parse(responseText);
                    document.getElementById("currentActivePlayer").innerHTML = "Current ActivePlayer: " + activePlayer;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
             
            
        //---------------------------------------Second Part of Game - Category Selection--------------    
			
			
			// This asks Game to select a category for the active AI Player
            // This calls the selectCategory REST method from TopTrumpsRESTAPI
			function selectCategory(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategory?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activeCategory = JSON.parse(responseText);
					document.getElementById("message").innerHTML = activePlayer + " selected " + activeCategory;
					getTopCardTitles(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
            // This calls the getTopCardTitles REST method from TopTrumpsRESTAPI
			function getTopCardTitles(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTopCardTitles?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					topCardTitles = JSON.parse(responseText);
		
					for(i = 1; i < 5; i++) {
						document.getElementById("ai" + i + "_card_name").innerHTML = topCardTitles[i];
						changeImage("ai" + i + "_shipImg", topCardTitles[i]);
						
					}
					getTopCards(gameIndex);    
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the getTopCards REST method from TopTrumpsRESTAPI
			function getTopCards(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTopCards?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					topCards = JSON.parse(responseText);
					topCardsCategories = Object.keys(topCards[0]);
					
					for (i = 1; i < topCards.length; i++) {
						for (j = 0; j < topCardsCategories.length; j++) {
							updateCategories("ai" + i +"_cat" + (j + 1) + "_name", topCardsCategories[j], 
							topCards[i][topCardsCategories[j]]);
						}
					
					}
					//update categoryButton to "Show Winner", computeResult()
					updateButton("categoryButton", computeResult, gameIndex, "Show Winner");
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            
			
			// If a human is the activePlayer, this method passes their choice to the Game
			// This is not perfect as I pass both gameIndex and the Category choice as one parameter
			// This calls the selectCategoryHuman REST method from TopTrumpsRESTAPI
			function selectCategoryHuman(gameIndexCat) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategoryHuman?gameIndexCat="+gameIndexCat); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					activeCategory = JSON.parse(responseText);
					document.getElementById("message").innerHTML = activePlayer + " selected " + activeCategory;
					getTopCardTitles(gameIndex);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the computeResult REST method from TopTrumpsRESTAPI
			// This will compareCards and end the round, transferring cards
			// This will return the roundWinner
			function computeResult(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/computeResult?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					roundWinner = JSON.parse(responseText);
					if (roundWinner == null){
						document.getElementById("message").innerHTML = activePlayer + " chose " + activeCategory + " and it was a draw.";
					}
					else {
						document.getElementById("message").innerHTML = roundWinner + "won!";
						activePlayer = roundWinner;
					}
					
					//update categoryButton to "Show Winner", computeResult()
					updateButton("categoryButton", newRound, gameIndex, "Next Round");
					
				};
				
				
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			//---------------HELPER METHODS--------------------------------
			
			
            //small example of how to change img src
            
            function changeImage(id, imgName){
                document.getElementById(id).src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + imgName +".jpg"
            }
            
            //small example of how to update card categories
            
            function updateCategories(id, key, value){
						document.getElementById(id).innerHTML = key + ": " + value;
			}
			
			//update a specific button and the function it points to
			
			function updateButton(id, funcName, parameter, text) {
					document.getElementById(id).innerHTML = text;
					document.getElementById(id).onclick = function(){funcName(parameter)};
			}
			
			//make the categories on Player 1's card eventlisteners
			
			function userCategoriesActivate(id, category) {
				
				document.getElementById(id).addEventListener("click", function(){
					activeCategory = category;
					var gameIndexCat = "" + gameIndex + "xxxxx" + category;
    				selectCategoryHuman(gameIndexCat);
				});
				
			}

		</script>
		
		</body>
</html>