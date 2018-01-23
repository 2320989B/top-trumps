package commandline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The ViewRoundSummary class is responsible for displaying information relevant
 * to the end of a new round:
 * The selected category.
 * The result.
 */
public class ViewRoundSummary {

   /**
    * Show the summary.
    *
    * @param activeCategory the active category
    * @param roundWinner    the round winner
    */
   void show(String activeCategory, String roundWinner,
             List<String> playerNames, List<String> allTopCardTitles,
             List<Map<String, Integer>> allTopCards) {

      // Character width of a card.
      final int CARD_WIDTH = 25;

      // Available characters between vertical boundaries.
      final int MIDDLE_WIDTH = CARD_WIDTH - 2;

      // Max field width of value column
      final int VALUE_WIDTH = 3;

      System.out.println(String.format("%-20s %s", "Selected Category:",
              activeCategory));

      String resultString;
      if (roundWinner == null) {
         resultString = "Draw...";
      } else {
         resultString = roundWinner + " wins!";
      }
      System.out.println(String.format("%-20s %s", "Result:", resultString));

      ViewUtils.printHorizontalCardStyle(MIDDLE_WIDTH, VALUE_WIDTH, allTopCardTitles,
              allTopCards);

      for (String playerName : playerNames) {
         System.out.print(String.format("%-27s", playerName));
      }
      System.out.println();

   }

   public static void main(String[] args) {
      //new ViewRoundSummary().show("Firepower", "Player 1");
   }


}
