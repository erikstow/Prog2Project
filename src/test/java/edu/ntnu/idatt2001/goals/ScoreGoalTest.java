package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreGoalTest {

  Player player;
  @BeforeEach
  void setUp() {
    player = new Player("Name", 10, 10, 10);
  }

  @Nested
  class GoalFulfilled {
    @Test
    void scoreGoalFulfilledWithGreaterScore() {
      Goal scoreGoal = new ScoreGoal(5);
      assertTrue(scoreGoal.isFulfilled(player));
    }

    @Test
    void scoreGoalFulfilledWithExactAmount() {
      Goal scoreGoal = new ScoreGoal(10);
      assertTrue(scoreGoal.isFulfilled(player));
    }
  }

  @Nested
  class GoalNotFulfilled {
    @Test
    void scoreGoalNotFulfilled() {
      Goal scoreGoal = new ScoreGoal(15);
      assertFalse(scoreGoal.isFulfilled(player));
    }
  }
}