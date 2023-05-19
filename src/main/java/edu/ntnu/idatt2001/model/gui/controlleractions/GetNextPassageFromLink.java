package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import java.util.List;

public class GetNextPassageFromLink implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    Link link = (Link) event.getValue();
    Passage passage = model.getGame().go(link);

    if (model.getGame().getPlayer().getHealth() <= 0) {
      passage = generatePlayerDeadPassage(model.getGame().getPlayer(), model.getGoals());
    } else if (passage.getTitle().equals("End")) {
      passage = generateEndPassage(model.getGame().getPlayer(), model.getGoals());
    }

    DataUpdateEvent next = new DataUpdateEvent("passage", passage);
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", model.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
  }

  private Passage generatePlayerDeadPassage(Player player, List<Goal> goals) {
    return new Passage("You died!", createPlayerStats(player, goals));
  }

  private Passage generateEndPassage(Player player, List<Goal> goals) {
    boolean allGoalsFulfilled = goals.stream().allMatch(goal -> goal.isFulfilled(player));
    String title = allGoalsFulfilled ? "Congratulations!" : "You failed your goals!";
    return new Passage(title, createPlayerStats(player, goals));
  }

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
