package commandline;

import model.Game;
import persistence.PostgresPersistence;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

   private Boolean writeGameLogsToFile;

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
         Game game = new Game();
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

   }

}
