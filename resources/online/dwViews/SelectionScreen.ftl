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
<!--       <link href="main.css" rel="stylesheet">
 -->      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet"> 
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





  </head>
    <div class="row justify-content-md-center" id="titlebar">
      <h1>Top Trumps</h1>
    </div>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
      
  <div class="container">
    <div class="row justify-content-sm-center">
          <div class="col-sm">
			<a href="http://localhost:7777/toptrumps/game"><button type="button" class="btn btn-primary btn-lg btn-block" id="menubtn">Start New Game</button></a>
<!--           </div>
          <div class="col-sm"> -->
			<button type="button" class="btn btn-primary btn-lg btn-block" id="menubtn">View Statistics</button>
          </div>
    </div>
  </div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
                //lets test storing an index reference to the new Game created by this tab
                
				
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
		//<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			//function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				//var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				//if (!xhr) {
  					//alert("CORS not supported");
				//}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				//xhr.onload = function(e) {
 					//var responseText = xhr.response; // the text of the response
					//alert(responseText); // lets produce an alert
				//};
				
				// We have done everything we need to prepare the CORS request, so send it
				//xhr.send();		
			//}
            
            
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			//function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				//var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				//if (!xhr) {
  					//alert("CORS not supported");
				//}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				//xhr.onload = function(e) {
 					//var responseText = xhr.response; // the text of the response
					//alert(responseText); // lets produce an alert
				//};
				
				// We have done everything we need to prepare the CORS request, so send it
				//xhr.send();		
			//}

		</script>
		
		</body>
</html>