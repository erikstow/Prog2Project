package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;

public class SetStoryAction implements ControllerAction {

  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {
    model.setStory((Story) event.getValue());
    model.setCurrentScreen(ApplicationScreenType.CREATION_SCREEN);
  }
}
