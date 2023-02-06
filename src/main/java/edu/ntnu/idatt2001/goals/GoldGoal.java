package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;
import edu.ntnu.idatt2001.goals.Goal;

/**
 * Checks if the player has enough gold to complete the goal.
 */
public class GoldGoal implements Goal {
  private final int minimumGold;

  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getGold() > minimumGold;
  }
}
