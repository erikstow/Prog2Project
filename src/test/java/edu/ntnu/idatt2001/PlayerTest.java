package edu.ntnu.idatt2001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class PlayerGetters {
    @Test
    void getName() {
      assertEquals("Name", player.getName());
    }

    @Test
    void getHealth() {
      assertEquals(10, player.getHealth());
    }

    @Test
    void getScore() {
      assertEquals(10, player.getScore());
    }

    @Test
    void getGold() {
      assertEquals(10, player.getGold());
    }

    @DisplayName("Get inventory with two items in inventory")
    @Test
    void getInventoryWithInventory() {
      String item1 = "Iten1";
      String item2 = "Iten2";
      String item3 = "Iten3";
      String item4 = "Iten4";

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
      assertEquals(11, player.getHealth());
    }

    @Test
    void addScore() {
      player.addScore(1);
      assertEquals(11, player.getScore());
    }

    @Test
    void addGold() {
      player.addGold(1);
      assertEquals(11, player.getGold());
    }

    @Test
    void addToInventory() {
      player.addToInventory("Test");
      assertEquals("Test", player.getInventory().get(0));
    }
  }

  @DisplayName("Exception handling for Player class")
  @Nested
  class ExceptionHandling {
    @Test
    void createPlayerWithNullName() {
      assertThrows(NullPointerException.class, () -> new Player(null, 1,1,1));
    }

    @Test
    void createPlayerWithNegativeHealth() {
      assertThrows(IllegalArgumentException.class, () -> new Player("h", -1, 1, 1));
    }

    @Test
    void createPlayerWithNegativeScore() {
      assertThrows(IllegalArgumentException.class, () -> new Player("y", 1, -1, 1));
    }

    @Test
    void createPlayerWithNegativeGold() {
      assertThrows(IllegalArgumentException.class, () -> new Player("y", 1, 1, -1));
    }
    @Test
    void addToInventoryWithNull() {
      assertThrows(NullPointerException.class, () -> player.addToInventory(null));
    }

  }
}