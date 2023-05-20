package edu.ntnu.idatt2001.model.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SettingsModel {
  private BooleanProperty restartAllowed = new SimpleBooleanProperty(false);

  public boolean isRestartAllowed() {
    return restartAllowed.get();
  }

  public BooleanProperty restartAllowedProperty() {
    return restartAllowed;
  }

  public void setRestartAllowed(boolean restartAllowed) {
    this.restartAllowed.set(restartAllowed);
  }
}
