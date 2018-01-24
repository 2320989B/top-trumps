package commandline;

import java.util.List;
import java.util.Map;
import static commandline.CharCodes.*;

/**
 * The ViewRoundSummary class is responsible for displaying information relevant
 * to the end of a new round:
 * The selected category.
 * The result.
 */
class ViewRoundSummary {

   /**
    * Show the summary.
    *
    * @param activeCategory the active category
    * @param roundWinner    the round winner
    */
   void show(String activeCategory, String roundWinner,
             List<String> playerNames, List<String> allTopCardTitles,
             List<Map<String, Integer>> allTopCards, String activePlayer) {

      // Character width of a card.
      final int CARD_WIDTH = 25;

      // Available characters between vertical boundaries.
      final int MIDDLE_WIDTH = CARD_WIDTH - 2;

      // Max field width of value column
      final int VALUE_WIDTH = 3;

      final int INDENT_WIDTH = 1;

      final int H_GAP = 2;

      ViewUtils.printHorizontalCardStyle(MIDDLE_WIDTH, VALUE_WIDTH, allTopCardTitles,
              allTopCards, activeCategory, H_GAP);

      final int fieldLength = CARD_WIDTH - INDENT_WIDTH + H_GAP;
      String formatString = "%-" + fieldLength + "s";
      for (String playerName : playerNames) {
         ViewUtils.indent(INDENT_WIDTH);
         String winnerMarker = "";
         if (playerName.equals(roundWinner)) {
            winnerMarker = ROUND_WINNER.getCode();
         }
         System.out.print(String.format(formatString, playerName + " "
                 + winnerMarker));
      }
      System.out.println();
      System.out.println();

      ViewUtils.indent(INDENT_WIDTH);
      if (roundWinner == null) {
         System.out.println(activePlayer + " picks category " +
                 activeCategory + " and draws.");
      } else if (activePlayer.equals(roundWinner)) {
         System.out.println(activePlayer + " picks category " +
                 activeCategory + " and wins!");
      }  else {
         System.out.println(activePlayer + " picks category " +
         activeCategory + " and loses to " + roundWinner + ".");
      }
      System.out.println();
   }

}
