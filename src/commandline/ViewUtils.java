package commandline;

import java.util.List;
import java.util.Scanner;

/**
 * The ViewUtils class provides static utility methods for displaying and
 * prompting for user input.
 */
final class ViewUtils {

   // Private constructor to prevent accidental instantiation of this
   // utility class.
   private ViewUtils() {
   }

   /**
    * Print a list of items as a numbered list, and prompt the user for a
    * selection.
    *
    * @param menuItems the menu items.
    * @return int representing the selection.
    */
   static int prompt(List menuItems) {
      // Print the menu.
      System.out.println("");
      int i = 1;
      for (Object item : menuItems) {
         System.out.println(i++ + ":" + item.toString());
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
