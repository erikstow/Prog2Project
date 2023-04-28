package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.events.ControllerEvent;

public interface ControllerNotifier {
  void update(ControllerEvent event);

  void addObserver(ControllerObserver controller);

  void removeObserver(ControllerObserver controller);
}
