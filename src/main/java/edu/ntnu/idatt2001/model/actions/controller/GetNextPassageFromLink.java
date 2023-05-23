package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import java.util.List;

/**
 * The GetNextPassageFromLink class implements the
 * ControllerAction interface to handle game progress events.
 * This action is used to navigate to the next passage in the game,
 * update the player's stats and trigger game endings
 * based on player's health or whether they have reached the 'End' passage.
 */
public class GetNextPassageFromLink implements ControllerAction {
  /**
   * Executes the action to get the next passage.
   * The method follows a provided link, updates the game state accordingly,
   * and checks for special conditions,
   * such as the player's death or reaching the end of the game.
   *
   * @param event      The ControllerEvent associated with this action.
   *                   The event value should be a Link.
   * @param controller The ApplicationController that controls the application flow.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws IllegalArgumentException If an attempt is made to follow a broken link.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    Link link = (Link) event.getValue();
    Passage passage = state.getGame().go(link);

    if (passage == null) {
      throw new IllegalArgumentException("Can't follow a broken link: " + link.getText());
    }

    if (state.getGame().getPlayer().getHealth() <= 0) {
      passage = generatePlayerDeadPassage(state.getGame().getPlayer(), state.getGoals());
    } else if (passage.getTitle().equals("End")) {
      passage = generateEndPassage(state.getGame().getPlayer(), state.getGoals());
    }

    DataUpdateEvent next = new DataUpdateEvent("passage", passage);
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", state.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
  }

  /**
   * Generates a Passage to be shown when the player character dies.
   *
   * @param player The Player object representing the player's character in the game.
   * @param goals  The List of goals the player was aiming to achieve in the game.
   * @return A Passage object containing a death message and the player's final statistics.
   */
  private Passage generatePlayerDeadPassage(Player player, List<Goal> goals) {
    return new Passage("You died!", createPlayerStats(player, goals));
  }

  /**
   * Generates a Passage to be shown at the end of the game.
   * The title and content of the passage depends on whether the player achieved all their goals.
   *
   * @param player The Player object representing the player's character in the game.
   * @param goals  The List of goals the player was aiming to achieve in the game.
   * @return A Passage object containing a message
   *        indicating the player's success or failure, and the player's final statistics.
   */
  private Passage generateEndPassage(Player player, List<Goal> goals) {
    boolean allGoalsFulfilled = goals.stream().allMatch(goal -> goal.isFulfilled(player));
    String title = allGoalsFulfilled ? "Congratulations!" : "You failed your goals!";
    return new Passage(title, createPlayerStats(player, goals));
  }

  /**
   * Creates a string containing the player's final stats.
   * The stats include score, gold, inventory items, and the status of each goal.
   *
   * @param player The Player object representing the player's character in the game.
   * @param goals  The List of goals the player was aiming to achieve in the game.
   * @return A string containing the player's final statistics and the status of each goal.
   */
  private String createPlayerStats(Player player, List<Goal> goals) {
    StringBuilder sb = new StringBuilder("Your stats:\n");
    sb.append("Your score: ").append(player.getScore()).append("\n");
    sb.append("Your gold: ").append(player.getGold()).append("\n");
    sb.append("Your inventory: " + "\n");
    for (String item : player.getInventory()) {
      sb.append(item).append("\n");
    }
    sb.append("Your achieved goals: ").append("\n");
    for (Goal goal : goals) {
      if (goal.isFulfilled(player)) {
        sb.append(goal).append("\n");
      }
    }
    sb.append("Your unfulfilled goals:").append("\n");
    for (Goal goal : goals) {
      if (!goal.isFulfilled(player)) {
        sb.append(goal).append("\n");
      }
    }
    return sb.toString();
  }
}
