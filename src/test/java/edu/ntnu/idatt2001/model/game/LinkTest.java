package edu.ntnu.idatt2001.model.game;

import edu.ntnu.idatt2001.model.actions.player.Action;
import edu.ntnu.idatt2001.model.actions.player.GoldAction;
import edu.ntnu.idatt2001.model.actions.player.ScoreAction;
import edu.ntnu.idatt2001.model.game.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
  @DisplayName("Link Getters")
  class LinkGetters {
    @Test
    @DisplayName("getReference should return the reference")
    void getReference() {
      assertEquals("Reference", link.getReference());
    }

    @Test
    @DisplayName("getActions should return the list of actions with two actions")
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
    @DisplayName("getActions should return an empty list when no actions are added")
    void getActionsWithEmptyActionList() {
      assertTrue(link.getActions().isEmpty());
    }

    @Test
    @DisplayName("getText should return the text")
    void getText() {
      assertEquals("Text", link.getText());
    }

  }

  @Nested
  @DisplayName("Link Adders")
  class LinkAdders {
    @Test
    @DisplayName("Adding two actions to the link should lead to two actions in the link")
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
  @DisplayName("Link Exception Handling")
  class LinkExceptionHandling {
    @Test
    @DisplayName("new link with null Text should throw NullPointerException")
    void createLinkWithNullText() {
      assertThrows(NullPointerException.class, () -> new Link(null, "Test"));
    }

    @Test
    @DisplayName("New Link with null Reference should throw NullPointerException")
    void createLinkWithNullReference() {
      assertThrows(NullPointerException.class, () -> new Link("Test", null));
    }
  }
}
