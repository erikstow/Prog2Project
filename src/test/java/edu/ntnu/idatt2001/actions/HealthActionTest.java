package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.actions.player.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The class HealthActionTest.
 * Tests for the HealthAction class.
 * Player is used as a test double.
 */
@DisplayName("Tests for HealthAction")
class HealthActionTest {

  Player player;

  /**
   * Method to set up the test double.
   */
  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("Name")
      .health(10)
      .score(10)
      .gold(10)
      .build();
  }

  /**
   * Nested class for testing execute method.
   */
  @Nested
  @DisplayName("Tests for execute method")
  class HealthActionExecuteTests {

    /**
     * Method to test if the execute method adds health to the player.
     */
    @Test
    @DisplayName("Give player more health")
    void givePlayerMoreHealth() {
      HealthAction action = new HealthAction(10);
      action.execute(player);
      assertEquals(20, player.getHealth());
    }

    /**
     * Method to test if the execute method adds zero health to the player.
     */
    @Test
    @DisplayName("Give player zero health")
    void givePlayerZeroHealth() {
      HealthAction action = new HealthAction(0);
      action.execute(player);
      assertEquals(10, player.getHealth());
    }

    /**
     * Method to test if the execute method adds negative health to the player.
     */
    @Test
    @DisplayName("Give player negative health")
    void givePlayerNegativeHealth() {
      HealthAction action = new HealthAction(-20);
      action.execute(player);
      assertEquals(-10, player.getHealth());
    }
  }

  /**
   * Nested class for testing exception handling.
   */
  @Nested
  @DisplayName("Tests for exception handling")
  class HealthActionExceptionTests {

    /**
     * Method to test if the execute method throws NullPointerException when player is null.
     */
    @Test
    @DisplayName("Give player null")
    void giveNullPlayer() {
      HealthAction action = new HealthAction(10);
      assertThrows(NullPointerException.class, () -> action.execute(null));
    }
  }
}
