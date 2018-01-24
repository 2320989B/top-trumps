package commandline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The ViewCategorySelector class is responsible for displaying a list of
 * categories.
 * Menu elements are numbered, and the user is prompted to enter the number
 * corresponding to their menu selection.
 */
public class ViewCategorySelector {

   /**
    * Show the category menu.
    *
    * @return a String representing the selected category.
    */
   String show(Map<String, Integer> cardProperties) {
      System.out.println("Select a category:");

      // Extract the category names (key) and store in a List.
      List<String> descriptionList = new ArrayList<>(cardProperties.keySet());

      // Display prompt and return the user selection.
      int response = ViewUtils.prompt(descriptionList);

      // Return the category corresponding to the selected index.
      // - 1 to offset zero-based index numbering.
      return descriptionList.get(response - 1);
   }

   public static void main(String[] args) {
      // TODO: Remove, here for testing only.
      Map<String, Integer> cardProperties = new LinkedHashMap<>();
      cardProperties.put("Size", 4);
      cardProperties.put("Speed", 5);
      cardProperties.put("Range", 7);
      cardProperties.put("Firepower", 3);
      cardProperties.put("Cargo", 4);

      String response = new ViewCategorySelector().show(cardProperties);
      System.out.println("User entered: " + response);
   }

}
