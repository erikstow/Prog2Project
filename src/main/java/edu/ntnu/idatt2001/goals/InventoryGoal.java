package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

import java.util.HashSet;
import java.util.List;

/**
 * Checks if player has all required items in their inventory
 * to complete the goal.
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return new HashSet<>(player.getInventory()).containsAll(mandatoryItems);
  }
}
