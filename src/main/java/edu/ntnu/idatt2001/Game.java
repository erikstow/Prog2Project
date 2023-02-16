package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.goals.Goal;

import java.util.List;

/**
 * Game is a facade for a Paths game. The class links a player to a story and has methods to start
 * and navigate the game.
 */
public class Game {
  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**Constructor to Game class.
   *
   * @param player the player in the game
   * @param story the story the game follows
   * @param goals list of goals to be acomplished in the game
   */
  public Game(Player player, Story story, List<Goal> goals) {
    if (player == null || story == null || goals == null) {
      throw new IllegalArgumentException("Player, story and goals cannot be null");
    }
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  public Player getPlayer() {
    return player;
  }

  public Story getStory() {
    return story;
  }

  public List<Goal> getGoals() {
    return goals;
  }

  /** Method to start the game.
   * 
   * @return The opening passage.
   */
  public Passage begin() {
    return this.story.getOpeningPassage();
  }

  /** Method to navigate the game.
   *
   * @param link The link to the corresponding passage.
   *
   * @return The passage.
   */
  public Passage go(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    return story.getPassage(link);
  }
}
