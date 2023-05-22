package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.state.SettingsState;
import edu.ntnu.idatt2001.view.SettingsScreenBuilder;
import javafx.scene.layout.Region;

public class SettingsController extends Controller {
  private final Region view;
  private final SettingsState model;

  public SettingsController() {
    model = new SettingsState();

    view = new SettingsScreenBuilder(model, this::buttonAction).build();
  }

  private void buttonAction(String action) {
    update(new DataUpdateEvent(action, null));
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    String key = event.getKey();
    if (key.equals("reset")) {
      model.setRestartAllowed(false);
    } else if (key.equals("gameStarted")) {
      model.setRestartAllowed(true);
    }
  }

  public Region getView() {
    return this.view;
  }
}
