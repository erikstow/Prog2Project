package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import java.util.ArrayList;
import java.util.List;

/**
 * The SetChosenGoalsAction class represents an
 * action to set the chosen goals in the application state.
 * This action is typically triggered when the user selects their goals in the character creation.
 */
public class SetChosenGoalsAction implements ControllerAction {
  /**
   * Executes the action to set the chosen goals in the application state.
   *
   * @param event      The ControllerEvent that triggered this action.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws IllegalArgumentException If the event value is not a list of goals.
   * @throws Exception                If an error occurs during the action execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    state.setGoals(getGoalList(event.getValue()));
  }

  /**
   * Transforms the given object into a List of Goal objects.
   * If the object is not a List or the List does not contain Goal objects,
   * it throws an IllegalArgumentException.
   *
   * @param value The object to be transformed into a List of Goal.
   * @return The transformed List of Goal objects.
   * @throws IllegalArgumentException If the object can't be transformed into a List of Goal.
   */
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
