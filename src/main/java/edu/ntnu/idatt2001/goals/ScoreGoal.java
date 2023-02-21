package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/** The class ScoreGoal.
 *  A goal that can be fulfilled by a player.
 *  The goal is fulfilled when the player has more score than
 *  the minimum score specified in the constructor.
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /** The constructor for the ScoreGoal class.
   * @param minimumPoints The minimum amount of score the player must have to fulfill the goal.
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() >= this.minimumPoints;
  }
}
