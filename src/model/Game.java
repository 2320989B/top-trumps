package model;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Game class is responsible for defining game logic and flow.
 */
public class Game extends Observable {
   private Logger logger;
   private GameState gameState;
   private ArrayList<Card> deck = new ArrayList<>();
   private ArrayList<Player> players = new ArrayList<>();
   private Player activePlayer;
   private Player roundWinner;
   // TODO: Is a new variable needed for gameWinner, we can just use the value of roundWinner at the end of the game instead.
   private Player gameWinner;
   private String deckInputFile;
   private String activeCategory;
   private int numAIPlayers;
   private int round = 0;
   private int humanWonRounds = 0;
   private int numDraws = 0;
   private Boolean isHumanBooted = false;

   public Game(int numAIPlayers, String deckInputFile,
               Boolean writeGameLogsToFile) {
      this.deckInputFile = deckInputFile;
      this.numAIPlayers = numAIPlayers;

      String logFilePath = "toptrumps.log";
      logger = new Logger(logFilePath, writeGameLogsToFile);
   }

   GameState getGameState() {
      return gameState;
   }

   ArrayList<Player> getPlayers() {
      return players;
   }

   Player getActivePlayer() {
      return activePlayer;
   }

   Player getRoundWinner() {
      return roundWinner;
   }

   Player getGameWinner() {
      return gameWinner;
   }

   String getActiveCategory() {
      return activeCategory;
   }

   int getRound() {

      return round;
   }

   int getHumanWonRounds() {
      return humanWonRounds;
   }

   int getNumDraws() {
      return numDraws;
   }

   private void setGameState(GameState gameState) {
      this.gameState = gameState;
      setChanged();
      notifyObservers();
   }

   public void setCategory(String category) {
      activeCategory = category;
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

      setGameState(GameState.PLAYERS_SPAWNED);

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

      setGameState(GameState.GAME_COMPLETE);
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
      // assume activePlayer will win the round and then test if this is true
      // if no other card beats or equals the ActivePlayer's card
      // the activePlayer will be returned as the winner
      Player currentWinner = activePlayer;
      for (Player player : players) {
         // if not the active player
         if (!(player == activePlayer)) {
            // check specific player value for the active category
            int value = player.getTopMostCard().getCardPropertyValue(activeCategory);
            if (bestValue < value) {
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
            this.numDraws++;
         } else {
            logger.log("Round winner: " + roundWinner.getName());
            this.gameWinner = roundWinner;
            if (roundWinner.getIsHuman()) {
               this.humanWonRounds++;
            }
         }

         setGameState(GameState.ROUND_COMPLETE);

         ArrayList<Card> currentTopCards = getCurrentTopCards();

         transferCards(roundWinner, currentTopCards);

         eliminatePlayers();


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
               setGameState(GameState.HUMAN_BOOTED);
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


}
