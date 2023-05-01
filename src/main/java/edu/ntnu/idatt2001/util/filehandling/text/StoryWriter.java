package edu.ntnu.idatt2001.util.filehandling.text;

import edu.ntnu.idatt2001.model.game.Story;

import java.io.FileWriter;
import java.io.IOException;

public class StoryWriter {

  private StoryWriter() {
    // Empty
  }

  public static void writeToFile(Story story) {
    try (FileWriter fileWriter = new FileWriter(story.getTitle() + ".paths")) {
      fileWriter.write(story.getAsString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
