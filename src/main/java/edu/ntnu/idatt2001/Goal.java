package edu.ntnu.idatt2001;

/**
 * Interface for classes that check if the player has fulfills certain contitions.
 */
public interface Goal {
  boolean isFulfilled(Player player);
}
