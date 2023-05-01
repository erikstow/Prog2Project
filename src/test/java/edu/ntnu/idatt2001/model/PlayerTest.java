package edu.ntnu.idatt2001.model;

import edu.ntnu.idatt2001.model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  Player player;
  Player.PlayerBuilder playerBuilder;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("Torgeir").build();
    playerBuilder = new Player.PlayerBuilder("Torgeir");
    
  }

  @Nested
  class PlayerGetters {
    @Test
    void getName() {
      assertEquals("Torgeir", player.getName());
    }

    @Test
    void getHealth() {
      assertEquals(100, player.getHealth());
    }

    @Test
    void getScore() {
      assertEquals(0, player.getScore());
    }

    @Test
    void getGold() {
      assertEquals(0, player.getGold());
    }

    @DisplayName("Get inventory with two items in inventory")
    @Test
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
  class PlayerAdders {
    @Test
    void addHealth() {
      player.addHealth(1);
      assertEquals(101, player.getHealth());
    }

    @Test
    void addScore() {
      player.addScore(1);
      assertEquals(1, player.getScore());
    }

    @Test
    void addGold() {
      player.addGold(1);
      assertEquals(1, player.getGold());
    }

    @Test
    void addToInventory() {
      player.addToInventory("Test");
      assertEquals("Test", player.getInventory().get(0));
    }
  }

@DisplayName("Exception handling for player class")
  @Nested
  class ExceptionHandling {
    @Test 
    void addToInventoryWithNull() {
      assertThrows(NullPointerException.class, () -> player.addToInventory(null));
    }
  }
  
  @DisplayName("Exception handling for playerBuilder class")
  @Nested
  class ExceptionHandlingBuilder {
    @Test
    void createPlayerWithNullName() {
      assertThrows(NullPointerException.class, () -> new Player.PlayerBuilder(null));
    }

    @Test
    void createPlayerWithNegativeHealth() {
      assertThrows(IllegalArgumentException.class, () -> playerBuilder.health(-1));
    }

    @Test
    void createPlayerWithNegativeScore() {

      assertThrows(IllegalArgumentException.class, () -> playerBuilder.score(-1));
    }

    @Test
    void createPlayerWithNegativeGold() {
      assertThrows(IllegalArgumentException.class, () -> playerBuilder.gold(-1));
    }
    @Test
    void buildPlayerWithNullInventory() {
      assertThrows(NullPointerException.class, () -> playerBuilder.inventory(null));
    }
  }
}