package edu.ntnu.idatt2001.model.events;

import edu.ntnu.idatt2001.controller.ControllerNotifier;

public class ErrorEvent extends ControllerEvent {
  private final Exception exception;

  public ErrorEvent(ControllerNotifier source, Exception exception) {
    super(source);
    this.exception = exception;
  }

  public Exception getException() {
    return exception;
  }
}
