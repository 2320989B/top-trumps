package commandline;

class ViewDBError {

   void show(String message) {
      ViewUtils.indent();
      System.out.println("Database error: " + message);
   }

}
