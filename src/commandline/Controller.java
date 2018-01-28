package commandline;

import model.GameAPI;
import model.GameState;
import persistence.PostgresPersistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The Controller class is responsible for triggering appropriate Views,
 * handling any returned user input, and reacting to Game events (gameAPI states).
 */
class Controller implements Observer {

   private Boolean writeGameLogsToFile;
   private GameAPI gameAPI;
   private List<String> initialPlayerNames;
   private PostgresPersistence dbConnection;

   /**
    * Instantiates a new Controller.
    *
    * @param writeGameLogsToFile flag to indicate if gameAPI logs should be
    *                            written to a debug file.
    */
   Controller(Boolean writeGameLogsToFile) {
      this.writeGameLogsToFile = writeGameLogsToFile;
      dbConnection = new PostgresPersistence();
   }

   /**
    * Start the controller logic path.
    *
    * @return a quit flag.
    */
   Boolean start() {
      // There are always 4 AI players in command line mode.
      final int NUM_AI_PLAYERS = 4;

      // Future flexibility, define input file here.
      final String DECK_INPUT_FILE = "StarCitizenDeck.txt";

      // First, we present the main menu to the user and await their response.
      final int selection = new ViewMainMenu().show();

      // Now handle the response appropriately.
      // 1. Start a new gameAPI.
      if (selection == 1) {
         gameAPI = new GameAPI(NUM_AI_PLAYERS, DECK_INPUT_FILE,
                 writeGameLogsToFile);
         // Observe the gameAPI.
         gameAPI.getGameLogic().addObserver(this);
         gameAPI.newGame();

         // 2. View statistics.
      } else if (selection == 2) {
         try {
            dbConnection.establishDBConnection();
            new ViewStats().show(dbConnection.getGameCount(),
                    dbConnection.getAIWinCount(),
                    dbConnection.getHumanWinCount(),
                    dbConnection.getAverageDraws(),
                    dbConnection.getMaxRoundCount());
            dbConnection.closeDBConnection();
         } catch (SQLException | ClassNotFoundException e) {
            new ViewDBError().show(e.getMessage());
         }

         // 3. Quit.
      } else {
         return true;
      }

      return false;

   }

   public void update(Observable observable, Object o) {
      GameState gameState = gameAPI.getGameState();

      if (gameState.equals(GameState.PLAYERS_SPAWNED)) {
         initialPlayerNames = gameAPI.getPlayerNames();

      } else if (gameState.equals(GameState.NEW_ROUND)) {
         new ViewNewRound().show(gameAPI.getRound(), gameAPI.getCardDescription(),
                 gameAPI.getCardCategories(), gameAPI.getActivePlayer(),
                 initialPlayerNames, gameAPI.getPlayerNames(),
                 gameAPI.getNumCardsInHumanHand());

      } else if (gameState.equals(GameState.PAUSE)) {
         new ViewPause().show();

      } else if (gameState.equals(GameState.CATEGORY_REQUIRED)) {
         final String selection = new ViewCategorySelector().show(
                 gameAPI.getCardCategories());
         gameAPI.setCategory(selection);

      } else if (gameState.equals(GameState.HUMAN_BOOTED)) {
         new ViewHumanBooted().show(gameAPI.getPlayerNames());
         new ViewPause().show();

      } else if (gameState.equals(GameState.ROUND_COMPLETE)) {
         new ViewRoundSummary().show(gameAPI.getActiveCategory(),
                 gameAPI.getRoundWinner(), gameAPI.getPlayerNames(),
                 gameAPI.getAllTopCardTitles(), gameAPI.getAllTopCards(),
                 gameAPI.getActivePlayer());

      } else if (gameState.equals(GameState.GAME_COMPLETE)) {
         new ViewGameComplete().show(gameAPI.getRoundWinner());
         try {
            dbConnection.establishDBConnection();
            passDBStats();
            dbConnection.commit(); //Commit the DB object data to the database
            dbConnection.closeDBConnection();
         } catch (SQLException | ClassNotFoundException e) {
            new ViewDBError().show(e.getMessage());
         }
      }
   }

   // Get latest data from model and feed to DB object.
   private void passDBStats() {
      dbConnection.setGameDraws(gameAPI.getNumDraws());
      dbConnection.setGameWinnerIsHuman(gameAPI.getWinnerHuman());
      dbConnection.setGameWinnerName(gameAPI.getWinnerName());
      dbConnection.setNumGameRounds(gameAPI.getRound());
      dbConnection.setPlayerRounds(gameAPI.getHumanWonRounds());
   }
}
