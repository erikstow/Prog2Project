package edu.ntnu.idatt2001.model.events;

import edu.ntnu.idatt2001.controller.ControllerNotifier;

public abstract class ControllerEvent {
  private final ControllerNotifier source;

  protected ControllerEvent(ControllerNotifier source) {
    this.source = source;
  }

  public ControllerNotifier getSource() {
    return source;
  }
}
