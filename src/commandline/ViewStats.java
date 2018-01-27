package commandline;

import static commandline.CharCodes.HORIZONTAL;

class ViewStats {
   void show(int gameCount, int AIWinCount, int humanWinCount,
             double avgDrawCount, int maxRoundCount) {

      final int COL_WIDTH = 30;

      System.out.println();
      String title = "STATISTICS";
      System.out.println(title);
      for (int i = 0; i < title.length(); i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.println();

      String formatString = "%-" + COL_WIDTH + "s";
      //String formatString = "%-30s";
      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "Games played overall:", gameCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "AI Wins:", AIWinCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "Human Wins:", humanWinCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%.2f",
              "Average draws:", avgDrawCount));

      ViewUtils.indent();
      System.out.println(String.format(formatString + "%d",
              "Max rounds in one game:", maxRoundCount));
   }

   public static void main(String[] args) {
      new ViewStats().show(10, 10, 10, 10, 10);
   }


}
