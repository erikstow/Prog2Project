package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;
import java.util.Objects;


/**
 * The class InventoryAction.
 * An action that adds an item to the player's inventory.
 * The item to add is specified in the constructor.
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   *The constructor for the InventoryAction class.
   * 
   * @param item The item to add to the player's inventory.
   */
  public InventoryAction(String item) throws NullPointerException {
    Objects.requireNonNull(item, "Item cannot be null");

    this.item = item;
  }

  /**
   *Method to Add an item to the player's inventory.
   * 
   * @param player The player to perform the action on.
   */
  @Override
  public void execute(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");

    player.addToInventory(this.item);
  }
}
