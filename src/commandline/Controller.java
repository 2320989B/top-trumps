package commandline;

import model.Game;
import model.GameState;
import persistence.PostgresPersistence;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

   private Boolean writeGameLogsToFile;
   private Game game;

   Controller (Boolean writeGameLogsToFile) {
      this.writeGameLogsToFile = writeGameLogsToFile;
   }

   public Boolean start() {
      // There are always 4 AI players in command line mode.
      final int NUM_AI_PLAYERS = 4;

      // First, we present the main menu to the user and await their response.
      ViewMainMenu viewMainMenu = new ViewMainMenu();
      int selection = viewMainMenu.show();

      // Now handle the response appropriately.
      if (selection == 1) {
         // 1. Start a new game.
         game = new Game();
         // Observe the game.
         game.addObserver(this);
         // TODO: Remove, here for testing only.
         System.out.println("NEW GAME OPTION SELECTED");
         // TODO: To be implemented.

      } else if (selection == 2) {
         // 2. View statistics.
         PostgresPersistence postgresPersistence = new PostgresPersistence();
         // TODO: Remove, here for testing only.
         System.out.println("VIEW STATISTICS OPTION SELECTED");
         // TODO: To be implemented.

      } else {
         // 3. Quit.
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
