package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.goals.Goal;

import java.util.List;
import java.util.Objects;

/**
 * Game is a facade for a Paths game. The class links a player to a story and has methods to start
 * and navigate the game. The class also contains a list of goals that the player must acomplish to
 * win the game.
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
  public Game(Player player, Story story, List<Goal> goals) throws NullPointerException {
    Objects.requireNonNull(player, "Player cannot be null");
    Objects.requireNonNull(story, "Story cannot be null");
    Objects.requireNonNull(goals, "Goals cannot be null");
    
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  /**
   * Method to get the player.
   * 
   * @return The player.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Method to get the story.
   *
   * @return The story.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Method to get the goals.
   *
   * @return The goals.
   */
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
  public Passage go(Link link) throws NullPointerException {
    Objects.requireNonNull(link, "Link cannot be null");

    return story.getPassage(link);
  }
}
