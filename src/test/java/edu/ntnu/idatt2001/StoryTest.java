package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

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
  }
}