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

/* Pavlos: add a side menu*/
nav {
    float: left;
    min-width: 360px;
    max-width: 360px;
    margin: 0;
    padding: 1em;
}

nav ul {
    list-style-type: none;
    padding: 0;
}

nav ul a {
    text-decoration: none;
}


</style>
  	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
  </head>
    <div class="row justify-content-md-center" id="titlebar">
      <h1>Top Trumps</h1>
    </div>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    
    	<nav>
			<ul>
				<li><p id="currentRound"></p></li>
				
				<li><button id="categoryButton" onclick="categorySelection()">Category Selection</button></li>
				<li><p id="message">Hello</p></li>
				<li><p id="p1_wins"></p></li>
				<li><p id="ai1_wins"></p></li>
				<li><p id="ai2_wins"></p></li>
				<li><p id="ai3_wins"></p></li>
				<li><p id="ai4_wins"></p></li>
				<li><p id="ai5_wins"></p></li>
				<li><p id="num_draws"></p></li>
				<li><p id="db_message"></p></li>
			
		<!--		<li><button type="button" class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#myModal">Select Difficulty</button></li><br />
				<li><button id="beginButton" onclick="getStuff()" class="btn btn-default btn-block btn-lg" >Begin Round</button></li><br />
  			<li><button id="roundWinnerButton" onclick="showWinner()" class="btn btn-default btn-block btn-lg">Show Winner</button></li><br />
  			<li><button id="NewRoundButton" onclick="setUpNewRound()" class="btn btn-default btn-block btn-lg">Set Up New Round</button></li><br />
				<li><h3 id="roundNumber">##</h3></li><br />
				<li><h3 id="whoIsActive">Who is Active?</h3></li><br />
				<li><h3 id="message">Hello</h3></li><br />
				<li><h3 id="difficultyLevel">level</h3></li><br />   -->
			</ul>
		</nav>

      
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
                <p id="p1_cards_left"></p>
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
                <p id="ai1_cards_left"></p>
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
                <p id="ai2_cards_left"></p>
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
                <p id="ai3_cards_left"></p>
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
                <p id="ai4_cards_left"></p>
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
        
        <p id="startNumberAIPlayers"></p>
        <p id="isHuman"></p>
        <p id="playerNames"></p>
        <p id="currentActivePlayer"></p>
        <p id="topCardTitles"></p>
        <p id="numOfCommunalCards"></p>
        <p id="p1NumberOfCardsLeft"></p>
        <p id="p1CategoryValues"></p>
        <p id="ai1NumberOfCardsLeft"></p>
        <p id="ai1CategoryValues"></p>
        <p id="ai2NumberOfCardsLeft"></p>
        <p id="ai2CategoryValues"</p>
        <p id="ai3NumberOfCardsLeft"</p>
        <p id="ai3CategoryValues"</p>
        <p id="ai4NumberOfCardsLeft"</p>
        <p id="ai4CategoryValues"</p>
        <div>
        	
        	<!-- <button id="beginButton" onclick="beginRound()">Begin Round</button> -->
        	
        	<!-- <button id="controlButton" onclick="compareCards()">Compare Cards</button> -->
        	<!-- <button id="roundWinnerButton" onclick="showWinner()">Show Winner</button> -->
        	<!-- <button id="NewRoundButton" onclick="setUpNewRound()">Set Up New Round</button> -->
        </div>
  </div>
		
		<script type="text/javascript">
			//set a global variable which holds game number then update the HTML element with id "gameNumber" to show this
			var gameIndex = 0;
            var categories = "";
            var totalPlayers = "";
            var numPlayersLeft = 0;
            var activePlayer = "";
            var activeCategory = "";
            var category1 = "";
            var category2 = "";
            var category3 = "";
            var category4 = "";
            var category5 = "";
            var roundWinner = "";
            var numOfCommunalCards = 0;
            var isHuman = "";
            
            var wins = [0, 0, 0, 0, 0, 0];
            var draws = 0;
			//document.getElementById("AI 2").style.visibility = "hidden";
			
			
			
			
			
			// Method that is called on page load
			function initalize() {
				
				startNewGame();
            }
            
            
            
			//---------------HELPER METHODS--------------------------------
			
			
            // -------------------------------------------------------------------------------------------------------------------
			// Call the changeImage() helper method with the id name of the tag to be updated and the title of the player's top card
			// Assumes the images are located at: http://dcs.gla.ac.uk/~richardm/TopTrumps/" + imgName +".jpg
			// Called by the getHumanTopCardTitle() API method.
			// Called by the getTopCardTitles() API method.
			// -------------------------------------------------------------------------------------------------------------------
            
            function changeImage(id, imgName){
                document.getElementById(id).src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + imgName +".jpg"
            }
            
            
            // -------------------------------------------------------------------------------------------------------------------
			// Call the updateCategories() helper method with the id name of the tag to be updated, with category name and value.
			// Called by the getHumanTopCardCategories() API method.
			// Called by the getTopCards() API method.
			// -------------------------------------------------------------------------------------------------------------------
            
            function updateCategories(id, key, value){
						document.getElementById(id).innerHTML = key + ": " + value;
			}
			
			
			
			// --------------------------------------------------------------------------------------------------------------------------------------
			// Call the updateButton() helper method with the id name of the button to be updated, the function to be assigned to the onlcick event,
			// the parameter to be passed to the function and the text to be displayed on the button.
			// Changes the function which is called when the button is clicked and the text which is displayed on the button.
			// Called by the newRound() API method.
			// Called by the getTopCards() API method.
			// Called by the computeResult() API method
			// -------------------------------------------------------------------------------------------------------------------------------------
			
			function updateButton(id, funcName, parameter, text) {
					document.getElementById(id).innerHTML = text;
					document.getElementById(id).onclick = function(){funcName(parameter)};
			}
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the categorySelection() method to initiate choosing of categories.
			// If the activePlayer is an AI player, call the selectCategory() API method to obtain AI's choice from Game.
			// If the activePlayer is Player 1, ask Player 1 to choose a category. Also adds EventListener's to <p>
			// tags on Player1's card by using the userCategoriesActivate() helper method.
			// Called by pressing button which has onlick="categorySelection().
			// -----------------------------------------------------------------------------------------------
                
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
			
			
			// --------------------------------------------------------------------------------------------------------------------------------------
			// Call the userCategoriesActivate() helper method with the id name of the tag to be updated and the category to be assigned.
			// Adds an event listener to a category on the user's card and calls the selectCategoryHuman() API method when clicked, passing
			// in a paramter contaning the reference to the specific Game for this tab and the category selected by Player 1.
			// Called by the categorySelection() helper method.
			// -------------------------------------------------------------------------------------------------------------------------------------
			
			function userCategoriesActivate(id, category) {
				
				document.getElementById(id).addEventListener("click", function(){
					activeCategory = category;
					var gameIndexCat = "" + gameIndex + "xxxxx" + category;
    				selectCategoryHuman(gameIndexCat);
				});
				
			}
			
			
			
			function updateButtonComplete(link) {
        		window.location=link;
   			}
			
			
			
			//-----------------------------------------------------------------------------------------------------------
			
             //<!-- ------------------ I THINK THESE METHODS ARE NO LONGER REQUIRED ------------------------------ -->
			
			 
			//function chooseCategory(category){
				//activeCategory = category;
				//document.getElementById("message").innerHTML = "Player 1 chose " + activeCategory;
			//}
			
			
			//function compareCards(){
				//if (activePlayer != "Player 1"){
					//selectCategory(gameIndex);
				//}
				//else {
					
					//var gameIndexCat = "" + gameIndex + "xxxxx" + activeCategory;
					//alert(gameIndexCat);
					//selectCategoryHuman(gameIndexCat);
				//}
				
			//}
			
			
			
			
			//function showWinner() {
				//computeResult(gameIndex);
				
			//}
			
			
				
			//function setUpNewRound() {
				//newRound(gameIndex);
				//getTopCardTitles(gameIndex);
				//getTopCards(gameIndex);
			//} 
			
			//-------------------------------------------------------------------------------------------- -->
			
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the startNewGame() API method to get the game index reference for this particular game tab.
			// Called by the initalise() method referenced within the <body> tag.
			// -----------------------------------------------------------------------------------------------
			
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
					getNumCommunalCards(gameIndex);
					getNumOfCardsLeft(gameIndex);
					getCategories(gameIndex);	
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getNumCommunalCards() API method with the gameIndex number for this particular tab.
			// Receives the number of cards currently in the communal pile and updates this number on screen.
			// Called by the startNewGame() API method.
			// Called by computeResult() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getNumCommunalCards REST method from TopTrumpsRESTAPI
			function getNumCommunalCards(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumCommunalCards?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					numOfCommunalCards = JSON.parse(responseText);
                    document.getElementById("numOfCommunalCards").innerHTML = "There are " + numOfCommunalCards 
                    + " cards in the communal pile.";
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getNumOfCardsLeft() API method with the gameIndex number for this particular tab.
			// Receives the number of cards left for each player and updates these numbers on screen.
			// Called by the startNewGame() API method.
			// Called by computeResult() API method.
			// -----------------------------------------------------------------------------------------------
			
			// This calls the getNumOfCardsLeft REST method from TopTrumpsRESTAPI
			function getNumOfCardsLeft(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumOfCardsLeft?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var numOfCardsLeft = JSON.parse(responseText);
					var playerNames = Object.keys(numOfCardsLeft);
					
					var player1Found = false;
					for (i = 0; i < playerNames.length; i++) {
						if (playerNames[i] == "Player 1") {
							document.getElementById("p1_cards_left").innerHTML = numOfCardsLeft[playerNames[i]];
							player1Found = true;
							
						}
					}
					if (player1Found == false) {
						document.getElementById("p1_cards_left").innerHTML = "0";
					}
					
					for (i = 1; i < 5; i++) {
					    var aiPlayerFound = false;
						for (j = 0; j < playerNames.length; j++) {
							if (playerNames[j] == "AI " + i) {
								document.getElementById("ai" + i + "_cards_left").innerHTML = numOfCardsLeft[playerNames[j]];
								aiPlayerFound = true;
								
							}
						}
						if (aiPlayerFound == false) {
						    document.getElementById("ai" + i + "_cards_left").innerHTML = "0";
						}
					}
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// ------------------------------------------------------------------------------------------------------
			// Call the getCategories() API method with the gameIndex number for this particular tab.
			// Receives the category names for this deck of cards and updates global variables with this information.
			// Called by the startNewGame() API method.
			// ------------------------------------------------------------------------------------------------------
            
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the newRound() API method with the gameIndex number for this particular tab.
			// Begins a new round within the Game and updates the round number on screen.
			// Uses updateButton helper method to update button for category selection.
			// Called by the getCategories() API method.
			// Called by user pressing on screen button "Next Round" when round has completed.
			// -----------------------------------------------------------------------------------------------
            
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getPlayerNames() API method with the gameIndex number for this particular tab.
			// Receives the player Names of the players still in the game.
			// Called by the newRound() API method.
			// -----------------------------------------------------------------------------------------------
			
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
                    
                    if (round == 1) {
						numPlayersLeft = playerNames.length;
						document.getElementById("p1_wins").innerHTML = "Player 1 wins: 0";
						for (i = 1; i < numPlayersLeft; i++) {
							document.getElementById("ai" + i + "_wins").innerHTML = "AI " + i + " wins: 0";
						}
						document.getElementById("num_draws").innerHTML = "Number of draws: 0";
					}
                    
					//isHuman(gameIndex); ----------Not sure but this seems to be causing issues on the 2nd round
					if (document.getElementById("p1_cards_left").innerHTML != "0") {
						getHumanTopCardTitle(gameIndex);
					}
					else {
						getActivePlayer(gameIndex);
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// CURRENTLY NOT USED - NEEDS CHECKED
			// -------------------------------------------------------------------------------------------------------------------------------------------------------
			// Call the isHuman() API method with the gameIndex number for this particular tab.
			// Returns whether Player 1 is still in the game.
			// Called by the getPlayerNames() API method.
			// -----------------------------------------------------------------------------------------------
			
			
				
			// This calls the isHuman REST method from TopTrumpsRESTAPI
			//function isHuman(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				//var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isHuman?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				//if (!xhr) {
					//alert("CORS not supported");
				//}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				//xhr.onload = function(e) {
					//var responseText = xhr.response; // the text of the response
					//alert("isHuman");
					//isHuman = JSON.parse(responseText);
                    //document.getElementById("isHuman").innerHTML = "Human Player Still in Game?: " + isHuman;
                    //if (isHuman == "true"){
                    	//getHumanTopCardTitle(gameIndex);
                   // }
				//};
				
				// We have done everything we need to prepare the CORS request, so send it
				// xhr.send();		
			//}
			
			// --------------SHOULD POSSIBLY BE CALLED FFROM THE isHuman METHOD------------------------------------------------------------------------------------------
			// -----------------------------------------------------------------------------------------------------------
			// Call the getHumanTopCardTitle() API method with the gameIndex number for this particular tab.
			// Receives the title of Player 1's top card and calls the changeImage helper method to update picture on card.
			// Called by the getPlayerNames() API method.
			// -----------------------------------------------------------------------------------------------------------
			
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getHumanTopCardCategories() API method with the gameIndex number for this particular tab.
			// Receives the category detials for Player 1's top card and updates the card on screen.
			// Called by the getHumanTopCardTitle() API method.
			// -----------------------------------------------------------------------------------------------
			
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getActivePlayer() API method with the gameIndex number for this particular tab.
			// Receives the activePlayer Name and updates this value to the screen.
			// Called by the getHumanTopCardCategories() API method.
			// -----------------------------------------------------------------------------------------------
            
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the selectCategory() API method with the gameIndex number for this particular tab.
			// Receives the category picked by the AI Player for the current round and updates the screen.
			// Called when user presses button which says Category Selection if activePlayer is AI Player.
			// -----------------------------------------------------------------------------------------------
			
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the getTopCardTitles() API method with the gameIndex number for this particular tab.
			// Receives the titles for each AI Player's top card and updates their cards on screen.
			// Uses the changeImage() helper method to update pictures of cards.
			// Called by the selectCategory() API method.
			// -----------------------------------------------------------------------------------------------
			
			
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
					if (document.getElementById("p1_cards_left").innerHTML != "0") { 
						var topCardTitleIndex = 1;
					}
					else {
						var topCardTitleIndex = 0;
					}
					for(i = 1; i < 5; i++) {
						if (document.getElementById("ai" + i + "_cards_left").innerHTML != "0") {
							document.getElementById("ai" + i + "_card_name").innerHTML = topCardTitles[topCardTitleIndex];
							changeImage("ai" + i + "_shipImg", topCardTitles[topCardTitleIndex]);
							topCardTitleIndex += 1;
						}
					}	
					getTopCards(gameIndex);    
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// -----------------------------------------------------------------------------------------------
			// Call the getTopCards() API method with the gameIndex number for this particular tab.
			// Receives the details of each AI Player's cards and updates these details on screen.
			// Uses the updateButton method to prepare button to "Show Winner".
			// Called by the getTopCardTitles() API method.
			// -----------------------------------------------------------------------------------------------
			
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
					
					if (document.getElementById("p1_cards_left").innerHTML != "0") { 
						var topCardsIndex = 1;
					}
					else {
						var topCardsIndex = 0;
					}
					for (i = 1; i < 5; i++) {
						if (document.getElementById("ai" + i + "_cards_left").innerHTML != "0") {
							for (j = 0; j < topCardsCategories.length; j++) {
								updateCategories("ai" + i +"_cat" + (j + 1) + "_name", topCardsCategories[j], 
								topCards[topCardsIndex][topCardsCategories[j]]);
								
							}
							topCardsIndex += 1;
						}
					}
					//update categoryButton to "Show Winner", computeResult()
					updateButton("categoryButton", computeResult, gameIndex, "Show Winner");
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            
            // -----------------------------------------------------------------------------------------------
			// Call the selectCategoryHuman() API method with the gameIndex number for this particular tab.
			// Also passes Category chosen as part of the string, both values separated by String "xxxxx".
			// Receives confirmation that game has processed human's ctageory choice from Game.
			// Called when user clicks on a category within their card, assuming they are the active player.
			// -----------------------------------------------------------------------------------------------
			
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
			
			
			// -----------------------------------------------------------------------------------------------
			// Call the computeResult() API method with the gameIndex number for this particular tab.
			// Receives the Round Winner Name or that the round was a draw and updates screen accordingly.
			// uses updateBButton helper method to prepare button for "Next Round".
			// Called when user presses button displaying "Show Winner".
			// -----------------------------------------------------------------------------------------------
			
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
						draws += 1;
						document.getElementById("message").innerHTML = activePlayer + " chose " + activeCategory + " and it was a draw.";
						document.getElementById("num_draws").innerHTML = "Number of draws: " + draws;
					}
					else {
						if (roundWinner == "Player 1") {
							wins[0] += 1;
							document.getElementById("p1_wins").innerHTML = "Player 1 wins: " + wins[0];
						}
						else {
							for (i = 1; i < numPlayersLeft; i++) {
								if (roundWinner == ("AI " + i)) {
									wins[i] += 1;
									document.getElementById("ai" + i + "_wins").innerHTML = "AI " + i + " wins: " + wins[i];
								}
							}
						}
						document.getElementById("message").innerHTML = roundWinner + "won!";
						activePlayer = roundWinner;
					}
					
					getPlayersLeft(gameIndex);
					
				};
				
				
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			// This calls the getPlayersLeft REST method from TopTrumpsRESTAPI
			function getPlayersLeft(gameIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPlayersLeft?gameIndex="+gameIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var playersLeft = JSON.parse(responseText);
                    if (playersLeft.length < 2) {
                        if (playersLeft == "Player 1"){
                            document.getElementById("p1_cards_left").innerHTML = "WINNER";
				            updateButton("categoryButton", updateButtonComplete, 'http://localhost:7777/toptrumps/', "Return to Selection");
				            updateDB(gameIndex);
                        }
                        else {
                            for (i = 1; i < 5; i++) {
				                if (playersLeft == "AI " + i) {
								
								
									document.getElementById("ai" + i + "_cards_left").innerHTML = "WINNER";
									updateButton("categoryButton", updateButtonComplete, 'http://localhost:7777/toptrumps/', "Return to Selection");
									updateDB(gameIndex);
								}
                            }
                        }
                    }
                    else {
                        getNumCommunalCards(gameIndex);
					    getNumOfCardsLeft(gameIndex);
					    //update categoryButton to "Show Winner", computeResult()
					    updateButton("categoryButton", newRound, gameIndex, "Next Round");
                    }
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			
			function updateDB(gameIndex) {
			
				//First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateDB?gameIndex="+gameIndex); // Request type and URL+parameters
				
				//Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				//CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					var message = JSON.parse(responseText);
					
					document.getElementById("db_message").innerHTML = message;
					
				};
				
				
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			

		</script>
		
		</body>
</html>