package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import javafx.application.Platform;

public class ExitGameAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model)
      throws Exception {
    Platform.exit();
  }
}
