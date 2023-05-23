package edu.ntnu.idatt2001.model.actions.player;

import edu.ntnu.idatt2001.model.game.Player;
import java.util.Objects;


/**
 * The class InventoryAction.
 * An action that adds an item to the player's inventory.
 * The item to add is specified in the constructor.
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   * The constructor for the InventoryAction class.
   *
   * @param item The item to add to the player's inventory.
   */
  public InventoryAction(String item) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(item, "Item cannot be null");
    
    if (item.isBlank()) {
      throw new IllegalArgumentException("Item cannot be blank");
    }

    this.item = item;
  }

  /**
   * Method to Add an item to the player's inventory.
   *
   * @param player The player to perform the action on.
   */
  @Override
  public void execute(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");

    player.addToInventory(this.item);
  }

  /**
   * Returns a string representation of the InventoryAction.
   *
   * @return a string representation of the InventoryAction.
   */
  public String getAsString() {
    return "!InventoryAction:" + this.item + "\n";
  }
}
