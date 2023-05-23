package edu.ntnu.idatt2001.util;

import edu.ntnu.idatt2001.model.actions.player.Action;
import edu.ntnu.idatt2001.model.actions.player.GoldAction;
import edu.ntnu.idatt2001.model.actions.player.InventoryAction;
import edu.ntnu.idatt2001.model.actions.player.ScoreAction;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.util.StoryReader;
import edu.ntnu.idatt2001.util.StoryWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for StoryWriter")
class StoryWriterTest {

  @Test
  @DisplayName("Write should write the story to a file and the fetched story should be the same as the original story")
  void write() throws IOException {
    Passage passage = new Passage("Passage title", "Passage content");
    Link link = new Link("Link text", "Link Reference");
    passage.addLink(link);
    Action gold = new GoldAction(50);
    link.addAction(gold);

    Passage secondPassage = new Passage("Second passage title", "Second passage content");
    Passage thirdPassage = new Passage("Third passage title", "Third passage content");
    Link firstLink = new Link("First link text", "First reference");
    Link secondLink = new Link("Second link text", "Second reference");
    secondPassage.addLink(firstLink);
    secondPassage.addLink(secondLink);
    Action score = new ScoreAction(50);
    Action inventory = new InventoryAction("Sword");
    firstLink.addAction(score);
    firstLink.addAction(inventory);

    Story story = new Story("Story title", passage);
    story.addPassage(secondPassage);
    story.addPassage(thirdPassage);

    StoryWriter.writeToFile(story);

    Path path = Paths.get(story.getTitle() + ".paths");
    byte[] fileBytes = Files.readAllBytes(path);
    String fetchedStory = new String(fileBytes, StandardCharsets.UTF_8);

    assertEquals(story.getAsString(), fetchedStory);
  }
}