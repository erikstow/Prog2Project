package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;

public interface ControllerObserver {
  void onUpdate(ControllerEvent event);
}
