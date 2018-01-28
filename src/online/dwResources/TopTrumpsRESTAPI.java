package online.dwResources;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Game;
//import model.Player; // not sure why this class is invisible, if you make it public it should work

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	
	//Joe - surely we need some way of extracting the deck file name from conf below
		// and we also surely have to have a list of Game objects - we need a list because
		// multiple tabs need their own instance of Game, and we also need to maintain
		// references to Game objects or they will be garbage collected
		private List<Game> Games;
		private int numOfCurrentGames;
		// can extract these values from TopTrumpsConfiguration conf
		private String deckInputFile;
		private int numAIPlayers;
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		numOfCurrentGames = 0;
		deckInputFile = conf.getDeckFile();
		numAIPlayers = conf.getNumAIPlayers();
		Games = new ArrayList<Game>();
	}

	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	//We wish to create a Game object, but return the index of the Game object
		// so that the tab can keep track of which Game object it 'owns', 
		// number of players. We'll return this as a JSON string
		@GET
		@Path("/startNewGame")
		public String startNewGame() throws IOException{
			//just setting to false just now
			Game newGame = new Game(numAIPlayers, deckInputFile, false);
			//add the new Game object to the list of Games
			Games.add(newGame);
			
			//start a new game
			Games.get(0).newGame();
			//create a String List with the information we would want to pass to web page
			// numAIplayers should be a separate method I think - want to keep checking
			// we do want to pass the index of this Game object for web page to store
			// names of players - separate again I think, perhaps part of numAIplayers
			// possibly want a separate method to return current top card
			//possibly separate to return categories and values
			//so
			List<String> gameIndex = new ArrayList<String>();
			gameIndex.add("GameIndex");
			String gameInd = "" + numOfCurrentGames;
			gameIndex.add(gameInd);
			
			// increment number of current Games in preparation for another Game
			numOfCurrentGames++;
			//send the Index to the web page
			//there is a method startNewGame() in GameScreen.ftl which will grab this info
			
			String listAsJSONString = oWriter.writeValueAsString(gameIndex);
			return listAsJSONString;
		}
	//an attempt at getting the active player
		/*@GET
		@Path("/getActivePlayer")
		public String getActivePlayer() throws IOException{
			
			
			//just to check that we can do this with Game 0
			Game currentGame = Games.get(0);
			
			//get the current Active Player and send it through to the web page
			String activePlayer = currentGame.getActivePlayer();
			System.out.println("ActivePlayer: " + activePlayer);
			List<String> activeName = new ArrayList<String>();
			activeName.add("ActivePlayer");
			activeName.add(activePlayer);
			
			String listAsJSONString = oWriter.writeValueAsString(activeName);
			
			return listAsJSONString;
		}*/
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
}
