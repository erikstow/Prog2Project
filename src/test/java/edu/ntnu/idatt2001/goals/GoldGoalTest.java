package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {
  Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class GoalFulfilled {
    @Test
    void goldGoalIsFulfilledWithGreaterGold() {
      Goal goldGoal = new GoldGoal(5);
      assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    void goldGoalIsFulfilledWithExactAmount() {
      Goal goldGoal = new GoldGoal(10);
      assertTrue(goldGoal.isFulfilled(player));
    }
  }

  @Nested
  class GoalNotFulfilled {
    @Test
    void goldGoalNotFulfilled() {
      Goal goldGoal = new GoldGoal(15);
      assertFalse(goldGoal.isFulfilled(player));
    }
  }
}