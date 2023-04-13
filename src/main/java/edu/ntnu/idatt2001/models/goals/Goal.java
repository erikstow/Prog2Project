package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.game.Player;

/** 
 * The interface Goal.
 * A goal that can be fulfilled by a player.
 * Has a method to check if the goal is fulfilled.
 */
public interface Goal {
  boolean isFulfilled(Player player) throws NullPointerException;
}
