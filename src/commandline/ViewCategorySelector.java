package commandline;

import java.util.HashMap;
import java.util.Map;

/**
 * The ViewCategorySelector class is responsible for displaying a list of
 * categories.
 * Menu elements are numbered, and the user is prompted to enter the number
 * corresponding to their menu selection.
 */
public class ViewCategorySelector {
   int show(Map<String, Integer> cardProperties) {
      return ViewUtils.prompt(cardProperties.keySet());
   }

   public static void main(String[] args) {
      // TODO: Remove, here for testing only.
      Map<String, Integer> cardProperties = new HashMap<>();
      cardProperties.put("Size", 4);
      cardProperties.put("Speed", 5);
      cardProperties.put("Range", 7);
      cardProperties.put("Firepower", 3);
      cardProperties.put("Cargo", 4);
      int response = new ViewCategorySelector().show(cardProperties);
      System.out.println("User entered: " + response);
   }

}
