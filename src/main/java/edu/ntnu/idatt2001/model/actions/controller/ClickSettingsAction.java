package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;

public class ClickSettingsAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model)
      throws Exception {

    if (model.getCurrentScreen() == ApplicationScreenType.SETTINGS_SCREEN) {
      model.setCurrentScreen(model.getPreviousScreen());
    } else {
      model.setPreviousScreen(model.getCurrentScreen());
      model.setCurrentScreen(ApplicationScreenType.SETTINGS_SCREEN);
    }
  }
}
