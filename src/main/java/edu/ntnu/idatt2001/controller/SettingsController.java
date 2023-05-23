package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.state.SettingsState;
import edu.ntnu.idatt2001.view.SettingsScreenBuilder;
import javafx.scene.layout.Region;

/**
 * The SettingsController class extends Controller and manages the settings view in the application.
 * It listens to user actions on the settings
 * screen and relays them as DataUpdateEvent to other parts of the system.
 * It also updates the settings state based on the events it receives.
 */
public class SettingsController extends Controller {
  private final Region view;
  private final SettingsState state;

  /**
   * Constructs a new SettingsController and initializes its view and state model.
   */
  public SettingsController() {
    state = new SettingsState();

    view = new SettingsScreenBuilder(state, this::buttonAction).build();
  }

  /**
   * Performs the action associated with a button click on the settings screen.
   *
   * @param action The action to be performed.
   */
  private void buttonAction(String action) {
    update(new DataUpdateEvent(action, null));
  }

  /**
   * Updates the state of the settings based on the ControllerEvent received.
   * Responds to "reset" and "gameStarted"
   * events to control the availability of the restart function.
   *
   * @param event The ControllerEvent to be processed.
   */
  @Override
  public void onUpdate(ControllerEvent event) {
    String key = event.getKey();
    if (key.equals("reset")) {
      state.setRestartAllowed(false);
    } else if (key.equals("gameStarted")) {
      state.setRestartAllowed(true);
    }
  }

  /**
   * Returns the view managed by this controller.
   *
   * @return The view managed by this controller.
   */
  public Region getView() {
    return this.view;
  }
}
