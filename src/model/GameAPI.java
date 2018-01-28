package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameAPI {

   private GameLogic gameLogic;

   public GameLogic getGameLogic() {
      return gameLogic;
   }

   // =======================================================================
   //
   // DELEGATE DIRECTLY TO GAME
   //
   // =======================================================================

   public GameAPI(int numAIPlayers, String deckInputFile,
                  Boolean writeGameLogsToFile) {
      gameLogic = new GameLogic(numAIPlayers, deckInputFile, writeGameLogsToFile);
   }

   public void newGame() {
      gameLogic.newGame();
   }

   public GameState getGameState() {
      return gameLogic.getGameState();
   }

   public String getActiveCategory() {
      return gameLogic.getActiveCategory();
   }

   public void setCategory(String category) {
      gameLogic.setActiveCategory(category);
   }

   public int getRound() {
      return gameLogic.getRound();
   }

   public int getHumanWonRounds() {
      return gameLogic.getHumanWonRounds();
   }

   public int getNumDraws() {
      return gameLogic.getNumDraws();
   }

   // =======================================================================
   //
   // EXTRACT FROM MODEL
   //
   // =======================================================================

   public String getActivePlayer() {
      return gameLogic.getActivePlayer().getName();
   }

   public String getRoundWinner() {
      if (gameLogic.getRoundWinner() != null) {
         return gameLogic.getRoundWinner().getName();
      } else {
         return null;
      }
   }

   public String getWinnerName() {
      // TODO: Is a new variable needed for gameWinner, we can just use the value of roundWinner at the end of the game instead.
      return gameLogic.getGameWinner().getName();
   }

   public Boolean getWinnerHuman() {
      return gameLogic.getGameWinner().getIsHuman();
   }

   public List<Map<String, Integer>> getAllTopCards() {
      List<Map<String, Integer>> allTopCards = new ArrayList<>();
      for (Player player : gameLogic.getPlayers()) {
         allTopCards.add(player.getTopMostCard().getCardProperties());
      }
      return allTopCards;
   }

   public List<String> getPlayerNames() {
      List<String> playerNames = new ArrayList<>();
      for (Player player : gameLogic.getPlayers()) {
         playerNames.add(player.getName());
      }
      return playerNames;
   }
   public List<String> getAllTopCardTitles() {
      List<String> allTopCardTitles = new ArrayList<>();
      for (Player player : gameLogic.getPlayers()) {
         allTopCardTitles.add(player.getTopMostCard().getName());
      }
      return allTopCardTitles;
   }

   public int getNumCardsInHumanHand() {
      return getHumanPlayer().hand.size();
   }
   // set initial ActivePlayer at random

   // set the current Active Category

   public String getCardDescription() {
      return getHumanPlayer().getTopMostCard().getName();
   }

   public Map<String, Integer> getCardCategories() {
      return getHumanPlayer().getTopMostCard().getCardProperties();
   }

   private Player getHumanPlayer() {
      for (Player player : gameLogic.getPlayers()) {
         if (player.getIsHuman()) {
            return player;
         }
      }
      return null;
   }
}

// 	//DB Statistics
// 	private int rounds = 0;
// 	private int humanWonRounds = 0;
// 	private int numDraws = 0;
// 	private Player gameWinner;

// 	public GameInfo() {

// 	}

// 	/**
// 	* GETTERS
// 	*/

// 	public void getDraws() {
// 		return this.numDraws;
// 	}

// 	public void getRounds() {
// 		return this.rounds;
// 	}

// 	public void getHumanWins() {
// 		return this.humanWonRounds;
// 	}

// 	public void getWinner() {
// 		return this.gameWinner;
// 	}

// 	public void getDraws() {
// 		return this.numDraws;
// 	}


// 	/**
// 	* Increments the number of drawn rounds in the game
// 	*/
// 	public void addDraw() {
// 		this.numDraws++;
// 	}

// 	/**
// 	* Increments the number of rounds in the game
// 	*/
// 	public void addRound() {
// 		this.rounds++;
// 	}

// 	/**
// 	* Increments the number of rounds in the game won by a human player
// 	*/
// 	public void addHumanWin() {
// 		this.humanWonRounds++;
// 	}

// 	/**
// 	* Sets the player object of the game winner
// 	*/
// 	public void setWinner(Player playerObj) {
// 		this.gameWinner = playerObj;
// 	}

// }
