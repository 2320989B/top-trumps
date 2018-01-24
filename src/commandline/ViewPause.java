package commandline;

import java.util.Scanner;

/**
 * The ViewPause class is responsible for interrupting application flow until
 * the user presses ENTER.
 */
class ViewPause {

   /**
    * Show the pause screen and wait for user input.
    */
   void show() {
      final int INDENT_WIDTH = 1;
      ViewUtils.indent(INDENT_WIDTH);
      System.out.print("Press ENTER to proceed...");
      new Scanner(System.in).nextLine();
   }

}
