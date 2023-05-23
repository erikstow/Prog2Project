package edu.ntnu.idatt2001.model.game;

import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Story class")
class StoryTest {

  Story story;
  Passage openingPassage;

  @BeforeEach
  void setUp() {
    openingPassage = new Passage("Title", "Title");
    story = new Story("Title", openingPassage);
  }

  @Nested
  @DisplayName("Getters for Story class")
  class Getters {
    @Test
    @DisplayName("getTitle should return the story's title")
    void getTitle() {
      assertEquals("Title", story.getTitle());
    }

    @Test
    @DisplayName("getOpeningPassage should return the story's opening passage")
    void getOpeningPassage() {
      assertEquals(openingPassage, story.getOpeningPassage());
    }

    @Test
    @DisplayName("getPassages should return all passages including the opening passage")
    void getPassagesOnlyOpeningPassage() {
      Map<Link, Passage> map = new HashMap<>();
      map.put(new Link(openingPassage.getTitle(), openingPassage.getTitle()), openingPassage);
      Collection<Passage> coll = map.values();
      assertTrue(coll.containsAll(story.getPassages()));
    }

    @Test
    @DisplayName("getPassages should return all passages including the opening passage and an additional passage")
    void getPassagesWithTwoPassages() {
      Passage passage = new Passage("Title1", "Title2");
      story.addPassage(passage);
      Map<Link, Passage> map = new HashMap<>();
      map.put(new Link(openingPassage.getTitle(), openingPassage.getTitle()), openingPassage);
      map.put(new Link(passage.getTitle(), passage.getTitle()), passage);

      Collection<Passage> coll = map.values();
      assertTrue(coll.containsAll(story.getPassages()));
    }

    @Test
    @DisplayName("getOpeningPassage should return the opening passage")
    void getPassageOpeningPassage() {
      assertEquals(openingPassage, story.getPassage(new Link("Title", "Title")));
    }

    @Test
    @DisplayName("getPassage should return a specific passage that is not the opening passage")
    void getPassageNotOpeningPassage() {
      Passage passage = new Passage("TT", "GG");
      story.addPassage(passage);
      assertEquals(passage, story.getPassage(new Link("TT", "TT")));
    }

    @Test
    @DisplayName("getPassages should return all passages in the story")
    void getPassages() {
      story.addPassage(new Passage("lsd", "sldkf"));
      assertEquals(2, story.getPassages().size());
    }

    @Test
    @DisplayName("getBrokenLinks should return an empty list when there are no broken links")
    void getBrokenLinksWithNoBrokenLinks() {
      Passage newPassage = new Passage("New title", "New content");
      Link newLink = new Link("Link title", "New title");
      openingPassage.addLink(newLink);
      story.addPassage(newPassage);

      assertTrue(story.getBrokenLinks().isEmpty());
    }

    @Test
    @DisplayName("removePassage should remove a passage that has no references")
    void removePassageWithNoReferences() {
      Passage newPassage = new Passage("new title", "new content");
      Link newLink = new Link("title", "new title");
      story.addPassage(newPassage);
      story.removePassage(newLink);

      assertEquals(1, story.getPassages().size());
    }

    @Test
    @DisplayName("removePassage should not remove a passage that has references")
    void removePassageWithReference() {
      Passage newPassage = new Passage("New title", "New content");
      Link newLink = new Link("Link text", "New title");
      openingPassage.addLink(newLink);
      story.addPassage(newPassage);
      story.removePassage(newLink);

      assertEquals(2, story.getPassages().size());
    }

    @Test
    @DisplayName("getBrokenLinks should return a list with a broken link")
    void getBrokenLinksWithBrokenLink() {
      Link newLink = new Link("Test", "Test");
      openingPassage.addLink(newLink);

      List<Link> brokenLinks = story.getBrokenLinks();
      assertEquals(1, brokenLinks.size());
      assertEquals(newLink, brokenLinks.get(0));
    }

    @Test
    @DisplayName("getBrokenLinks should return a list with both broken and unbroken links")
    void getBrokenLinksWithBothBrokenAndUnbrokenLinks() {
      Passage newPassage = new Passage("New title", "New content");
      Link brokenLink = new Link("Broken", "Broken");
      openingPassage.addLink(brokenLink);

      Link okayLink = new Link("Okay link", "New title");
      openingPassage.addLink(okayLink);

      Link newBroken = new Link("Also broken", "Also broken");
      newPassage.addLink(newBroken);

      newPassage.addLink(new Link("Opening", openingPassage.getTitle()));

      story.addPassage(newPassage);
      assertEquals(2, story.getBrokenLinks().size());
      assertTrue(story.getBrokenLinks().contains(brokenLink));
      assertTrue(story.getBrokenLinks().contains(newBroken));
    }
  }


  @Nested
  @DisplayName("Exception handling for Story class")
  class ExceptionHandling {
    @Test
    @DisplayName("addPassage with null should throw NullPointerException")
    void addPassageNullPassage() {
      assertThrows(NullPointerException.class, () -> story.addPassage(null));
    }

    @Test
    @DisplayName("New Story with null title should throw NullPointerException")
    void createStoryWithNullTitle() {
      assertThrows(NullPointerException.class, () -> new Story(null, openingPassage));
    }

    @Test
    @DisplayName("New Story with null Passage should throw NullPointerException")
    void createStoryWithNullPassage() {
      assertThrows(NullPointerException.class, () -> new Story("hello", null));
    }

    @Test
    @DisplayName("getPassage with null should throw NullPointerException")
    void getPassageWithNullLink() {
      assertThrows(NullPointerException.class, () -> story.getPassage(null));
    }

    @Test
    @DisplayName("removePassage with null should throw NullPointerException")
    void removePassageWithNullLink() {
      assertThrows(NullPointerException.class, () -> story.removePassage(null));
    }
  }
}