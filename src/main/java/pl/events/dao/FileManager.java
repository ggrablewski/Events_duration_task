package pl.events.dao;

import pl.events.EventManager;

import java.util.Scanner;

/**
 * This class simplifies some of the file input/output operations
 */
public class FileManager {

   /**
    * Takes the path to the source file
    *
    * @return The path and the name of the source file
    */
   public String getFilePathAndName() {
      Scanner consoleScanner = new Scanner(System.in);
      System.out.println(String.format("Default path and file name is %s%s", EventManager.DEFAULT_FILEPATH, EventManager.DEFAULT_FILENAME));
      System.out.println("Enter path to the source file (ENTER = default): ");
      String filePathAndName = consoleScanner.nextLine();

      if (filePathAndName == "") {
         filePathAndName = EventManager.DEFAULT_FILEPATH;
      }
      filePathAndName += EventManager.DEFAULT_FILENAME;

      return filePathAndName;
   }


}
