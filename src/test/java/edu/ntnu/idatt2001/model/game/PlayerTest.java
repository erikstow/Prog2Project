package edu.ntnu.idatt2001.model.game;

import edu.ntnu.idatt2001.model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Player class")
class PlayerTest {
  Player player;
  Player.PlayerBuilder playerBuilder;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("Torgeir").build();
    playerBuilder = new Player.PlayerBuilder("Torgeir");
    
  }

  @Nested
  @DisplayName("Player Getters")
  class PlayerGetters {
    @Test
    @DisplayName("getName should return the player's name")
    void getName() {
      assertEquals("Torgeir", player.getName());
    }

    @Test
    @DisplayName("getHealth should return the player's health")
    void getHealth() {
      assertEquals(100, player.getHealth());
    }

    @Test
    @DisplayName("getScore should return the player's score")
    void getScore() {
      assertEquals(0, player.getScore());
    }

    @Test
    @DisplayName("getGold should return the player's gold")
    void getGold() {
      assertEquals(0, player.getGold());
    }

    @Test
    @DisplayName("getInventory should return the player's inventory with two items")
    void getInventoryWithInventory() {
      String item1 = "Item1";
      String item2 = "Item2";
      String item3 = "Item3";
      String item4 = "Item4";

      List<String> inv = new ArrayList<>();
      inv.add(item1);
      inv.add(item2);
      inv.add(item3);
      inv.add(item4);

      player.addToInventory(item1);
      player.addToInventory(item2);
      player.addToInventory(item3);
      player.addToInventory(item4);

      assertEquals(inv, player.getInventory());
    }
  }

  @Nested
  @DisplayName("Player Adders")
  class PlayerAdders {
    @Test
    @DisplayName("addHealth should increase the player's health")
    void addHealth() {
      player.addHealth(1);
      assertEquals(101, player.getHealth());
    }

    @Test
    @DisplayName("addScore should increase the player's score")
    void addScore() {
      player.addScore(1);
      assertEquals(1, player.getScore());
    }

    @Test
    @DisplayName("addGold should increase the player's gold")
    void addGold() {
      player.addGold(1);
      assertEquals(1, player.getGold());
    }

    @Test
    @DisplayName("addToInventory should add an item to the player's inventory")
    void addToInventory() {
      player.addToInventory("Test");
      assertEquals("Test", player.getInventory().get(0));
    }
  }

@DisplayName("Exception handling for player class")
  @Nested
  class ExceptionHandling {
    @Test
    @DisplayName("addToInventory with null should throw NullPointerException")
    void addToInventoryWithNull() {
      assertThrows(NullPointerException.class, () -> player.addToInventory(null));
    }
  }
  
  @DisplayName("Exception handling for playerBuilder class")
  @Nested
  class ExceptionHandlingBuilder {
    @Test
    @DisplayName("New Player with null Name should throw NullPointerException")
    void createPlayerWithNullName() {
      assertThrows(NullPointerException.class, () -> new Player.PlayerBuilder(null));
    }

    @Test
    @DisplayName("New Player with negative Health should throw IllegalArgumentException")
    void createPlayerWithNegativeHealth() {
      assertThrows(IllegalArgumentException.class, () -> playerBuilder.health(-1));
    }

    @Test
    @DisplayName("New Player with negative Score throw IllegalArgumentException")
    void createPlayerWithNegativeScore() {

      assertThrows(IllegalArgumentException.class, () -> playerBuilder.score(-1));
    }

    @Test
    @DisplayName("New player with negative Gold should throw IllegalArgumentException")
    void createPlayerWithNegativeGold() {
      assertThrows(IllegalArgumentException.class, () -> playerBuilder.gold(-1));
    }

    @Test
    @DisplayName("New player with null Inventory should throw NullPointerException")
    void buildPlayerWithNullInventory() {
      assertThrows(NullPointerException.class, () -> playerBuilder.inventory(null));
    }
  }
}