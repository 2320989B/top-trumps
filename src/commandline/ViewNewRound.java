package commandline;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The ViewNewRound class is responsible for displaying information relevant
 * to the start of a new round:
 * The round number.
 * A visual representation of the player's card, and its properties.
 * The active player name.
 */
public class ViewNewRound {

   /**
    * Show the category menu.
    *
    * @return an int representing the menu selection.
    */
   void show(int round, String cardName, Map<String, Integer> cardProperties,
             String activePlayer) {

      // Character width of a card.
      final int CARD_WIDTH = 25;

      // Available characters between vertical boundaries.
      final int MIDDLE_WIDTH = CARD_WIDTH - 2;

      // Max field width of value column
      final int VALUE_WIDTH = 3;

      // Print the round.
      System.out.println();
      System.out.println("ROUND " + round + " (" + activePlayer + ")");

      // Print the card.
      ViewUtils.printTopBoundary(MIDDLE_WIDTH);
      ViewUtils.printTitle(MIDDLE_WIDTH, cardName.toUpperCase());
      ViewUtils.printMiddleBoundary(MIDDLE_WIDTH);
      for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
         ViewUtils.printCardProperty(MIDDLE_WIDTH, VALUE_WIDTH,
                 entry.getKey(), entry.getValue().toString());
      }
      ViewUtils.printBottomBoundary(MIDDLE_WIDTH);

   }


   public static void main(String[] args) {
      // TODO: Remove, here for testing only.
      Map<String, Integer> cardProperties = new LinkedHashMap<>();
      cardProperties.put("Size", 4);
      cardProperties.put("Speed", 5);
      cardProperties.put("Range", 7);
      cardProperties.put("Firepower", 3);
      cardProperties.put("Cargo", 4);
      System.out.println(cardProperties.get("Cargo"));
      new ViewNewRound().show(1, "Constellation", cardProperties, "Bob");
   }

}
