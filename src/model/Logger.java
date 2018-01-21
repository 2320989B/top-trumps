package model;

public class Logger {

   private Boolean writeGameLogsToFile;
   private String logFilePath;
   final private String prefix = "";

   Logger(String logFilePath, Boolean writeGameLogsToFile) {
      this.logFilePath = logFilePath;
      this.writeGameLogsToFile = writeGameLogsToFile;
   }

   void log(String logMessage) {
      // TODO: WIP, print to console will be changed to print to file once everything is working.
      if (writeGameLogsToFile) {
         System.out.println(prefix + logMessage);
         for (int i = 0; i < (prefix.length() + logMessage.length()); i++) {
            System.out.print("-");
         }
         System.out.println();
      }
   }

}
