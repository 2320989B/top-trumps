package commandline;

import model.GameInfo;

class ViewHumanBooted {

   void show(GameInfo gameInfo) {
      ViewUtils.indent();
      System.out.print("You have lost! ");

      if (gameInfo.getPlayerNames().size() > 1) {
         System.out.print("Now be a good sport and let the AI finish...");
      }

      System.out.println();
   }
}
