package edu.ntnu.idatt2001.util;

import edu.ntnu.idatt2001.model.actions.player.Action;
import edu.ntnu.idatt2001.model.actions.player.GoldAction;
import edu.ntnu.idatt2001.model.actions.player.InventoryAction;
import edu.ntnu.idatt2001.model.actions.player.ScoreAction;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for the story reader")
class StoryReaderTest {
  @Test
  @DisplayName("Read should read the story from the written file and return the same story")
  void readStoryWrittenToFile() {
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
    Story fetchedStory;
    try {
      fetchedStory = StoryReader.read(story.getTitle() + ".paths");
      assertEquals(story.getAsString(), fetchedStory.getAsString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}