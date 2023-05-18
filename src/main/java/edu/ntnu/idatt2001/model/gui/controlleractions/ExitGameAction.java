package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import javafx.application.Platform;

public class ExitGameAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) throws Exception {
    Platform.exit();
  }
}
