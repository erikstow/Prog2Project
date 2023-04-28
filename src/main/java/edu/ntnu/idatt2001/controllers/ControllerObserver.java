package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.events.ControllerEvent;

public interface ControllerObserver {
  void onUpdate(ControllerEvent event);
}
