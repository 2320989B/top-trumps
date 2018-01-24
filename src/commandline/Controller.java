package commandline;

import model.Game;
import model.GameState;
//import persistence.PostgresPersistence;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The Controller class is responsible for triggering appropriate Views,
 * handling any returned user input, and reacting to Game events (game states).
 */
class Controller implements Observer {

   private Boolean writeGameLogsToFile;
   private Game game;
   private List<String> initialPlayerNames;
//   private PostgresPersistence dbConnection;

   /**
    * Instantiates a new Controller.
    *
    * @param writeGameLogsToFile flag to indicate if game logs should be
    *                            written to a debug file.
    */
   Controller(Boolean writeGameLogsToFile) {
      this.writeGameLogsToFile = writeGameLogsToFile;
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
      // 1. Start a new game.
      if (selection == 1) {
         game = new Game(NUM_AI_PLAYERS, DECK_INPUT_FILE, writeGameLogsToFile);
         // Observe the game.
         game.addObserver(this);
         game.newGame();

         // 2. View statistics.
      } else if (selection == 2) {
//         dbConnection = new PostgresPersistence();

         // 3. Quit.
      } else {
         return true;
      }

      return false;

   }

   public void update(Observable observable, Object o) {
      GameState gameState = game.getGameState();

      if (gameState.equals(GameState.PLAYERS_SPAWNED)) {
         initialPlayerNames = game.getPlayerNames();

      } else if (gameState.equals(GameState.NEW_ROUND)) {
         new ViewNewRound().show(game.getRound(), game.getCardDescription(),
                 game.getCardCategories(), game.getActivePlayer(),
                 initialPlayerNames, game.getPlayerNames(),
                 game.getNumCardsInHumanHand());

      } else if (gameState.equals(GameState.PAUSE)) {
         new ViewPause().show();

      } else if (gameState.equals(GameState.CATEGORY_REQUIRED)) {
         final String selection = new ViewCategorySelector().show(
                 game.getCardCategories());
         game.setCategory(selection);

      } else if (gameState.equals(GameState.HUMAN_BOOTED)) {
         new ViewHumanBooted().show();
         new ViewPause().show();

      } else if (gameState.equals(GameState.ROUND_COMPLETE)) {
         new ViewRoundSummary().show(game.getActiveCategory(),
                 game.getRoundWinner(), game.getPlayerNames(),
                 game.getAllTopCardTitles(), game.getAllTopCards(),
                 game.getActivePlayer());

      } else if (gameState.equals(GameState.GAME_COMPLETE)) {
//          game.setDBValues(dbConnection);
//          dbConnection.commit();
         new ViewGameComplete().show(game.getRoundWinner());
      }
   }

}
