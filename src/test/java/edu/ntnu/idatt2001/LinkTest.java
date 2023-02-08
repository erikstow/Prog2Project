package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.actions.GoldAction;
import edu.ntnu.idatt2001.actions.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {
  public Link link;

  @BeforeEach
  void setUp() {
    link = new Link("Text", "Reference");
  }

  @Test
  void getReference() {
    assertEquals("Reference", link.getReference());
  }

  @Test
  void addAndGetActions() {
    GoldAction goldAction = new GoldAction(500);
    link.addAction(goldAction);
    assertEquals(goldAction, link.getActions().get(0));
  }

  @Test
  void getText() {
    assertEquals("Text", link.getText());
  }
}
