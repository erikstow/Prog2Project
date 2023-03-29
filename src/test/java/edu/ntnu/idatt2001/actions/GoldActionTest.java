package edu.ntnu.idatt2001.actions;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * The class GoldActionTest.
 * Tests for the GoldAction class.
 * Player is used as a test double.
 */
@DisplayName("Tests for GoldAction")
public class GoldActionTest {
  
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
  class GoldActionExecuteTests {

    /**
     * Method to test if the execute method adds gold to the player.
     */
    @Test
    @DisplayName("Give player more gold")
    void givePlayerMoreGold() {
      GoldAction action = new GoldAction(10);
      action.execute(player);
      assertEquals(20, player.getGold());
    }

    /**
     * Method to test if the execute method adds zero gold to the player.
     */
    @Test
    @DisplayName("Give player zero gold")
    void givePlayerZeroGold() {
      GoldAction action = new GoldAction(0);
      action.execute(player);
      assertEquals(10, player.getGold());
    }

    /**
     * Method to test if the execute method adds negative gold to the player.
     */
    @Test
    @DisplayName("Give player negative gold")
    void givePlayerNegativeGold() {
      GoldAction action = new GoldAction(-10);
      action.execute(player);
      assertEquals(0, player.getGold());
    }
  }

  /**
   * Nested class for testing exception handling.
   */
  @Nested
  @DisplayName("Tests for exception handling")
  class GoldActionExceptionTests {

    /**
     * Method to test if the execute method throws an exception when given a null player.
     */
    @Test
    @DisplayName("Give null player")
    void giveNullPlayer() {
      GoldAction action = new GoldAction(10);
      assertThrows(NullPointerException.class, () -> action.execute(null));
    }
  }
}
