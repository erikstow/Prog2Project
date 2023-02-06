package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.Player;

/**
 * Interface for classes that check if the player has fulfills certain contitions.
 */
public interface Goal {
  boolean isFulfilled(Player player);
}
