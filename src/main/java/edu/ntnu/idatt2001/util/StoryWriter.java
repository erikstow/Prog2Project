package edu.ntnu.idatt2001.util;

import edu.ntnu.idatt2001.model.game.Story;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The StoryWriter class provides the functionality to write a story object into a file.
 * This is a utility class and cannot be instantiated.
 */
public class StoryWriter {

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private StoryWriter() {
    // Empty
  }

  /**
   * Writes a story to a file.
   * The filename is derived from the story's title and it is written in the ".paths" format.
   * In case of an IOException, it prints the stack trace.
   *
   * @param story the Story object to be written into a file.
   */
  public static void writeToFile(Story story) {
    try (FileWriter fileWriter = new FileWriter(story.getTitle() + ".paths")) {
      fileWriter.write(story.getAsString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
