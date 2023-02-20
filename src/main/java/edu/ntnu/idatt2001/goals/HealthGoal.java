package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/**
 * Checks to see if the player has enough health to complete the goal.
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= this.minimumHealth;
  }
}
