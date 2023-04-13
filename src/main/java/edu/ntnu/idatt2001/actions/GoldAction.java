package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.game.Player;
import java.util.Objects;


/**
 * The class GoldAction.
 * An action that adds gold to the player.
 * The amount of gold to add is specified in the constructor.
 */ 
public class GoldAction implements Action {
  private final int gold;

  /**
   * The constructor for the GoldAction class.
   *
   * @param gold The amount of gold to add to the player.
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /**
   * Method to Add gold to the player.
   *
   * @param player The player to perform the action on.
   */
  @Override
  public void execute(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    player.addGold(this.gold);
  }

  public String getAsString() {
    return "!GoldAction:" + this.gold + "\n";
  }
}
