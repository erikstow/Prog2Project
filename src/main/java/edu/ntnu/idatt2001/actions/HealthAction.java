package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/** The class HealthAction.
 * An action that adds health to the player.
 * The amount of health to add is specified in the constructor.
 */
public class HealthAction implements Action {
  private final int health;

  /** The constructor for the HealthAction class.
   * @param health The amount of health to add to the player.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) throws IllegalArgumentException {
    if (player.getHealth() + this.health < 0) {
      player.addHealth(-player.getHealth());
    }
    player.addHealth(this.health);
  }
}
