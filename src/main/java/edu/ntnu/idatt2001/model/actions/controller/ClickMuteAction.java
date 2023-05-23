package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The ClickMuteAction class implements the ControllerAction interface
 * and is responsible for executing a 'Mute' action on the game.
 * When the 'Mute' action is executed, the game music is toggled between on and off state.
 */
public class ClickMuteAction implements ControllerAction {

  /**
   * Executes the 'Mute' action on the game.
   * This action toggles the music on/off state of the game.
   * If the music was previously playing, it's turned off and vice versa.
   *
   * @param event      The event that triggers this action.
   * @param controller The application controller.
   * @param state      The current state of the application.
   * @throws Exception if an error occurs during execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state)
      throws Exception {
    controller.getMusicManager().muteToggle();
    state.isMusicOnProperty().set(!state.isMusicOnProperty().get());
  }
}
