package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.actions.GoldAction;
import edu.ntnu.idatt2001.actions.Action;
import edu.ntnu.idatt2001.actions.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {
  public Link link;

  @BeforeEach
  void setUp() {
    link = new Link("Text", "Reference");
  }

  @Nested
  class LinkGetters {
    @Test
    void getReference() {
      assertEquals("Reference", link.getReference());
    }

    @Test
    void getActionsWithTwoActions() {
      GoldAction goldAction = new GoldAction(100);
      ScoreAction scoreAction = new ScoreAction(100);
      link.addAction(goldAction);
      link.addAction(scoreAction);
      ArrayList<Action> actionList = new ArrayList<>();
      actionList.add(goldAction);
      actionList.add(scoreAction);
      assertEquals(actionList, link.getActions());
    }

    @Test
    void getActionsWithEmptyActionList() {
      assertTrue(link.getActions().isEmpty());
    }

    @Test
    void getText() {
      assertEquals("Text", link.getText());
    }

  }

  @Nested
  class LinkAdders {
    @Test
    void addTwoActions() {
      GoldAction goldAction = new GoldAction(500);
      ScoreAction scoreAction = new ScoreAction(100);
      link.addAction(goldAction);
      link.addAction(scoreAction);
      assertEquals(goldAction, link.getActions().get(0));
      assertEquals(scoreAction, link.getActions().get(1));
    }
  }

  @Nested
  class LinkExceptionHandling {
    @Test
    void createLinkWithNullText() {
      assertThrows(NullPointerException.class, () -> new Link(null, "Test"));
    }

    @Test
    void createLinkWithNullReference() {
      assertThrows(NullPointerException.class, () -> new Link("Test", null));
    }
  }
}
