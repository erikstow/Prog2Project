package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.game.Player;

/** 
 * The interface Goal.
 * A goal that can be fulfilled by a player.
 * Has a method to check if the goal is fulfilled.
 */
public interface Goal {
  boolean isFulfilled(Player player) throws NullPointerException;
}
