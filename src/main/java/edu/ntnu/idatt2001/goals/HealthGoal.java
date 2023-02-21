package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/** The class HealthGoal.
 *  A goal that can be fulfilled by a player.
 *  The goal is fulfilled when the player has more health than 
 *  the minimum health specified in the constructor.
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /** The constructor for the HealthGoal class.
   * @param minimumHealth The minimum amount of health the player must have to fulfill the goal.
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getHealth() > this.minimumHealth;
  }
}
