package model;

import java.util.Observable;
import java.util.ArrayList;  //easier to have an ArrayList of objects rather than a fixed length array
import java.util.Collection; //allows us to take advantage of Collections.shuffle(deck)

public class Game extends Observable {
	
	//create an enum as we know all possible states before compile time
	//could be in a separate .java file?
	public enum GameState {
		NEW_GAME,
		NEW_ROUND,
		CATEGORY_REQUIRED,
		ROUND_COMPLETE,
		NEW_COMPLETE
	}
	
	private ArrayList<Card> deck;
	private ArrayList<Player> players;
	private GameState gameState; //I believe we will use this to update our observer 
	
	//Additional instance variables?
	private Player activePlayer;
	private int round;
	
	//constructor
	public Game() {
		//presumably want to call the setGameState method here rather than just setting gameState variable
		//this means that a notification is sent to the controller to say that New game has started
		//the controller can then call the appropriate method in the view I think
		setGameState(GameState.NEW_GAME);
	}
	
	//get the current gameState
	public GameState getGameState() {
		return this.gameState;
	}
	//begin a new game
	public void newGame() {
		
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
	
}
