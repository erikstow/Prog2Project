package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

public class SetStoryAction implements ControllerAction {

  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    model.setStory((Story) event.getValue());
    model.setCurrentScreen(ScreenType.CREATION_SCREEN);
  }
}
