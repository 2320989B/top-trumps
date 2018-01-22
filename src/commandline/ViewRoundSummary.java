package commandline;

public class ViewRoundSummary {

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
