package commandline;

class ViewDBError {

   void show(String message) {
      ViewUtils.indent(1);
      System.out.println("Database error: " + message);
   }

}
