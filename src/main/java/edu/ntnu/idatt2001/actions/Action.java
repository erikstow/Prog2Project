package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.Player;

/** The interface Action.
 * An action is a task that can be performed by a player.
 * The action is performed by calling the execute method.
 */ 
public interface Action {
  void execute(Player player);
}
