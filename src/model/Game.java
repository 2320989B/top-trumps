package model;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class Game extends Observable {
   private ArrayList<Card> deck = new ArrayList<>();
   private ArrayList<Player> players = new ArrayList<>();
   private GameState gameState; // I believe we will use this to update our
   // observer
   private int numAIPlayers; // need to have a record of how many players currently
   private String deckInputFile;// need to have a reference to the input file
   // Additional instance variables?
   private Player activePlayer;
   private String activeCategory;
   private int round = 0;
   private Boolean isHumanBooted = false; //
   private Boolean writeGameLogsToFile;
   final private String logFilePath = "toptrumps.log";
   private Logger logger;
   private Player roundWinner;

   // constructor to create a deck, the players and deal the intial cards to
   // players
   public Game(int numAIPlayers, String deckInputFile,
               Boolean writeGameLogsToFile) {
      // presumably want to call the setGameState method here rather than just setting
      // gameState variable
      // this means that a notification is sent to the controller to say that New game
      // has started
      // the controller can then call the appropriate method in the view I think
      setGameState(GameState.NEW_GAME);
      this.deckInputFile = deckInputFile;
      this.numAIPlayers = numAIPlayers;
      this.writeGameLogsToFile = writeGameLogsToFile;

      logger = new Logger(logFilePath, writeGameLogsToFile);
   }

   public Game() {

   }

   public int getRound() {
      return round;
   }

   public String getActiveCategory() {
      return activeCategory;
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

   // get the current gameState
   public GameState getGameState() {
      return this.gameState;
   }

   // set initial ActivePlayer at random
   // set the current Active Category
   public void setCategory(String category) {
      activeCategory = category;
   }

   // not sure what the return or type is here
   public void getStats() {

   }

   public String getRoundWinner() {
      if (roundWinner == null) {
         return null;
      } else {
         return roundWinner.getName();
      }
   }

   public int getNumCardsInHumanHand() {
      return getHumanPlayer().hand.size();
   }

// set the gameState during the game logic

   private void setGameState(GameState gameState) {
      this.gameState = gameState;
      setChanged();
      notifyObservers();
   }
   // worth thinking about whether we create a new game object instead of new game

   public void newGame() {
      logger.log("New game starting.");
      // create the deck
      createDeck();
      logger.log("Deck loaded: " + deck.toString());
      // shuffle the deck
      shuffleDeck();
      logger.log("Deck shuffled: " + deck.toString());
      // create the players
      createPlayers();
      // deal cards to players' decks
      deal();
      for (Player player : players) {
         logger.log("Hand: " + player);
      }
      // Randomly select the active player for the first round.
      selectRandomPlayer();
      // Set first round.
      // round = 1; //TODO: maybe easier to increase the round at the start of the
      // loop?

      playRound();
   }

   private void createPlayers() {
      // create human player first
      Player human = new Player("Player 1", true);
      players.add(human);
      // create remaining AI Players
      for (int i = 0; i < numAIPlayers; i++) {
         Player AI = new Player("AI " + (i + 1), false);
         players.add(AI);
      }
   }

   // read in file to create the deck and shuffle

   private void createDeck() {
      FileReader reader = null;
      try {
         try {
            reader = new FileReader(deckInputFile);
            Scanner in = new Scanner(reader);
            // read the top line column names of the file
            // e.g. description, size, speed etc.
            String categories = in.nextLine();

            // loop through the file line by line, creating a card and adding to the deck
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
      // only deal while there are cards in the deck
      // means cards can be dealt in a round robin fashion without worrying about how
      // many
      // players and cards there are
      int index = 0;
      while (!deck.isEmpty()) {
         Card card = deck.get(0);
         players.get(index).receiveCard(card);
         deck.remove(0);
         if (index < players.size() - 1) {
            index++;
         } else {
            index = 0;
         }
      }
   }

   private Player getHumanPlayer() {
      for (Player player : players) {
         if (player.getIsHuman()) {
            return player;
         }
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

   private void selectActiveCategory() {
      // if user is the active player, ask for the user's category
      if (activePlayer.getIsHuman() == true) {
         setGameState(GameState.CATEGORY_REQUIRED);
      }
      // get AI active player to select best category
      else {
         Card activeCard = activePlayer.getTopMostCard();
         activeCategory = activeCard.getBestCategory();
      }
   }

   private Player compareTopCards() {
      // COMPARE CARDS
      int bestValue = activePlayer.getTopMostCard().getCardPropertyValue(activeCategory);
      Player currentWinner = null;
      for (Player player : players) {
         // if not the active player
         if (!(player == activePlayer)) {
            // check specific player value for the active category
            int value = player.getTopMostCard().getCardPropertyValue(activeCategory);
            if (bestValue > value) {
               bestValue = value;
               currentWinner = player;
            }
            // if value matches top value then there is a tie
            else if (bestValue == value) {
               currentWinner = null;
            }
         }
      }
      return currentWinner;
   }

   private ArrayList<Card> getCurrentTopCards() {
      // logic to get round cards from all players
      ArrayList<Card> currentTopCards = new ArrayList<>();
      for (Player player : players) {
         // TODO - add in to take all current round cards and communal cards
         player.submitActiveCard(currentTopCards);
      }
      return currentTopCards;
   }

   private void transferCards(Player roundWinner, ArrayList<Card> currentTopCards) {
      // if there is a winner, the winner becomes the active player and takes round
      // cards
      if (roundWinner != null) {
         // set winner of round to be activePlayer for next round
         activePlayer = roundWinner;
         // take the pile of round cards
         roundWinner.takeAllCards(currentTopCards);
         // take communal cards if communal pile is not empty
         if (!deck.isEmpty()) {
            roundWinner.takeAllCards(deck);
         }
      }
      // if there is a draw, add round cards to the communal pile
      else {
         // need a cleaner way to add one deck to another maybe
         while (!currentTopCards.isEmpty()) {
            Card card = currentTopCards.get(0);
            deck.add(card);
            currentTopCards.remove(0);
         }
      }
   }

   private void playRound() {
      // game logic which should keep looping for every round
      while (players.size() > 1) {
         // increase the round number on every round
         round++;

         logger.log("\nRound " + round + " starting.");
         logger.log("Active player: " + activePlayer.getName());
         // Log player name and topmost card for all players.
         for (Player player : players) {
            logger.log(player.getName() + "'s card: " + player.getTopMostCard()
                    .toString());
         }

         // signal to the controller that we wish to start a new round
         // controller will present user's top card to the correct view
         // if the human player still exists
         if (!isHumanBooted) {
            setGameState(GameState.NEW_ROUND);
         } else {
            System.out.println("Current round is: " + round + " and human has been eliminated");
         }

         if (!isHumanBooted && !activePlayer.getIsHuman()) {
            setGameState(GameState.PAUSE);
         }

         // get active category for round, from user input or AI decision
         selectActiveCategory();
         logger.log("Selected category: " + activeCategory + "=" +
                 activePlayer.getTopMostCard().getCardProperties().get(activeCategory));

         // find whether the current round winner exists or is null
         roundWinner = compareTopCards();

         if (roundWinner == null) {
            logger.log("Round winner: Draw");
         } else {
            logger.log("Round winner: " + roundWinner.getName());
         }

         ArrayList<Card> currentTopCards = getCurrentTopCards();

         transferCards(roundWinner, currentTopCards);

         eliminatePlayers();

         setGameState(GameState.ROUND_COMPLETE);

         if (!isHumanBooted) {
            setGameState(GameState.PAUSE);
         }

         logger.log("Communal Deck: " + deck);
         for (Player player : players) {
            logger.log("Hand: " + player);
         }
         logger.log("Round complete.");
      }
   }

   private void eliminatePlayers() {
      // check if any players have no cards and eliminate them if so/////////may need
      // to make tidier
      // failing here because you are trying to modify something you are looping over
      // an iterator solution is below but we should maybe use a lambda alternative
      /*
       * for (Player player : players) { if(player.getList().isEmpty()) {
       * players.remove(player); }
       *
       * }
       */

      Iterator<Player> iter = players.iterator();

      while (iter.hasNext()) {
         Player player = iter.next();

         if (player.getList().isEmpty()) {
            // if the player with an empty deck is humnan, flag that they have been booted
            // from game
            if (player.getIsHuman()) {
               isHumanBooted = true;
            }
            iter.remove();
            // need to remember that due to successive draws, the active player could run
            // out of cards
            // select a new random player if playe
            if (!players.contains(activePlayer)) {
               selectRandomPlayer();
            }
         }
      }
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
         System.out.println(player.getName() + " has " + player.hand.size() + " cards in their hand.");
         totalCardsInAllHands += player.hand.size();
      }
      System.out.println("Sum of all cards in all hands: " + totalCardsInAllHands);

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
      System.out.println("Direct call: " + game.players.get(0).hand.get(0).getName());

      // Test getActivePlayer.
      System.out.println("\nTesting getActivePlayer...");
      System.out.println("===============================");

      game.activePlayer = game.players.get(0);
      System.out.println("Active Player is :" + game.getActivePlayer());
      game.activePlayer = game.players.get(1);
      System.out.println("Active Player is :" + game.getActivePlayer());

      // Test getCardProperties.
      System.out.println("\nTesting getCardProperties...");
      System.out.println("===============================");

      for (Map.Entry<String, Integer> entry : game.getCardCategories().entrySet()) {
         System.out.println("Key is: " + entry.getKey() + "   Value is: " + entry.getValue());
      }

      // Test Game.
      System.out.println("\nTesting getCardProperties...");
      System.out.println("===============================");

      game.numAIPlayers = 4;
      game.createDeck();
      game.shuffleDeck();
      game.createPlayers();
      game.deal();
      game.selectRandomPlayer();
      System.out.println("Active Player is: " + game.activePlayer);
      System.out.println("is HumanBooted: " + game.isHumanBooted);

   }

}
