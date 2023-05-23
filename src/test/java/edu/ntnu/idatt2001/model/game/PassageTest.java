package edu.ntnu.idatt2001.model.game;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Passage class")
class PassageTest {
  Passage passage;

  @BeforeEach
  void setUp() {
    passage = new Passage("Title", "Content");
  }

  @Nested
  @DisplayName("Passage Getters")
  class PassageGetters {

    @Test
    @DisplayName("getTitle should return the title")
    void getTitle() {
      assertEquals("Title", passage.getTitle());
    }

    @Test
    @DisplayName("getContent should return the content")
    void getContent() {
      assertEquals("Content", passage.getContent());
    }

    @Test
    @DisplayName("getLinks should return an empty list when no links are added")
    void getLinksWithEmptyList() {
      assertTrue(passage.getLinks().isEmpty());
    }

    @Test
    @DisplayName("getLinks should return a list with two links when two links are added")
    void getLinksWithTwoLinks() {
      Link link1 = new Link("Test", "Test1");
      Link link2 = new Link("Test", "Test2");
      passage.addLink(link1);
      passage.addLink(link2);
      List<Link> list = new ArrayList<>();
      list.add(link1);
      list.add(link2);

      assertEquals(list, passage.getLinks());
    }

    @Test
    @DisplayName("hasLinks should return false when no links are added")
    void hasLinksWithNoLinks() {
      assertFalse(passage.hasLinks());
    }

    @Test
    @DisplayName("hasLinks should return true when links are added")
    void hasLinksWithLinks() {
      passage.addLink(new Link("t", "t"));
      assertTrue(passage.hasLinks());
    }
  }


  @Nested
  @DisplayName("Passage Adders")
  class PassageAdders {
    @Test
    @DisplayName("addLink should add a link that is not a duplicate")
    void addLinkNotDuplicate() {
      Link link = new Link("test", "test");
      assertTrue(passage.addLink(link));
      assertEquals(link, passage.getLinks().get(0));
    }

    @Test
    @DisplayName("addLink should not add a duplicate link")
    void addLinkWhenDuplicate() {
      Link link = new Link("T", "T");
      passage.addLink(link);
      assertFalse(passage.addLink(new Link("T", "T")));
    }
  }


  @Nested
  @DisplayName("Passage Exception Handling")
  class ExceptionHandling {
    @Test
    @DisplayName("new Passage with nNull Title should throw NullPointerException")
    void createPassageWithNullTitle() {
      assertThrows(NullPointerException.class, () -> new Passage(null, "test"));
    }

    @Test
    @DisplayName("Create passage with null content")
    void createPassageWithNullContent() {
      assertThrows(NullPointerException.class, () -> new Passage("Test", null));
    }

    @Test
    @DisplayName("Adding null Link should throw NullPointerException")
    void addNullLink() {
      assertThrows(NullPointerException.class, () -> passage.addLink(null));
    }
  }
}