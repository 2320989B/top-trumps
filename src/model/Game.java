package model;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class Game extends Observable {
   private ArrayList<Card> deck;
   private ArrayList<Player> players = new ArrayList<>();
   private GameState gameState; //I believe we will use this to update our
   // observer
   private int numAIPlayers; //need to have a record of how many players currently
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
      this.numAIPlayers = numAIPlayers;
   }

   public Game() {

   }

   public int getRound() {
      return round;
   }

   public String getCardDescription() {
      return null;
   }

   public Map<String, Integer> getCardCategories() {
      return null;
   }

   public String getActivePlayer() {
      return null;
   }

   //get the current gameState
   public GameState getGameState() {
      return this.gameState;
   }

   //set initial ActivePlayer at random
   //set the current Active Category
   public void setCategory(String category) {

   }

   //not sure what the return or type is here
   public void getStats() {

   }

   private void selectRandomPlayer() {
      Random rand = new Random();
      // Restrict random number range to the avaiable index in the players list.
      // - 1 to offset zero-based index numbering.
      int random = rand.nextInt(players.size() - 1);
      activePlayer = players.get(random);
   }

   //set the gameState during the game logic
   private void setGameState(GameState gameState) {
      this.gameState = gameState;
      setChanged();
      notifyObservers();
   }

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
      // Randomly select the active player for the first round.
      selectRandomPlayer();
      // Set first round.
      round = 1;

      //game logic which should keep looping for every round
      while (players.size() > 1) {
         //signal to the controller that we wish to start a new round
         setGameState(GameState.NEW_ROUND);
      }
   }

   private void createPlayers() {
      //create human player first
      Player human = new Player("Player 1", true);
      players.add(human);
      //create remaining AI Players, start at 1 because Player 1 is at position 0
      for (int i = 1; i < numAIPlayers + 1; i++) {
         Player AI = new Player("AI " + i, false);
         players.add(AI);
      }
      //TEST: check the players are created
      for (Player player : players) {
         System.out.println(player);
      }
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
            while (in.hasNextLine()) {
               String values = in.nextLine();
               Card newCard = new Card(categories, values);
               deck.add(newCard);
            }
         } finally {
            if (reader != null) {
               reader.close();
            }
         }
      } catch (IOException e) {
         System.out.print("error");
      }
   }

   private void deal() {
      //only deal while there are cards in the deck
      //means cards can be dealt in a round robin fashion without worrying about how many
      //players and cards there are
      while (!deck.isEmpty()) {
         for (Player player : players) {
            Card card = deck.get(0);
            player.receiveCard(card);
         }
      }
   }

   public static void main(String[] args) {

      Game game = new Game();
      game.numAIPlayers = 6;
      game.createPlayers();
      System.out.println("done");

   }

}
