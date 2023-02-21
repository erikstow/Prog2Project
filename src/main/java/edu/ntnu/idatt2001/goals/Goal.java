package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/** The interface Goal.
 * A goal that can be fulfilled by a player.
 */
public interface Goal {
  boolean isFulfilled(Player player);
}
