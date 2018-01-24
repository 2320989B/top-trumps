package commandline;

import java.util.List;
import java.util.Map;

import static commandline.CharCodes.*;

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
             String activePlayer, List<String> initialPlayerNames,
             List<String> playerNames, int numCardsInHumanHand) {

      // Character width of a card.
      final int CARD_WIDTH = 25;

      // Available characters between vertical boundaries.
      final int MIDDLE_WIDTH = CARD_WIDTH - 2;

      // Max field width of value column
      final int VALUE_WIDTH = 3;

      // Print the round.
      System.out.println();
      String roundTitle = "ROUND " + round;
      System.out.println(roundTitle);
      for (int i = 0; i < roundTitle.length(); i++) {
         System.out.print(HORIZONTAL.getCode());
      }
      System.out.println();

      // Print the player list.
      for (String player : initialPlayerNames) {
         ViewUtils.indent();
         if (player.equals(activePlayer)) {
            System.out.print(PLAYER_ACTIVE.getCode());
         } else if (playerNames.contains(player)) {
            System.out.print(PLAYER_WAITING.getCode());
         } else {
            System.out.print(PLAYER_OUT_GAME.getCode());
         }
         System.out.print(" " + player);
         System.out.println();
      }
      System.out.println();
      ViewUtils.indent();
      System.out.println("Your hand:");

      // Print the players hand.
      ViewUtils.printStackedCardStyle(MIDDLE_WIDTH, VALUE_WIDTH, cardName,
              cardProperties, numCardsInHumanHand);
   }

}
