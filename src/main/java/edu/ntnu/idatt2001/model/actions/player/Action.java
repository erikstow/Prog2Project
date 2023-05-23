package edu.ntnu.idatt2001.model.actions.player;

import edu.ntnu.idatt2001.model.game.Player;

/**
 * The interface Action.
 * An action is a task that can be performed by a player.
 * The action is performed by calling the execute method.
 */
public interface Action {
  /**
   * Executes the action for the given player.
   *
   * @param player the player on which the action is performed.
   * @throws NullPointerException if the player is null.
   */
  void execute(Player player) throws NullPointerException;

  /**
   * Returns a string representation of the action.
   *
   * @return a string representation of the action.
   */
  String getAsString();
}
