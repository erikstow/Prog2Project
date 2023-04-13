package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.actions.Action;
import edu.ntnu.idatt2001.actions.GoldAction;
import edu.ntnu.idatt2001.actions.InventoryAction;
import edu.ntnu.idatt2001.actions.ScoreAction;
import edu.ntnu.idatt2001.filehandling.StoryReader;
import edu.ntnu.idatt2001.filehandling.StoryWriter;
import edu.ntnu.idatt2001.game.Link;
import edu.ntnu.idatt2001.game.Passage;
import edu.ntnu.idatt2001.game.Story;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoryWriterTest {

  @Test
  void write() throws IOException {
    Passage passage = new Passage("Passage title", "Passage content");
    Link link = new Link("Link text", "Link Reference");
    passage.addLink(link);
    Action gold = new GoldAction(50);
    link.addAction(gold);

    Passage secondPassage = new Passage("Second passage title", "Second passage content");
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

    StoryWriter.writeToFile(story);

    Story fetchedStory = StoryReader.read(story.getTitle() + ".paths");
    assertEquals(story.getAsString(), fetchedStory.getAsString());
  }
}