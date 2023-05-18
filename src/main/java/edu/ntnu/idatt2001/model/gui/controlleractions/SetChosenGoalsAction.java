package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import java.util.List;

public class SetChosenGoalsAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    model.setGoals(getGoalList(event.getValue()));
  }

  private List<Goal> getGoalList(Object value) {
    if (value instanceof List<?> list && (!list.isEmpty() && list.get(0) instanceof Goal)) {
        return (List<Goal>) list;

    }
    throw new IllegalArgumentException("Invalid value type for goals list");
  }
}