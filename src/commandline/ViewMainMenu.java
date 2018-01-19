package commandline;

import java.util.*;

public class ViewMainMenu {

   public int show() {

      // Define the desired menu items.
      List<String> menuItems = new ArrayList<String>();
      menuItems.add("Start a new game."); // Selection 1
      menuItems.add("View statistics."); // Selection 2
      menuItems.add("Quit"); // Selection 3

      // Print the menu.
      System.out.println("");
      int i = 1;
      for (String item : menuItems) {
         System.out.println(i++ + ":" + item);
      }
      // Prompt for a selection from the user.
      System.out.print("Selection: ");

      Scanner in = new Scanner(System.in);
      int selection = 0;
      for (;;) {
         try {
            // Grab the user input.
            selection = Integer.parseInt(in.nextLine());

            // Define limits of allowable selections.
            final int LOWER_BOUND = 1;
            final int UPPER_BOUND = menuItems.size();

            // Check selection is within a valid range.
            if (selection >= LOWER_BOUND && selection <= UPPER_BOUND) {
               break;
            } else {
               System.out.print(String.format("Entry must be between %d and %d: ",
                       LOWER_BOUND, UPPER_BOUND));
            }
         } catch (NumberFormatException e) {
            System.out.print("Entry must be an integer: ");
         }
      }
      return selection;
   }

}
