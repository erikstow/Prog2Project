package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {

  Player player;
  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class GoalFulfilled {
    @Test
    void healthGoalFulfilledWithGreaterHealth() {
      Goal healthGoal = new HealthGoal(5);
      assertTrue(healthGoal.isFulfilled(player));
    }

    @Test
    void healthGoalFulfilledWithExactAmout() {
      Goal healthGoal = new HealthGoal(10);
      assertTrue(healthGoal.isFulfilled(player));
    }
  }

  @Nested
  class GoalNotFulfilled {
    @Test
    void healthGoalNotFulfilled() {
      Goal healthGoal = new HealthGoal(15);
      assertFalse(healthGoal.isFulfilled(player));
    }
  }
}