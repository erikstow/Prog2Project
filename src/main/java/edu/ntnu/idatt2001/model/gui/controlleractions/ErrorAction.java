package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import javafx.scene.control.Alert;

public class ErrorAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    Exception e = (Exception) event.getValue();

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error of type " + e.getClass().getSimpleName());
    alert.setContentText(e.getMessage());

    alert.showAndWait();
  }
}
