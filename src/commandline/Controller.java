package commandline;

import model.Game;
import model.GameInfo;
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
   private Game game;
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
         game = new Game(NUM_AI_PLAYERS, DECK_INPUT_FILE,
                 writeGameLogsToFile);
         // Observe the gameAPI.
         game.addObserver(this);
         game.newGame();

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
      GameInfo gameInfo = new GameInfo(game);

      GameState gameState = gameInfo.getGameState();

      if (gameState.equals(GameState.PLAYERS_SPAWNED)) {
         initialPlayerNames = gameInfo.getPlayerNames();

      } else if (gameState.equals(GameState.NEW_ROUND)) {
         new ViewNewRound().show(gameInfo, initialPlayerNames);

      } else if (gameState.equals(GameState.PAUSE)) {
         new ViewPause().show();

      } else if (gameState.equals(GameState.CATEGORY_REQUIRED)) {
         final String selection = new ViewCategorySelector().show(gameInfo);
         game.setCategory(selection);

      } else if (gameState.equals(GameState.HUMAN_BOOTED)) {
         new ViewHumanBooted().show(gameInfo);
         new ViewPause().show();

      } else if (gameState.equals(GameState.ROUND_COMPLETE)) {
         new ViewRoundSummary().show(gameInfo);

      } else if (gameState.equals(GameState.GAME_COMPLETE)) {
         new ViewGameComplete().show(gameInfo);
         try {
            dbConnection.establishDBConnection();
            passDBStats(gameInfo);
            dbConnection.commit(); //Commit the DB object data to the database
            dbConnection.closeDBConnection();
         } catch (SQLException | ClassNotFoundException e) {
            new ViewDBError().show(e.getMessage());
         }
      }
   }

   // Get latest data from model and feed to DB object.
   private void passDBStats(GameInfo gameInfo) {
      dbConnection.setGameDraws(gameInfo.getNumDraws());
      // TODO: Add required getters to gameInfo.
//      dbConnection.setGameWinnerIsHuman(gameAPI.getGameInfo().getWinnerHuman());
//      dbConnection.setGameWinnerName(gameAPI.getGameInfo().getWinnerName());
//      dbConnection.setNumGameRounds(gameAPI.getRound());
      dbConnection.setPlayerRounds(gameInfo.getHumanRoundsWon());
   }
}
