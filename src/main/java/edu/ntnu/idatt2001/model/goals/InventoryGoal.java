package edu.ntnu.idatt2001.model.goals;

import edu.ntnu.idatt2001.model.game.Player;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/** 
 * The class InventoryGoal.
 * A goal that can be fulfilled by a player.
 * The goal is fulfilled when the player has all the items specified in the constructor.
 */ 
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /** 
   * The constructor for the InventoryGoal class.
   *
   * @param mandatoryItems The items that the player must have to fulfill the goal.
   */
  public InventoryGoal(List<String> mandatoryItems) throws NullPointerException {
    Objects.requireNonNull(mandatoryItems, "Mandatory items cannot be null");
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Method to check if the player has all the items specified in the constructor.
   *
   * @param player The player to check the goal for.
   *
   * @return True if the player has all the items specified in the constructor, false otherwise.
   */
  @Override
  public boolean isFulfilled(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    return new HashSet<>(player.getInventory()).containsAll(mandatoryItems);
  }

  @Override
  public String toString() {
    return "Have " + mandatoryItems + " in inventory";
  }
}
