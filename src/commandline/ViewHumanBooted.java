package commandline;

import java.util.List;

class ViewHumanBooted {

   void show(List<String> playerNames) {
      ViewUtils.indent();
      System.out.print("You have lost! ");

      if (playerNames.size() > 1) {
         System.out.print("Now be a good sport and let the AI finish...");
      }

      System.out.println();
   }
}
