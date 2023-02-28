package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreActionTest {

  Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class PositiveTests {
    @Test
    void givePlayerMoreScore() {
      ScoreAction action = new ScoreAction(10);
      action.execute(player);
      assertEquals(20, player.getScore());
    }

    @Test
    void givePlayerLessScore() {
      ScoreAction action = new ScoreAction(-20);
      action.execute(player);
      assertEquals(-10, player.getScore());

    }
  }
}