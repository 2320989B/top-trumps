package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The GameInfo class is an Immutable Parameter Object responsible for
 * providing a snapshot of various game data. The class marshals relevant
 * data from within the model package, and provides getters
 * to allow other packages to extract this data.
 */
public class GameInfo {
   private final List<Map<String, Integer>> topCards;
   private final List<String> playerNames;
   private final List<String> topCardTitles;
   private ArrayList<Card> deck;
   private int numOfCommunalCards;
   private Integer numHumanCards;
   private String humanTopCardTitle;
   private Map<String, Integer> cardCategories;
   private Map<String, Integer> numOfCardsLeft;
   private String activeCategory;
   private int round;
   private int humanRoundsWon;
   private int numDraws;
   private String activePlayerName;
   private String roundWinnerName;
   private String gameWinnerName;
   private Boolean winnerHuman;
   private GameState gameState;


   /**
    * Upon instantiation, GameInfo will fetch data from the model.
    * Unavailable data is represented as a null value.
    *
    * @param game the Game object.
    */
   public GameInfo(Game game) {
      gameState = game.getGameState();
      activeCategory = game.getActiveCategory();
      round = game.getRound();
      humanRoundsWon = game.getHumanWonRounds();
      numDraws = game.getNumDraws();

      if (game.getActivePlayer() != null) {
         activePlayerName = game.getActivePlayer().getName();
      } else {
         activePlayerName = null;
      }

      if (game.getRoundWinner() != null) {
         roundWinnerName = game.getRoundWinner().getName();
      } else {
         roundWinnerName = null;
      }

      // TODO: Is a new variable needed for gameWinner, we can just use the value of roundWinner at the end of the game instead.
      if (game.getGameWinner() != null) {
         gameWinnerName = game.getGameWinner().getName();
      } else {
         gameWinnerName = null;
      }

      if (game.getGameWinner() != null) {
         winnerHuman = game.getGameWinner().getIsHuman();
      } else {
         winnerHuman = null;
      }

      topCards = getTopCards(game);
      playerNames = getPlayerNames(game);
      topCardTitles = getTopCardTitles(game);
      deck = getDeck(game);
      Player humanPlayer = getHumanPlayer(game);
      numOfCardsLeft = getNumOfCards(game);

      if (humanPlayer != null && humanPlayer.getHand() != null) {
         numHumanCards = humanPlayer.getHand().size();
      } else {
         numHumanCards = null;
      }
      
      if (deck.size() > 0) {
          numOfCommunalCards = deck.size();
       } else {
          numHumanCards = 0;
       }

      if (humanPlayer != null && humanPlayer.getTopMostCard() != null) {
         humanTopCardTitle = humanPlayer.getTopMostCard().getName();
         cardCategories = humanPlayer.getTopMostCard().getCardProperties();
      } else {
         humanTopCardTitle = null;
         cardCategories = null;
      }
   }

   public List<Map<String, Integer>> getTopCards() {
      return topCards;
   }

   public List<String> getPlayerNames() {
      return playerNames;
   }

   public List<String> getTopCardTitles() {
      return topCardTitles;
   }

   public String getActiveCategory() {
      return activeCategory;
   }

   public int getRound() {
      return round;
   }

   public int getHumanRoundsWon() {
      return humanRoundsWon;
   }

   public String getGameWinnerName() {
	  return gameWinnerName;
   }
	   
   public Boolean getGameWinnerHuman() {
	  return winnerHuman;
	     }
   public int getNumDraws() {
      return numDraws;
   }

   public String getActivePlayerName() {
      return activePlayerName;
   }

   public String getRoundWinnerName() {
      return roundWinnerName;
   }

   public GameState getGameState() {
      return gameState;
   }
   
   public int getNumOfCommunalCards() {
	   return numOfCommunalCards;
   }
   
   public Map<String, Integer> getNumOfCardsLeft() {
	   return numOfCardsLeft;
   }

   public Integer getNumHumanCards() {
      return numHumanCards;
   }

   public String getHumanTopCardTitle() {
      return humanTopCardTitle;
   }

   public Map<String, Integer> getCardCategories() {
      return cardCategories;
   }

   private List<Map<String, Integer>> getTopCards(Game game) {
      List<Map<String, Integer>> topCards = new ArrayList<>();
      for (Player player : game.getPlayers()) {
         try {
            topCards.add(player.getTopMostCard().getCardProperties());
         } catch (NullPointerException e) {
            return null;
         }
      }
      return topCards;
   }

   private List<String> getPlayerNames(Game game) {
      List<String> playerNames = new ArrayList<>();
      for (Player player : game.getPlayers()) {
         playerNames.add(player.getName());
      }
      return playerNames;
   }

   private List<String> getTopCardTitles(Game game) {
      List<String> allTopCardTitles = new ArrayList<>();
      for (Player player : game.getPlayers()) {
         try {
            allTopCardTitles.add(player.getTopMostCard().getName());
         } catch (NullPointerException e) {
            return null;
         }
      }
      return allTopCardTitles;
   }

   private Player getHumanPlayer(Game game) {
      for (Player player : game.getPlayers()) {
         if (player.getIsHuman()) {
            return player;
         }
      }
      return null;
   }
   
   private ArrayList<Card> getDeck(Game game) {
	   return game.getDeck();
   }
   
   private Map<String, Integer> getNumOfCards(Game game) {
	   Map<String, Integer> numOfPlayerCards = new LinkedHashMap<String, Integer>();
	   for (Player player : game.getPlayers()) {
	       String name = player.getName();
	       Integer cardsLeft = player.getHand().size();
	       numOfPlayerCards.put(name, cardsLeft);
	   }
	   return numOfPlayerCards;
   }

}
