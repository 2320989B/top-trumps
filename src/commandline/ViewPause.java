package commandline;

import java.util.Scanner;

/**
 * The ViewPause class is responsible for interrupting application flow until
 * the user presses ENTER.
 */
public class ViewPause {

   /**
    * Show the pause screen and wait for user input.
    */
   void show() {
      System.out.print("Press ENTER to proceed...");
      new Scanner(System.in).nextLine();
   }

}
