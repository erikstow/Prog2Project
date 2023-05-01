package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {
  Passage passage;

  @BeforeEach
  void setUp() {
    passage = new Passage("Title", "Content");
  }

  @Nested
  class PassageGetters {

    @Test
    void getTitle() {
      assertEquals("Title", passage.getTitle());
    }

    @Test
    void getContent() {
      assertEquals("Content", passage.getContent());
    }

    @Test
    void getLinksWithEmptyList() {
      assertTrue(passage.getLinks().isEmpty());
    }

    @Test
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
    void hasLinksWithNoLinks() {
      assertFalse(passage.hasLinks());
    }

    @Test
    void hasLinksWithLinks() {
      passage.addLink(new Link("t", "t"));
      assertTrue(passage.hasLinks());
    }
  }


  @Nested
  class PassageAdders {
    @Test
    void addLinkNotDuplicate() {
      Link link = new Link("test", "test");
      assertTrue(passage.addLink(link));
      assertEquals(link, passage.getLinks().get(0));
    }

    @Test
    void addLinkWhenDuplicate() {
      Link link = new Link("T", "T");
      passage.addLink(link);
      assertFalse(passage.addLink(new Link("T", "T")));
    }
  }


  @Nested
  class ExceptionHandling {
    @Test
    void createPassageWithNullTitle() {
      assertThrows(NullPointerException.class, () -> new Passage(null, "test"));
    }

    @Test
    @DisplayName("Create passage with null content")
    void createPassageWithNullContent() {
      assertThrows(NullPointerException.class, () -> new Passage("Test", null));
    }

    @Test
    void addNullLink() {
      assertThrows(NullPointerException.class, () -> passage.addLink(null));
    }
  }
}