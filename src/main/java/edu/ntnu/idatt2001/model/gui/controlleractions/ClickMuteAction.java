package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;

public class ClickMuteAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) throws Exception {
    controller.getMusicManager().muteToggle();
    model.isMusicOnProperty().set(!model.isMusicOnProperty().get());
  }
}
