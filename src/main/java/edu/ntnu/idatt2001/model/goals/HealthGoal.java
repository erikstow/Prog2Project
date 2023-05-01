package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import java.util.Objects;

/** 
 * The class HealthGoal.
 * A goal that can be fulfilled by a player.
 * The goal is fulfilled when the player has more health than 
 * the minimum health specified in the constructor.
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /** 
   * The constructor for the HealthGoal class.
   *
   * @param minimumHealth The minimum amount of health the player must have to fulfill the goal.
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Method to check if the player has more health than the minimum health.
   *
   * @param player The player to check the goal for.
   *
   * @return True if the player has more health than the minimum health, false otherwise.
   */
  @Override
  public boolean isFulfilled(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    return player.getHealth() >= this.minimumHealth;
  }
}
