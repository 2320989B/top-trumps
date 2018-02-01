package online.dwResources;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import model.GameAPI;
//import model.Player; // not sure why this class is invisible, if you make it public it should work
import model.GameInfo;

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
		private List<GameAPI> games;
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
		games = new ArrayList<GameAPI>();
	}

	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	
	
	//We wish to create a Game object, but return the index of the Game object
		// so that the tab can keep track of which Game object it 'owns', 
		// number of players. We'll return this as a JSON string
		@GET
		@Path("/startNewGame")
		public String startNewGame() throws IOException{
			//create a GameAPI object to get access to Game methods
			GameAPI game = new GameAPI(numAIPlayers, deckInputFile, false);
			
			//add the new GameAPI object to the list of Games
			games.add(game);

			//start a new game
			game.newGame();
			
			//create a String List with the information we would want to pass to web page
			// numAIplayers should be a separate method I think - want to keep checking
			// we do want to pass the index of this Game object for web page to store
			// names of players - separate again I think, perhaps part of numAIplayers
			// possibly want a separate method to return current top card
			//possibly separate to return categories and values
			//so
			//List<String> gameIndex = new ArrayList<String>();
			//gameIndex.add("plop");
			String gameInd = "" + numOfCurrentGames;
			//gameIndex.add(gameInd);
			
			// increment number of current Games in preparation for another Game
			numOfCurrentGames++;
			//send the Index to the web page
			//there is a method startNewGame() in GameScreen.ftl which will grab this info
			
			String stringAsJSONString = oWriter.writeValueAsString(gameInd);
			
			return stringAsJSONString;
		}
	
	@GET
	@Path("/getCategories")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getCategories(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get the list of categories
		Map<String, Integer> categories = gameInfo.getCardCategories();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(categories);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/newRound")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String newRound(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//start a new round
		game.newRound();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		int round = gameInfo.getRound();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(round);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getActivePlayer")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getActivePlayer(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		String activePlayer  = gameInfo.getActivePlayerName();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activePlayer);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/isHuman")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String isHuman(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		List<String> players = gameInfo.getPlayerNames();
		boolean isHuman;
		if (players.contains("Player 1")) {
			isHuman = true;
		}
		else
			isHuman = false;
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(isHuman);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getPlayerNames")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getPlayerNames(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		List<String> players = gameInfo.getPlayerNames();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(players);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getHumanTopCardTitle")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getHumanTopCardTitle(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the human top card title
		String humanTopCardTitle = gameInfo.getHumanTopCardTitle();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(humanTopCardTitle);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getHumanTopCardCategories")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getHumanTopCardCategories(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
		
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to get the list of categories
		Map<String, Integer> categories = gameInfo.getCardCategories();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(categories);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getTopCardTitles")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getTopCardTitles(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		List<String> topCardTitles = gameInfo.getTopCardTitles();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(topCardTitles);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/getTopCards")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String getTopCards(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		List<Map<String, Integer>> topCards = gameInfo.getTopCards();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(topCards);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/selectCategory")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String selectCategory(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//select category in game
		game.selectCategory();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		String activeCategory = gameInfo.getActiveCategory();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activeCategory);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/selectCategoryHuman")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String selectCategoryHuman(@QueryParam("gameIndexCat") String gameIndexCat) throws IOException {
		//convert the string gameIndex to an int
		System.out.println("GameIndexCat: " + gameIndexCat);
		//split the string into a String array, splitting on "xxxxx"
		String[] request = gameIndexCat.split("xxxxx");
		String num = request[0];
		int gameNumber = Integer.parseInt(num);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//select category in game
		game.selectCategory();
		
		//Human actually needs to set category
		String selectedCategory = request[1];
		game.setCategory(selectedCategory);
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		String activeCategoryCheck = gameInfo.getActiveCategory();
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(activeCategoryCheck);
		return stringAsJSONString;
	}
	
	@GET
	@Path("/computeResult")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param gameIndex - The index of this particular game in the games list
	 * @return - A String
	 * @throws IOException
	 */
	public String computeResult(@QueryParam("gameIndex") String gameIndex) throws IOException {
		//convert the string gameIndex to an int
		int gameNumber = Integer.parseInt(gameIndex);
	
		//get the specific instance of GameAPI associated with the tab calling this API
		GameAPI game = games.get(gameNumber);
		
		//compare Cards
		game.computeResult();
		
		//transfer cards
		game.concludeRound();
		
		//create a GameInfo object to get information about this specific game
		GameInfo gameInfo = game.getGameInfo();
		
		//use GameInfo object method to return the new round number
		String roundWinner = gameInfo.getRoundWinnerName();
		System.out.println("String is: " + roundWinner);
		
		//send the map of categories to the tab as a JSON string
		String stringAsJSONString = oWriter.writeValueAsString(roundWinner);
		return stringAsJSONString;
	}
}
