package edu.ntnu.idatt2001.models.events;

import edu.ntnu.idatt2001.controllers.ControllerNotifier;

public abstract class ControllerEvent {
  private final ControllerNotifier source;

  protected ControllerEvent(ControllerNotifier source) {
    this.source = source;
  }

  public ControllerNotifier getSource() {
    return source;
  }
}
