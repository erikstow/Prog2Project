package edu.ntnu.idatt2001.actions;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * The class InventoryActionTest.
 * Tests for the InventoryAction class.
 * Player is used as a test double.
 */
@DisplayName("InventoryAction")
public class InventoryActionTest {

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
  @DisplayName("Execute method")
  class InventoryActionExecuteTests {

    /**
     * Method to test if the execute method adds item to the player.
     */
    @Test
    @DisplayName("Add item to player")
    void addItemToPlayer() {
      InventoryAction action = new InventoryAction("Item");
      action.execute(player);
      assertTrue(player.getInventory().contains("Item"));
    }

    /**
     * Method to test if the execute method adds item to the player twice.
     */
    @Test
    @DisplayName("Add 2 items to player")
    void addItemToPlayerTwice() {
      InventoryAction action = new InventoryAction("Item");
      action.execute(player);
      action.execute(player);
      assertTrue(player.getInventory().contains("Item"));
    }
  }

  /**
   * Nested class for testing exception handling.
   */
  @Nested
  @DisplayName("Exception handling")
  class InventoryActionExceptionHandlingTests {
    
    /**
     * Method to test if the constructor throws an exception when item is null.
     */
    @Test
    @DisplayName("Item is null")
    void itemIsNull() {
      assertThrows(NullPointerException.class, () -> new InventoryAction(null));
    }

    /**
     * Method to test if the constructor throws an exception when item is blank.
     */
    @Test
    @DisplayName("Item is blank")
    void itemIsBlank() {
      assertThrows(IllegalArgumentException.class, () -> new InventoryAction(""));
    }

    /**
     * Method to test if the execute method throws an exception when player is null.
     */
    @Test
    @DisplayName("Player is null")
    void playerIsNull() {
      InventoryAction action = new InventoryAction("Item");
      assertThrows(NullPointerException.class, () -> action.execute(null));
    }
  }

}