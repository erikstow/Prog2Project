package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {

  Player player;

  /**
   * Creates defauld player object.
   */
  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @DisplayName("Health goal is fulfilled")
  @Nested
  class GoalFulfilled {
    @DisplayName("Player has more health than the goal")
    @Test
    void healthGoalFulfilledWithGreaterHealth() {
      Goal healthGoal = new HealthGoal(5);
      assertTrue(healthGoal.isFulfilled(player));
    }

    @DisplayName("Player has the same amount of health as the goal")
    @Test
    void healthGoalFulfilledWithExactAmout() {
      Goal healthGoal = new HealthGoal(10);
      assertTrue(healthGoal.isFulfilled(player));
    }
  }

  @DisplayName("Health goal is not fulfilled")
  @Nested
  class GoalNotFulfilled {
    @DisplayName("Player has less health than the goal")
    @Test
    void healthGoalNotFulfilled() {
      Goal healthGoal = new HealthGoal(15);
      assertFalse(healthGoal.isFulfilled(player));
    }
  }

  @DisplayName("Exception handling for Health Goal class")
  @Nested
  class ExceptionHandling {
    @DisplayName("Tests health goal with null player")
    @Test
    void nullPlayer() {
      HealthGoal healthGoal = new HealthGoal(10);
      assertThrows(NullPointerException.class, () -> healthGoal.isFulfilled(null));
    }
  }
}