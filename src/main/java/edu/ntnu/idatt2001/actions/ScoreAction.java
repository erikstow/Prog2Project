package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/**
 * Adds to the score of a player.
 */
public class ScoreAction implements Action {
  private final int points;

  public ScoreAction(int points) {
    this.points = points;
  }

  @Override
  public void execute(Player player) {
    player.addScore(points);
  }
}
