package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * This class represents an action in the controller to return to the title screen.
 * This action is expected to be triggered when a return to title event occurs.
 * It sets the current screen of the model to the title screen, stops all music tracks,
 * and then plays the "title" track.
 */
public class ReturnToTitleAction implements ControllerAction {
  /**
   * Executes the return to title action. It stops all music tracks, plays the "title" track,
   * and sets the current screen of the model to the title screen.
   *
   * @param event      The ControllerEvent that triggered this action.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws Exception If an error occurs during the action execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    controller.update(new DataUpdateEvent("reset", null));
    controller.getMusicManager().stopAllTracks();
    controller.getMusicManager().playTrack("title");
    state.setCurrentScreen(ApplicationScreenType.TITLE_SCREEN);
  }
}
