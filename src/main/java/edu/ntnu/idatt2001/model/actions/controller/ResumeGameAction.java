package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * This class represents an action in the controller to resume the game.
 * This action is expected to be triggered when a resume game event occurs.
 * It sets the current screen of the model to the previous screen.
 */
public class ResumeGameAction implements ControllerAction {
  /**
   * Executes the resume game action. The current screen of the model is set to its previous screen.
   *
   * @param event      The ControllerEvent that triggered this action.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws Exception If an error occurs during the action execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    state.setCurrentScreen(state.getPreviousScreen());
  }
}
