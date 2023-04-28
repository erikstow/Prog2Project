package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.events.ControllerEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableController implements ControllerNotifier{
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

  public List<ControllerObserver> getObservers() {
    return observers;
  }
}
