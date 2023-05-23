package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import javafx.application.Platform;

/**
 * The ExitGameAction class implements the ControllerAction interface to handle exit game events.
 * This action is used when the user chooses to exit the game. It closes the JavaFX application.
 */
public class ExitGameAction implements ControllerAction {
  /**
   * Executes the action to exit the game.
   * This method uses the JavaFX Platform's exit method to terminate the JavaFX application.
   *
   * @param event      The ControllerEvent associated with this action.
   * @param controller The ApplicationController that controls the application flow.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws Exception If an error occurs during execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state)
      throws Exception {
    Platform.exit();
  }
}
