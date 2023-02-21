package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/** The class ScoreAction.
 * An action that adds score to the player.
 * The amount of score to add is specified in the constructor.
 */ 
public class ScoreAction implements Action {
  private final int points;

  /** The constructor for the ScoreAction class.
   * @param points The amount of score to add to the player.
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  @Override
  public void execute(Player player) {
    player.addScore(points);
  }
}
