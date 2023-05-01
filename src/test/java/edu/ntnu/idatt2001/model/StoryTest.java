package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Story;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoryTest {

  Story story;
  Passage openingPassage;

  @BeforeEach
  void setUp() {
    openingPassage = new Passage("Title", "Title");
    story = new Story("Title", openingPassage);
  }

  @Test
  void getTitle() {
    assertEquals("Title", story.getTitle());
  }

  @Test
  void getOpeningPassage() {
    assertEquals(openingPassage, story.getOpeningPassage());
  }

  @Test
  void getPassagesOnlyOpeningPassage() {
    Map<Link, Passage> map = new HashMap<>();
    map.put(new Link(openingPassage.getTitle(), openingPassage.getTitle()), openingPassage);
    Collection<Passage> coll = map.values();
    assertTrue(coll.containsAll(story.getPassages()));
  }

  @Test
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
  void getPassageOpeningPassage() {
    assertEquals(openingPassage, story.getPassage(new Link("Title", "Title")));
  }

  @Test
  void getPassageNotOpeningPassage() {
    Passage passage = new Passage("TT", "GG");
    story.addPassage(passage);
    assertEquals(passage, story.getPassage(new Link("TT", "TT")));
  }

  @Test
  void getPassages() {
    story.addPassage(new Passage("lsd", "sldkf"));
    assertEquals(2, story.getPassages().size());
  }

  @Test
  @DisplayName("Get broken links when there is no broken links")
  void getBrokenLinksWithNoBrokenLinks() {
    Passage newPassage = new Passage("New title", "New content");
    Link newLink = new Link("Link title", "New title");
    openingPassage.addLink(newLink);
    story.addPassage(newPassage);

    assertTrue(story.getBrokenLinks().isEmpty());
  }

  @Test
  @DisplayName("Remove passage that has no links leading to it")
  void removePassageWithNoReferences() {
    Passage newPassage = new Passage("new title", "new content");
    Link newLink = new Link("title", "new title");
    story.addPassage(newPassage);
    story.removePassage(newLink);

    assertEquals(1, story.getPassages().size());
  }


  @Test
  @DisplayName("Remove passage that has links leading to it")
  void removePassageWithReference() {
    Passage newPassage = new Passage("New title", "New content");
    Link newLink = new Link("Link text", "New title");
    openingPassage.addLink(newLink);
    story.addPassage(newPassage);
    story.removePassage(newLink);

    assertEquals(2, story.getPassages().size());
  }

  @Test
  @DisplayName("Get broken links with a broken link")
  void getBrokenLinksWithBrokenLink() {
    Link newLink = new Link("Test", "Test");
    openingPassage.addLink(newLink);

    List<Link> brokenLinks = story.getBrokenLinks();
    assertEquals(1, brokenLinks.size());
    assertEquals(newLink, brokenLinks.get(0));
  }

  @Test
  @DisplayName("Get broken links with both broken and unbroken links")
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

  @Nested
  class ExceptionHandling {
    @Test
    void addPassageNullPassage() {
      assertThrows(NullPointerException.class, () -> story.addPassage(null));
    }

    @Test
    void createStoryWithNullTitle() {
      assertThrows(NullPointerException.class, () -> new Story(null, openingPassage));
    }

    @Test
    void createStoryWithNullPassage() {
      assertThrows(NullPointerException.class, () -> new Story("hello", null));
    }

    @Test
    void getPassageWithNullLink() {
      assertThrows(NullPointerException.class, () -> story.getPassage(null));
    }

    @Test
    void removePassageWithNullLink() {
      assertThrows(NullPointerException.class, () -> story.removePassage(null));
    }
  }
}