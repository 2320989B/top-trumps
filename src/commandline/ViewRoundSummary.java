package commandline;

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
   void show(String activeCategory, String roundWinner) {
      System.out.println(String.format("%-20s %s", "Selected Category:",
              activeCategory));

      String resultString;
      if (roundWinner == null) {
         resultString = "Draw...";
      } else {
         resultString = roundWinner + " wins!";
      }
      System.out.println(String.format("%-20s %s", "Result:", resultString));
   }

   public static void main(String[] args) {
      new ViewRoundSummary().show("Firepower", "Player 1");
   }


}
