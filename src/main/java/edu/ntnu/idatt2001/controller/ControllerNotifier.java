package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;

import java.util.function.Predicate;

public interface ControllerNotifier {
  void update(ControllerEvent event);
  void update(ControllerEvent event, Predicate<ControllerObserver> filter);

  void addObserver(ControllerObserver controller);

  void removeObserver(ControllerObserver controller);
}
