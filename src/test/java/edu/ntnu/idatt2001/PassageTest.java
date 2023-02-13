package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {
  Passage passage;

  @BeforeEach
  void setUp() {
    passage = new Passage("Title", "Content");
  }

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
  void hasLinksWithNoLinks() {
    assertFalse(passage.hasLinks());
  }

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
    assertFalse(passage.addLink(link));
  }

  @Test
  void hasLinksWithLinks() {
    passage.addLink(new Link("t", "t"));
    assertTrue(passage.hasLinks());
  }
}