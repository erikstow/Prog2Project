package edu.ntnu.idatt2001.model.actions;

import edu.ntnu.idatt2001.model.game.Player;

/** 
 * The interface Action.
 * An action is a task that can be performed by a player.
 * The action is performed by calling the execute method.
 */ 
public interface Action {
  void execute(Player player) throws NullPointerException;
  String getAsString();
}
