package edu.ntnu.idatt2001;

import java.util.List;

/**
 * Game is a facade for a Paths game. The class links a player to a story and has methods to start
 * and navigate the game.
 */
public class Game {
  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**
   *
   *
   * @param player the player in the game
   * @param story the story the game follows
   * @param goals list of goals to be acomplished in the game
   */
  public Game(Player player, Story story, List<Goal> goals) {
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

  public Passage begin() {
    return this.story.getOpeningPassage();
  }

  public Passage go(Link link) {
    return story.getPassage(link);
  }
}
