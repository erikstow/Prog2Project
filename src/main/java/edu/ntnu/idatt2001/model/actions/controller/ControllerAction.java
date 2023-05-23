package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The ControllerAction interface defines the actions that can be taken
 * by the controller in response to an event within the application.
 * Each ControllerAction is associated with a specific ControllerEvent,
 * and modifies the ApplicationController and ApplicationState accordingly.
 */
public interface ControllerAction {

  /**
   * Executes the action associated with a specific controller event.
   *
   * @param event      The event that triggers this action.
   * @param controller The application controller that the action is executed upon.
   * @param state      The current state of the application that might be modified by the action.
   * @throws Exception if an error occurs during execution.
   */
  void execute(ControllerEvent event,
               ApplicationController controller,
               ApplicationState state)
      throws Exception;
}
