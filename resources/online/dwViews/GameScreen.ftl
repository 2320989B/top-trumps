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
      <link href="main.css" rel="stylesheet">
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
  </head>
    <div class="row justify-content-md-center" id="titlebar">
      <h1>Top Trumps</h1>
    </div>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
      
  <div class="container">
    <div class="row justify-content-sm-center">
          <div class="col-sm" id="card">
            <div class="cardheader">
              <div class="playername">
                <h2>Player 1</h2>
              </div>
              <div class="shipname">
                <h2>Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img src="ship.png" />
              </div>
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
            </div>
          </div>
          <div class="col-sm" id="card">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 1</h2>
              </div>
              <div class="shipname">
                <h2>Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img src="ship.png" />
              </div>
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
            </div>
          </div>
          <div class="col-sm" id="card">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 2</h2>
              </div>
              <div class="shipname">
                <h2>Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img src="ship.png" />
              </div>
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
            </div>
          </div>
          <div class="col-sm" id="card">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 3</h2>
              </div>
              <div class="shipname">
                <h2>Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img src="ship.png" />
              </div>
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
            </div>
          </div>
          <div class="col-sm" id="card">
            <div class="cardheader">
              <div class="playername">
                <h2>AI 4</h2>
              </div>
              <div class="shipname">
                <h2>Sabre</h2>
              </div>
            </div>
            <div class="shipinfo">
              <div class="shipimg">
                <img src="ship.png" />
              </div>
              <p>Size: 1</p>
              <p>Speed: 1</p>
              <p>Range: 1</p>
              <p>Firepower: 1</p>
              <p>Cargo: 1</p>
            </div>
          </div>
    </div>
    <div class="row" id="menu">
      MENU ITEMS
    </div>
  </div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");
				
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