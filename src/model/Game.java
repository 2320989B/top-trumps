package model;

import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;  //easier to have an ArrayList of objects rather than a fixed length array
import java.util.Collections;


public class Game extends Observable {

	private ArrayList<Card> deck;
	private ArrayList<Player> players;
	private GameState gameState; //I believe we will use this to update our
	// observer
	private int numOfPlayers; //need to have a record of how many players currently
	private String deckInputFile;// need to have a reference to the input file
	//Additional instance variables?
	private Player activePlayer;
	private String activeCategory;
	private int round; 
	
	//constructor to create a deck, the players and deal the intial cards to players
	public Game(int numAIPlayers, String deckInputFile) {
		//presumably want to call the setGameState method here rather than just setting gameState variable
		//this means that a notification is sent to the controller to say that New game has started
		//the controller can then call the appropriate method in the view I think
		setGameState(GameState.NEW_GAME);
		this.deckInputFile = deckInputFile;
		
	}
		
	private void createPlayers() {
		//create human player first
		Player human = new Player("Player1", true);
		players.add(human);
		//create remaining AI Players, start at 1 because Player 1 is at position 0
		for(int i = 1; i < numOfPlayers; i++) {
			Player AI = new Player("AI" + i, false);
			players.add(AI);
		}
		//TEST: check the players are created
		for(Player player : players) {
			System.out.println(player);
		}
	}
	
	private void deal() {
		//only deal while there are cards in the deck
		//means cards can be dealt in a round robin fashion without worrying about how many
		//players and cards there are
		while(!deck.isEmpty()) {
			for(Player player : players) {
				Card card = deck.get(0);
				player.receiveCard(card);
			}
		}
	}
	
	//get the current gameState
	public GameState getGameState() {
		return this.gameState;
	}
	//begin a new game
	//worth thinking about whether we create a new game object instead of new game
	public void newGame() {
		//create the deck
		createDeck();
		//shuffle the deck
		Collections.shuffle(deck);
		//create the players
		createPlayers();
		//deal cards to players' decks
		deal();
		//set an initial active player
		setActivePlayer();
		
		//game logic which should keep looping for every round
		while(numOfPlayers > 1) {
			//signal to the controller that we wish to start a new round
			setGameState(GameState.NEW_ROUND);
			
			
			
			
			
			
		}
	}
	
	//set initial ActivePlayer at random
	private void setActivePlayer() {
		Random rand = new Random();
		int random = rand.nextInt(5);
		activePlayer = players.get(random);
	}
	
	//set the current Active Category
	public void setCategory() {
		
	}
	
	//not sure what the return or type is here
	public void getStats() {
		
	}
	
	//set the gameState during the game logic
	private void setGameState(GameState gameState) {
		this.gameState = gameState;
		setChanged();
		notifyObservers();
	}
	
	//read in file to create the deck and shuffle
	private void createDeck() {
		FileReader reader = null;
	    try {
	    	try {
	    		reader = new FileReader(deckInputFile);
	    		Scanner in = new Scanner(reader);
	    		//read the top line column names of the file
	    		//e.g. description, size, speed etc.
	    		String categories = in.nextLine();
	    		
	    		//loop through the file line by line, creating a card and adding to the deck
	    		while(in.hasNextLine()) {
	    			String values = in.nextLine();
	    			Card newCard = new Card(categories, values);
	    			deck.add(newCard);
	    		}
	    	}
	    	finally {
	    		if(reader != null) {
	    			reader.close();
	    		}
	    	}
	    }
	    catch (IOException e) {
	    	System.out.print("error");
	    }
	}
}
