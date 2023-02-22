package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;
import java.util.Objects;

/** 
 *The class HealthAction.
 * An action that adds health to the player.
 * The amount of health to add is specified in the constructor.
 */
public class HealthAction implements Action {
  private final int health;

  /** 
   *The constructor for the HealthAction class.
   * 
   * @param health The amount of health to add to the player.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /** 
   * Method to Add health to the player.
   * 
   * @param player The player to perform the action on.
   */
  @Override
  public void execute(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");

    player.addHealth(this.health);
  }
}
