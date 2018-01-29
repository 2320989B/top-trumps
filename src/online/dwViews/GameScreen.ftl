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
      <link href="./css/main.css" rel="stylesheet" />
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
  </head>
    <div class="row justify-content-md-center" id="titlebar">
      <h1>Top Trumps</h1>
    </div>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
      
  <div class="container">
    <div class="row justify-content-sm-center">
        
          <div class="col-sm" id="Player 1">
            
                <h2>Player 1</h2>
              
              
              <h2>Sabre</h2>
            
                <img src="ship.png" />

              <p id="p1_cat1_name">Size</p>
             <p id="p1_cat1_value">20</p>
              <p id="p1_cat2_name">Speed</p>
            <p id="p1_cat2_value">20</p>
              <p id="p1_cat3_name">Range</p>
            <p id="p1_cat3_value">20</p>
              <p id="p1_cat4_name">Firepower</p>
            <p id="p1_cat4_value">20</p>
              <p id="p1_cat5_name">Cargo</p>
            <p id="p1_cat5_value">20</p>
          </div>
          <div class="col-sm" id="AI 1">
                <h2>AI 1</h2>
              
              
                <h2>Sabre</h2>
              
            
            
                <img src="ship.png" />
    
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
        
          </div>
        
        <div class="col-sm" id="AI 2">
                <h2>AI 1</h2>
              
              
                <h2>Sabre</h2>
              
            
            
                <img src="ship.png" />
    
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
        
          </div>
         
    </div>
    <div class="row" id="menu">
      MENU ITEMS
        <p id="gameNumber"></p>
        <p id="cat1"></p>
    </div>
  </div>
		
		<script type="text/javascript">
            //set a global variable which holds game number then update the HTML element with id "gameNumber" to show this
		    var gameNumber = "10";
            document.getElementById("gameNumber").innerHTML = "Game Number: " + gameNumber;
            
            //set a player to be invisble if they are out of the game?
            document.getElementById("AI 2").style.visibility = "hidden";
            
            //update the names of all categories
            category1 = "SIZE";
            document.getElementById("p1_cat1_name").innerHTML = category1;
            
            //Update a player's category value
            document.getElementById("p1_cat1_value").innerHTML = "100";
            
            //get the value of a specific category and display it next to menu items
            var x = document.getElementById("p1_cat1_value").innerHTML;
            document.getElementById("cat1").innerHTML = x;
            
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");
                startNewGame();
                
                
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
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            
            
            
            //When you start a new game you will want some important information:
            // Number of AIPlayers
            // 
            
            
            
            
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
					alert(responseText);
                    
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
            
            //function getActivePlayer() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				//var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				//if (!xhr) {
  					//alert("CORS not supported");
				//}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				//xhr.onload = function(e) {
 					//var responseText = xhr.response; // the text of the response
					//alert(responseText);
                    //document.getElementById("cat1").innerHTML = responseText;
				//};
				
				// We have done everything we need to prepare the CORS request, so send it
				//xhr.send();		
			//}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>