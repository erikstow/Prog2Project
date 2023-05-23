package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import javafx.scene.control.Alert;

/**
 * The ErrorAction class implements the ControllerAction interface to handle error scenarios.
 * It is used when an exception occurs in the application. It displays an alert with the
 * exception type and message to inform the user about the occurred error.
 */
public class ErrorAction implements ControllerAction {
  /**
   * Executes the action for an error event.
   * An alert with the type of ERROR is created, showing the simple name of the exception's class
   * and its message. This alert is then displayed and waits for the user to close it.
   *
   * @param event      The ControllerEvent associated with this action.
   * @param controller The ApplicationController that controls the application flow.
   * @param state      The ApplicationState representing the current state of the application.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    Exception e = (Exception) event.getValue();

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error of type " + e.getClass().getSimpleName());
    alert.setContentText(e.getMessage());

    alert.showAndWait();
  }
}
