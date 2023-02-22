package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import java.util.Objects;

/** The class GoldGoal.
 *  A goal that can be fulfilled by a player.
 * The goal is fulfilled when the player has more gold than 
 * the minimum gold specified in the constructor.
 */
public class GoldGoal implements Goal {
  private final int minimumGold;

  /** The constructor for the GoldGoal class.
   * @param minimumGold The minimum amount of gold the player must have to fulfill the goal.
   */
  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * Method to check if the player has more gold than the minimum gold.
   *
   * @param player The player to check the goal for.
   *
   * @return True if the player has more gold than the minimum gold, false otherwise.
   */
  @Override
  public boolean isFulfilled(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    return player.getGold() >= minimumGold;
  }
}
