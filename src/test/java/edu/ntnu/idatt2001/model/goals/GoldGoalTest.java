package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.GoldGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the GoldGoal class")
class GoldGoalTest {
  Player player;

  /**
   * Sets up a default player object.
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
   * Tests for when the goal is fulfilled.
   */
  @Nested
  @DisplayName("Gold goal is fulfilled")
  class GoalFulfilled {

    @Test
    @DisplayName("Player has more gold than the goal")
    void goldGoalIsFulfilledWithGreaterGold() {
      Goal goldGoal = new GoldGoal(5);
      assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("Player has exact amount")
    void goldGoalIsFulfilledWithExactAmount() {
      Goal goldGoal = new GoldGoal(10);
      assertTrue(goldGoal.isFulfilled(player));
    }
  }

  @Nested
  @DisplayName("Gold goal is not fulfilled")
  class GoalNotFulfilled {
    @Test
    @DisplayName("Player has less gold than the goal")
    void goldGoalNotFulfilled() {
      Goal goldGoal = new GoldGoal(15);
      assertFalse(goldGoal.isFulfilled(player));
    }
  }

  @Nested
  @DisplayName("Exception handling for GoldGoal class")
  class ExceptionHandling {
    @Test
    @DisplayName("Tests gold goal with null player")
    void nullPlayer() {
      GoldGoal goldGoal = new GoldGoal(10);
      assertThrows(NullPointerException.class, () -> goldGoal.isFulfilled(null));
    }
  }
}