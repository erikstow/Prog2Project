package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import java.util.Objects;

/** 
 * The class ScoreGoal.
 *  A goal that can be fulfilled by a player.
 *  The goal is fulfilled when the player has more score than
 *  the minimum score specified in the constructor.
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /** 
   * The constructor for the ScoreGoal class.
   *
   * @param minimumPoints The minimum amount of score the player must have to fulfill the goal.
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /**
   * Method to check if the player has more score than the minimum score.
   *
   * @param player The player to check the goal for.
   *
   * @return True if the player has more score than the minimum score, false otherwise.
   */
  @Override
  public boolean isFulfilled(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    
    return player.getScore() >= this.minimumPoints;
  }

  @Override
  public String toString() {
    return "ScoreGoal{" + "minimumPoints=" + minimumPoints + '}';
  }
}
