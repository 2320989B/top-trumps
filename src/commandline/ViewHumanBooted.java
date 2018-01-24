package commandline;

class ViewHumanBooted {

   void show() {
      final int INDENT_WIDTH = 1;
      ViewUtils.indent(INDENT_WIDTH);
      System.out.println("You have lost! Now be a good sport and let the AI " +
           "finish...");
      System.out.println();
   }
}
