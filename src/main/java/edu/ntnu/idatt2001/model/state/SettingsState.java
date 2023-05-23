package edu.ntnu.idatt2001.model.state;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Represents the state of the game settings at any given time.
 * Currently this only includes a property representing if a restart is allowed.
 */
public class SettingsState {
  private final BooleanProperty restartAllowed = new SimpleBooleanProperty(false);

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
