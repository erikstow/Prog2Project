package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

public class ClickSettingsAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) throws Exception {

    if (model.getCurrentScreen() == ScreenType.SETTINGS_SCREEN) {
      model.setCurrentScreen(model.getPreviousScreen());
    } else {
      model.setPreviousScreen(model.getCurrentScreen());
      model.setCurrentScreen(ScreenType.SETTINGS_SCREEN);
    }
  }
}
