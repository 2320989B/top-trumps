package commandline;

import model.GameInfo;

class ViewGameComplete {
   void show(GameInfo gameInfo) {

      // Extract required values from gameInfo.
      String winner = gameInfo.getRoundWinnerName();

      ViewUtils.indent();
      if (winner == null) {
         System.out.println("Game Over: Stalemate!");
      } else {
         System.out.println("Game Over: " + winner + " wins the game!");
      }
   }
}