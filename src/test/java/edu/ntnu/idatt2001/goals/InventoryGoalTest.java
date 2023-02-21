package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryGoalTest {
  Player player;
  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class GoalFulfilled {
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
  class GoalNotFulfilled {
    @Test
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