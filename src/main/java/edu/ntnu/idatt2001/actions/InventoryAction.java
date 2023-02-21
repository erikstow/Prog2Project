package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/** The class InventoryAction.
 * An action that adds an item to the player's inventory.
 * The item to add is specified in the constructor.
 */
public class InventoryAction implements Action {
  private final String item;

  /** The constructor for the InventoryAction class.
   * @param item The item to add to the player's inventory.
   */
  public InventoryAction(String item) {
    this.item = item;
  }

  @Override
  public void execute(Player player) {
    player.addToInventory(this.item);
  }
}
