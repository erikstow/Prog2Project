package edu.ntnu.idatt2001.actions;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * The class ScoreActionTest.
 * Tests for the ScoreAction class.
 * Player is used as a test double.
 */
@DisplayName("Tests for ScoreAction")
class ScoreActionTest {

  Player player;

  /**
   * Method to set up the test double.
   */
  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  /**
   * Nested class for testing execute method.
   */
  @Nested
  @DisplayName("Tests for execute method")
  class ScoreActionExecuteTests {

    /**
     * Method to test if the execute method adds score to the player.
     */
    @Test
    @DisplayName("Give player more score")
    void givePlayerMoreScore() {
      ScoreAction action = new ScoreAction(10);
      action.execute(player);
      assertEquals(20, player.getScore());
    }

    /**
     * Method to test if the execute method adds zero score to the player.
     */
    @Test
    @DisplayName("Give player zero score")
    void givePlayerZeroScore() {
      ScoreAction action = new ScoreAction(0);
      action.execute(player);
      assertEquals(10, player.getScore());
    }

    /**
     * Method to test if the execute method adds negative score to the player.
     */
    @Test
    @DisplayName("Give player less score")
    void givePlayerLessScore() {
      ScoreAction action = new ScoreAction(-20);
      action.execute(player);
      assertEquals(-10, player.getScore());
    }
  }

  /**
   * Nested class for testing exception handling.
   */
  @Nested
  @DisplayName("ExceptionHandling")
  class ExceptionHandling {

    /**
     * Method to test if the execute method throws NullPointerException.
     */
    @Test
    @DisplayName("Player is null")
    void throwNullPointerException() {
      ScoreAction action = new ScoreAction(10);
      assertThrows(NullPointerException.class, () -> action.execute(null));
    }
  }
}
