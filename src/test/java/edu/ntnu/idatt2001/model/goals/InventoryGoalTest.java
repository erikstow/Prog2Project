package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.InventoryGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for the InventoryGoal class")
class InventoryGoalTest {
  Player player;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("Name")
      .health(10)
      .score(10)
      .gold(10)
      .build();
  }

  @DisplayName("Inventory goal is fulfilled")
  @Nested
  class GoalFulfilled {
    @DisplayName("isFulfilled should return true when the player has more items in inventory than necessary")
    @Test
    void inventoryGoalFulfilledWithGreaterInventory() {
      List<String> inventory = new ArrayList<>();
      player.addToInventory("Tent");
      player.addToInventory("String");
      player.addToInventory("Sword");
      player.addToInventory("Glasses");
      inventory.add("Tent");
      inventory.add("Sword");
      inventory.add("String");
      Goal inventoryGoal = new InventoryGoal(inventory);
      assertTrue(inventoryGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("isFulfilled should return true when the player has exactly the required items in inventory")
    void inventoryGoalFulfilledWithExactInventory() {
      List<String> inventory = new ArrayList<>();
      player.addToInventory("Tent");
      player.addToInventory("String");
      player.addToInventory("Sword");
      player.addToInventory("Glasses");
      inventory.add("Tent");
      inventory.add("Sword");
      inventory.add("Glasses");
      inventory.add("String");
      Goal inventoryGoal = new InventoryGoal(inventory);
      assertTrue(inventoryGoal.isFulfilled(player));
    }
  }

  @Nested
  @DisplayName("Inventory goal is not fulfilled")
  class GoalNotFulfilled {
    @Test
    @DisplayName("isFulfilled should return false when the player does not have the required items in inventory")
    void inventoryGoalNotFulfilled() {
      List<String> inventory = new ArrayList<>();
      player.addToInventory("Tent");
      player.addToInventory("Sword");
      player.addToInventory("Glasses");
      player.addToInventory("Mice");
      inventory.add("Tent");
      inventory.add("Sword");
      inventory.add("Glasses");
      inventory.add("String");
      Goal inventoryGoal = new InventoryGoal(inventory);
      assertFalse(inventoryGoal.isFulfilled(player));
    }
  }
}