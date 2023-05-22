package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;

public class ReturnToTitleAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {
    controller.update(new DataUpdateEvent("reset", null));
    controller.getMusicManager().stopAllTracks();
    controller.getMusicManager().playTrack("title");
    model.setCurrentScreen(ApplicationScreenType.TITLE_SCREEN);
  }
}
