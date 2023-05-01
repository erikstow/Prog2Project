package edu.ntnu.idatt2001.model.events;

import edu.ntnu.idatt2001.controller.ControllerNotifier;

public class ScreenChangeEvent extends ControllerEvent {
  private final String identifier;

  public ScreenChangeEvent(ControllerNotifier source, String identifier) {
    super(source);
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }
}
