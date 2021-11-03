package pl.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.events.dao.Event;
import pl.events.dao.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EventManager {

   public static final String DEFAULT_FILEPATH = "..\\";
   public static final String DEFAULT_FILENAME = "logfile.txt";

   /**
    * Class main - reading events from the source file, flagging those longer than 4ms and writing the results
    *
    * @param args Standard param for reading run arguments
    */
   public static void main(String[] args) {

      Integer finishCode = 0;
      String jsonLine;
      ObjectMapper jsonMapper = new ObjectMapper();
      Event currentEvent;
      Long duration;
      Map<String, Event> eventMap = new HashMap<String, Event>();

      FileManager fileManager = new FileManager();
      String fileName = fileManager.getFilePathAndName();
      File sourceFile = new File(fileName);

      try {
         Scanner sourceFileScanner = new Scanner(sourceFile);

         while (sourceFileScanner.hasNextLine()) {

            jsonLine = sourceFileScanner.nextLine();
            currentEvent = jsonMapper.readValue(jsonLine, Event.class);

            if (eventMap.containsKey(currentEvent.getId())) {

               duration = eventMap.get(currentEvent.getId()).getTimestamp() - currentEvent.getTimestamp();
               if (Math.abs(duration) > 4) {
                  logEvent(currentEvent, duration);
               }

            } else {
               eventMap.put(currentEvent.getId(), currentEvent);
            }

         }

         sourceFileScanner.close();

      } catch (IOException e) {
         System.out.println("Error reading the source file: " + fileName);
         finishCode = -1;
      }

      // TODO writing to a file

      System.out.println("Program finished with the code " + finishCode);

   }

   private static void logEvent(Event currentEvent, Long duration) {

      System.out.println("Event: " + currentEvent);
      System.out.println("Duration " + duration);

      // TODO writing logged events to a map
      /*
      - first: to create another class for saving events (same fields as Event, but:
         without 'status' and
         with 'duration' instead of 'time stamp'
       */
      // TODO input data validation while flagging
      /*
         for example, checking, if the FINISHED status is later than STARTED
       */

   }

   // TODO enhancements:
   /*
   - writing to a database, not a file - using @Entity, @Column, JpaRepository
   - unit tests using JUnit (@Test)
   - error logging to a file (logback)
    */

}
