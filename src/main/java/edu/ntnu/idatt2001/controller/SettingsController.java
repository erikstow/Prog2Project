package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.gui.SettingsModel;
import edu.ntnu.idatt2001.view.SettingsScreenBuilder;
import javafx.scene.layout.Region;

public class SettingsController extends Controller {
  private final Region view;
  private final SettingsModel model;

  SettingsController() {
    model = new SettingsModel();
    this.view = new SettingsScreenBuilder(model, this::resume, this::exitToTitle, this::restartGame).build();
  }

  private void resume() {
    update(new DataUpdateEvent("resumeGame", null));
  }

  private void restartGame() {
    update(new DataUpdateEvent("restartGame", null));
  }

  private void exitToTitle() {
    update(new DataUpdateEvent("returnToTitle", null));
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
