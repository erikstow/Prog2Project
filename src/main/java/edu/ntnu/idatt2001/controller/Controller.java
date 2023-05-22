package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Controller implements ControllerNotifier, ControllerObserver {
  private final List<ControllerObserver> observers = new ArrayList<>();

  @Override
  public void addObserver(ControllerObserver controller) {
    observers.add(controller);
  }

  @Override
  public void removeObserver(ControllerObserver controller) {
    observers.remove(controller);
  }

  @Override
  public void update(ControllerEvent event) {
    for (ControllerObserver observer : observers) {
      observer.onUpdate(event);
    }
  }

  @Override
  public void update(ControllerEvent event, Predicate<ControllerObserver> filter) {
    for (ControllerObserver observer : observers) {
      if (filter.test(observer)) {
        observer.onUpdate(event);
      }
    }
  }
}
