package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

import java.util.HashSet;
import java.util.List;

/** The class InventoryGoal.
 *  A goal that can be fulfilled by a player.
 *  The goal is fulfilled when the player has all the items specified in the constructor.
 */ 
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /** The constructor for the InventoryGoal class.
   * @param mandatoryItems The items that the player must have to fulfill the goal.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return new HashSet<>(player.getInventory()).containsAll(mandatoryItems);
  }
}
