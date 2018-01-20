package commandline;

import java.util.*;

/**
 * The ViewMainMenu class is responsible for displaying the main program menu.
 * Menu elements are numbered, and the user is prompted to enter the number
 * corresponding to their menu selection.
 */
class ViewMainMenu {

   /**
    * Show the main menu.
    *
    * @return an int representing the menu selection.
    */
   int show() {
      // Define the desired menu items.
      List<String> menuItems = new ArrayList<>();
      menuItems.add("Start a new game."); // Selection 1
      menuItems.add("View statistics."); // Selection 2
      menuItems.add("Quit"); // Selection 3

      // Display prompt and return the user selection.
      return ViewUtils.prompt(menuItems);
   }


   public static void main(String[] args) {
      // TODO: Remove, here for testing only.
      int response = new ViewMainMenu().show();
      System.out.println("User entered: " + response);
   }

}