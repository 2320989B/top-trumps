package commandline;

class ViewGameComplete {
   void show(String winner) {
      ViewUtils.indent();
      if (winner == null) {
         System.out.println("Game Over: Stalemate!");
      } else {
         System.out.println("Game Over: " + winner + " wins the game!");
      }
   }
}