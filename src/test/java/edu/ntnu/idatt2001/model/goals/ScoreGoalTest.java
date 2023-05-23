package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreGoalTest {

  Player player;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder("Name")
      .health(10)
      .score(10)
      .gold(10)
      .build();
  }

  @Nested
  @DisplayName("Score goal is fulfilled")
  class GoalFulfilled {
    @Test
    @DisplayName("isFulfilled should return true when the player's score is greater than the goal score")
    void scoreGoalFulfilledWithGreaterScore() {
      Goal scoreGoal = new ScoreGoal(5);
      assertTrue(scoreGoal.isFulfilled(player));
    }

    @Test
    @DisplayName("isFulfilled should return true when the player's score is equal to the goal score")
    void scoreGoalFulfilledWithExactAmount() {
      Goal scoreGoal = new ScoreGoal(10);
      assertTrue(scoreGoal.isFulfilled(player));
    }
  }

  @Nested
  @DisplayName("Score goal is not fulfilled")
  class GoalNotFulfilled {
    @Test
    @DisplayName("isFulfilled should return false when the player's score is less than the goal score")
    void scoreGoalNotFulfilled() {
      Goal scoreGoal = new ScoreGoal(15);
      assertFalse(scoreGoal.isFulfilled(player));
    }
  }
}