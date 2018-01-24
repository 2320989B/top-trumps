package commandline;

class ViewGameComplete {
   void show(String winner) {
      final int INDENT_WIDTH = 1;
      ViewUtils.indent(INDENT_WIDTH);
      if (winner == null) {
         System.out.println("Game Over: Stalemate!");
      } else {
         System.out.println("Game Over: " + winner + " wins the game!");
      }
   }
}