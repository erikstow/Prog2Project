package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import java.util.ArrayList;
import java.util.List;

public class SetChosenGoalsAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {
    model.setGoals(getGoalList(event.getValue()));
  }

  private List<Goal> getGoalList(Object value) {
    if (value instanceof List<?> list) {
      if (list.isEmpty()) {
        return new ArrayList<>();
      } else if (list.get(0) instanceof Goal) {
        return (List<Goal>) list;
      }
    }
    throw new IllegalArgumentException("Invalid value type for goals list in: "
        + this.getClass().getSimpleName());
  }
}
