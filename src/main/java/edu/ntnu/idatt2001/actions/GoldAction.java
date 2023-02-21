package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/** The class GoldAction.
 * An action that adds gold to the player.
 * The amount of gold to add is specified in the constructor.
 */ 
public class GoldAction implements Action {
  private final int gold;

  /** The constructor for the GoldAction class.
   * @param gold The amount of gold to add to the player.
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public void execute(Player player) {
    player.addGold(this.gold);
  }
}
