package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

public class ClickMuteAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model)
      throws Exception {
    controller.getMusicManager().muteToggle();
    model.isMusicOnProperty().set(!model.isMusicOnProperty().get());
  }
}
