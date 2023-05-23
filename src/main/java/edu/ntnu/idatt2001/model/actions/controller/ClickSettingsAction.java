package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The ClickSettingsAction class implements the ControllerAction interface
 * and is responsible for executing a 'Settings' action in the game.
 * When the 'Settings' action is executed, it toggles between the current screen
 * and the settings screen.
 */
public class ClickSettingsAction implements ControllerAction {

  /**
   * Executes the 'Settings' action on the game.
   * If the current screen is the settings screen, it will change the screen
   * to the previous screen. Otherwise, it sets the current screen to the settings screen.
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

    if (state.getCurrentScreen() == ApplicationScreenType.SETTINGS_SCREEN) {
      state.setCurrentScreen(state.getPreviousScreen());
    } else {
      state.setPreviousScreen(state.getCurrentScreen());
      state.setCurrentScreen(ApplicationScreenType.SETTINGS_SCREEN);
    }
  }
}
