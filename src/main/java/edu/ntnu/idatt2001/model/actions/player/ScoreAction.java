package edu.ntnu.idatt2001.model.actions.player;

import edu.ntnu.idatt2001.model.game.Player;
import java.util.Objects;

/** 
 * The class ScoreAction.
 * An action that adds score to the player.
 * The amount of score to add is specified in the constructor.
 */ 
public class ScoreAction implements Action {
  private final int points;

  /** 
   * The constructor for the ScoreAction class.
   *
   * @param points The amount of score to add to the player.
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Method to add score to the player.
   *
   * @param player The player to perform the action on.
   */
  @Override
  public void execute(Player player) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");

    player.addScore(points);
  }

  public String getAsString() {
    return "!ScoreAction:" + this.points + "\n";
  }
}
