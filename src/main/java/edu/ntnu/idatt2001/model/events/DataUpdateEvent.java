package edu.ntnu.idatt2001.model.events;

import edu.ntnu.idatt2001.controller.ControllerNotifier;

public class DataUpdateEvent implements ControllerEvent {
  private final String key;
  private final Object value;

  public DataUpdateEvent(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }
}
