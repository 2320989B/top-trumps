package commandline;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static commandline.DivStyles.*;

/**
 * The ViewUtils class provides static utility methods for displaying
 * information to the user via System.out, and for prompting for user input
 * via System.in.
 */
final class ViewUtils {

   // Private constructor to prevent accidental instantiation of this
   // utility class.
   private ViewUtils() {
   }

   /**
    * Print the topmost boundary line of a card representation.
    *
    * @param middleWidth the middle width of the card representation.
    */
   static void printTopBoundary(int middleWidth) {
      // Print top boundary.
      System.out.print(TOP_LEFT.getCode());
      for (int i = 0; i < middleWidth; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(TOP_RIGHT.getCode() + "\n");
   }

   /**
    * Print the title line of a card representation.
    *
    * @param middleWidth the middle width of the card representation.
    * @param title       the card title
    */
   static void printTitle(int middleWidth, String title) {
      System.out.print(VERTICAL.getCode());
      System.out.print(String.format("%-" + middleWidth + "s", title));
      System.out.print(VERTICAL.getCode() + "\n");
   }

   /**
    * Print the middle boundary line of a card representation.
    *
    * @param middleWith the middle width of the card representation.
    */
   static void printMiddleBoundary(int middleWith) {
      System.out.print(TEE_LEFT.getCode());
      for (int i = 0; i < middleWith; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(TEE_RIGHT.getCode() + "\n");
   }

   /**
    * Print a line containing a card property.
    *
    * @param middleWidth the middle width of the card representation.
    * @param valueWidth  the max width of the value field.
    * @param property    the card property name.
    * @param value       the card property value.
    */
   static void printCardProperty(int middleWidth, int valueWidth,
                                 String property, String value) {
         System.out.print(VERTICAL.getCode());

         // Property name is formatted to be left justified, with an explicit
         // limit on allowable field length (defined by middleWidth -
         // valueWidth). String lengths which exceed this width will be
         // truncated.
         String propertyFormatString = "%-" + (middleWidth - valueWidth) +
                 "." + (middleWidth - valueWidth) + "s";
         System.out.print(String.format(propertyFormatString, property));

         // Property value is formatted to be right justified, with an explicit
         // limit on allowable field length (defined by valueWidth). String
         // lengths which exceed this width will be truncated.
         String valueFormatString = "%" + valueWidth + "." + valueWidth + "s";
         System.out.print(String.format(valueFormatString, value));

         System.out.print(VERTICAL.getCode() + "\n");
   }

   /**
    * Print the bottom boundary line of a card representation.
    *
    * @param middleWidth the middle width
    */
   static void printBottomBoundary(int middleWidth) {
      System.out.print(BOTTOM_LEFT.getCode());
      for (int i = 0; i < middleWidth; i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.print(BOTTOM_RIGHT.getCode() + "\n");
   }

   /**
    * Print a list of items as a numbered list, and prompt the user for a
    * selection.
    *
    * @param menuItems the menu items.
    * @return int representing the selection.
    */
   static int prompt(Collection menuItems) {
      // Print the menu.
      System.out.println("");
      int i = 1;
      for (Object item : menuItems) {
         System.out.println(i++ + ": " + item.toString());
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