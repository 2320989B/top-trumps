package model;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class Game extends Observable {
   private ArrayList<Card> deck = new ArrayList<>();
   private ArrayList<Card> communal = new ArrayList<>();
   private ArrayList<Player> players = new ArrayList<>();
   private GameState gameState; //I believe we will use this to update our
   // observer
   private int numAIPlayers; //need to have a record of how many players currently
   private String deckInputFile;// need to have a reference to the input file
   //Additional instance variables?
   private Player activePlayer;
   private String activeCategory;
   private int round = 0;
   private Boolean isHumanBooted; //

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
      return getHumanPlayer().getTopMostCard().getName();
   }

   public Map<String, Integer> getCardCategories() {
	  return getHumanPlayer().getTopMostCard().getCardProperties();
   }

   public String getActivePlayer() {
	   return activePlayer.getName();
   }

   //get the current gameState
   public GameState getGameState() {
      return this.gameState;
   }

   //set initial ActivePlayer at random
   //set the current Active Category
   public void setCategory(String category) {
	   activeCategory = category;
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
   //worth thinking about whether we create a new game object instead of new game

   public void newGame() {
      //create the deck
      createDeck();
      //shuffle the deck
      shuffleDeck();
      //create the players
      createPlayers();
      //deal cards to players' decks
      deal();
      // Randomly select the active player for the first round.
      selectRandomPlayer();
      // Set first round.
      //round = 1;

      //game logic which should keep looping for every round
      while (players.size() > 1) {
    	  round++;
         //signal to the controller that we wish to start a new round
         setGameState(GameState.NEW_ROUND);
         //if user is the active player, ask for the user's category
         if(activePlayer.getIsHuman() == true) {
        	 setGameState(GameState.CATEGORY_REQUIRED);
         }
         //get AI active player to select best category
         else {
        	 Card activeCard = activePlayer.getTopMostCard();
        	 activeCategory = activeCard.getBestCategory();
         }
         //COMPARE CARDS
         int bestValue = activePlayer.getTopMostCard().getCardPropertyValue(activeCategory);
         //int bestValueDraw = 0;
         Player currentWinner = null;
         for(Player player : players) {
        	 //if not the active player
        	 if(!(player == activePlayer)) {
        		 //check specific player value for the active category
        		 int value = player.getTopMostCard().getCardPropertyValue(activeCategory);
        		 if(bestValue > value) {
        			 bestValue = value;
        			 currentWinner = player;
        		 }
        		 //if value matches top value then there is a tie
        		 else if(bestValue == value) {
        			 currentWinner = null;
        		 }
        	 }
         }
         //logic to get round cards from all players
    	 ArrayList<Card> currentTopCards = new ArrayList<>();
    	 for(Player player : players) {
    		 //TODO - add in to take all current round cards and communal cards
    		 player.submitActiveCard(currentTopCards);
    	 }
    	 
    	 //if there is a winner, the winner becomes the active player and takes round cards
         if(currentWinner != null) {
        	 //set winner of round to be activePlayer for next round
        	 activePlayer = currentWinner;
        	 //take the pile of round cards
        	 currentWinner.takeAllCards(currentTopCards);
        	 //take communal cards if communal pile is not empty
        	 if(!communal.isEmpty()) {
        		 currentWinner.takeAllCards(communal);
        	 }
         }
         //if there is a draw, add round cards to the communal pile
         else {
        	 System.out.println("It was a draw"); //TODO Remove///////////////////////
        	 //need a cleaner way to add one deck to another maybe
        	 while(!currentTopCards.isEmpty()) {
        		 Card card = currentTopCards.get(0);
        		 communal.add(card);
        		 currentTopCards.remove(0);
        	 }
         }
         //check if any players have no cards and eliminate them if so/////////may need to make tidier
         //failing here because you are trying to modify something you are looping over
         //an iterator solution is below but we should maybe use a lambda alternative
        /* for (Player player : players) {
        		 if(player.getList().isEmpty()) {
        			 players.remove(player);
        		 }
        	
         }*/
         
         Iterator<Player> iter = players.iterator();

         while (iter.hasNext()) {
             Player player = iter.next();

             if (player.getList().isEmpty()) { 
            	 iter.remove();
            	 //need to remember that due to successive draws, the active player could run out of cards
            	 //select a new random player if playe
             	 if(!players.contains(activePlayer)) {
             		 selectRandomPlayer();
             	 }
             }
         }
        
         setGameState(GameState.ROUND_COMPLETE);
      }
   }
   private void createPlayers() {
      //create human player first
      Player human = new Player("Player 1", true);
      players.add(human);
      //create remaining AI Players
      for (int i = 0; i < numAIPlayers; i++) {
         Player AI = new Player("AI " + (i + 1), false);
         players.add(AI);
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

   private void shuffleDeck() {
      Collections.shuffle(deck);
   }

   private void deal() {
      //only deal while there are cards in the deck
      //means cards can be dealt in a round robin fashion without worrying about how many
      //players and cards there are
	  int index = 0;
      while (!deck.isEmpty()) {
    	  Card card = deck.get(0);
          players.get(index).receiveCard(card);
          deck.remove(0);
          if(index < players.size()-1) {
    	     index++;
          }
          else {
    	     index = 0;
          }
      }
   }

   private Player getHumanPlayer() {
      for (Player player : players) {
         if (player.getIsHuman()) { return player; }
      }
      return null;
   }

   private void selectRandomPlayer() {
      Random rand = new Random();
      // Restrict random number range to available indexes in the players list.
      // - 1 to offset zero-based index numbering.
      int random = rand.nextInt(players.size());
      activePlayer = players.get(random);
   }

   public static void main(String[] args) {

      Game game = new Game();

      // Test createPlayers.
      System.out.println("\nTesting createPlayers...");
      System.out.println("========================");

      game.numAIPlayers = 2;
      game.createPlayers();

      // Check the players are created.
      for (Player player : game.players) {
         System.out.println(player.getName());
      }


      // Test createDeck.
      System.out.println("\nTesting createDeck...");
      System.out.println("=====================");

      game.deckInputFile = "StarCitizenDeck.txt";
      game.createDeck();

      // Check that the deck is created.
      for (Card card : game.deck) {
         System.out.println(card.getName());
      }
      System.out.println("Size of deck: " + game.deck.size());


      // Test shuffleDeck.
      System.out.println("\nTesting shuffleDeck...");
      System.out.println("======================");

      game.shuffleDeck();

      // Check the deck is shuffled.
      for (Card card : game.deck) {
         System.out.println(card.getName());
      }
      System.out.println("Size of deck: " + game.deck.size());


      // Test deal.
      System.out.println("\nTesting deal...");
      System.out.println("===============");

      game.deal();
      System.out.println("Size of deck after deal: " + game.deck.size());
      int totalCardsInAllHands = 0;
      for (Player player : game.players) {
         System.out.println(player.getName() + " has " + player.hand.size() +
         " cards in their hand.");
         totalCardsInAllHands += player.hand.size();
      }
      System.out.println("Sum of all cards in all hands: "
              + totalCardsInAllHands);


      // Test selectRandomPlayer.
      System.out.println("\nTesting selectRandomPlayer...");
      System.out.println("=============================");

      // Do some runs and make sure this looks randomy.
      for (int i = 0; i < 10; i++) {
         game.selectRandomPlayer();
         System.out.println(game.activePlayer.getName());
      }


      // Test getHumanPlayer.
      System.out.println("\nTesting getHumanPlayer...");
      System.out.println("=========================");

      System.out.println("Human player: " + game.getHumanPlayer().getName());


      // Test getCardDescription.
      System.out.println("\nTesting getCardDescription...");
      System.out.println("=============================");

      System.out.println("Human's card: " + game.getCardDescription());
      // Human will be at index 0 in the player list, and his top card will at
      // index 0 in his hand.
      System.out.println("Direct call: " + game.players.get(0).hand.get(0)
              .getName());


      // Test getActivePlayer.
      System.out.println("\nTesting getActivePlayer...");
      System.out.println("===============================");
      
      game.activePlayer = game.players.get(0);
      System.out.println("Active Player is :" + game.getActivePlayer() );
      game.activePlayer = game.players.get(1);
      System.out.println("Active Player is :" + game.getActivePlayer() );


      // Test getCardProperties.
      System.out.println("\nTesting getCardProperties...");
      System.out.println("===============================");
      
      for(Map.Entry<String, Integer> entry : game.getCardCategories().entrySet()) {
			System.out.println("Key is: " + entry.getKey() + "   Value is: " + entry.getValue());
      }
      
    }

}
