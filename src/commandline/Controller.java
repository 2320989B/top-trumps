package commandline;

import model.Game;
import model.GameState;
import persistence.PostgresPersistence;

import java.util.Observable;
import java.util.Observer;

/**
 * The Controller class is responsible for triggering appropriate Views,
 * handling any returned user input, and reacting to Game events (game states).
 */
class Controller implements Observer {

   private Boolean writeGameLogsToFile;
   private Game game;

   /**
    * Instantiates a new Controller.
    *
    * @param writeGameLogsToFile flag to indicate if game logs should be
    *                            written to a debug file.
    */
   Controller (Boolean writeGameLogsToFile) {
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

      // First, we present the main menu to the user and await their response.
      final int selection = new ViewMainMenu().show();

      // Now handle the response appropriately.
      // 1. Start a new game.
      if (selection == 1) {
         game = new Game();
         // Observe the game.
         game.addObserver(this);
         // TODO: Remove, here for testing only.
         System.out.println("NEW GAME OPTION SELECTED");
         // TODO: To be implemented.

      // 2. View statistics.
      } else if (selection == 2) {
         PostgresPersistence postgresPersistence = new PostgresPersistence();
         // TODO: Remove, here for testing only.
         System.out.println("VIEW STATISTICS OPTION SELECTED");
         // TODO: To be implemented.

      // 3. Quit.
      } else {
         return true;
      }

      // TODO: Remove, here for testing only.
      System.out.println("CONTROLLER LOGIC COMPLETE, RETURNING TO MAIN MENU");
      return false;

   }

   public void update(Observable observable, Object o) {
      GameState gameState = game.getGameState();// TODO: To be implemented.
      if (gameState.equals(GameState.NEW_ROUND)) {
         // TODO: Remove, here for testing only.
         System.out.println("RECEIVED GAMESTATE=NEW_ROUND");
         // TODO: Need to get required values to feed .show():
         // int round, String cardName, Map<String, Integer> cardProperties,
         // String activePlayer
         // new ViewNewRound().show();

      }  else if (gameState.equals(GameState.CATEGORY_REQUIRED)) {
         // TODO: Remove, here for testing only.
         System.out.println("RECEIVED GAMESTATE=CATEGORY_REQUIRED");

      }  else if (gameState.equals(GameState.ROUND_COMPLETE)) {
         // TODO: Remove, here for testing only.
         System.out.println("RECEIVED GAMESTATE=ROUND_COMPLETE");

      }  else if (gameState.equals(GameState.GAME_COMPLETE)) {
        // TODO: Remove, here for testing only.
        System.out.println("RECEIVED GAMESTATE=GAME_COMPLETE");
        }
   }

}
