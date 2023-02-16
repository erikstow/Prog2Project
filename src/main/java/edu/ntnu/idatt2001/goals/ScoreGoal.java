package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/**
 * Checks if the player has enough score to complete the goal.
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() > this.minimumPoints;
  }
}
