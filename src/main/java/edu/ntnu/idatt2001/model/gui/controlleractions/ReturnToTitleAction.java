package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

public class ReturnToTitleAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    controller.update(new DataUpdateEvent("reset", null));
    controller.getMusicManager().stopAllTracks();
    controller.getMusicManager().playTrack("title");
    controller.changeScreen(ScreenType.TITLE_SCREEN);
  }
}
