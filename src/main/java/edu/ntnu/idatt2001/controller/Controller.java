package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The Controller class is an abstract class that implements
 * ControllerNotifier and ControllerObserver interfaces.
 * It maintains a list of observers and defines methods
 * to add/remove observers and update them with events.
 * It serves as a base class for other specific controller classes.
 */
public abstract class Controller implements ControllerNotifier, ControllerObserver {
  private final List<ControllerObserver> observers = new ArrayList<>();

  /**
   * Adds a ControllerObserver to the list of observers that are to be notified of ControllerEvents.
   *
   * @param controller the ControllerObserver to be added
   */
  @Override
  public void addObserver(ControllerObserver controller) {
    observers.add(controller);
  }


  /**
   * Removes a ControllerObserver from the list of observers.
   *
   * @param controller the ControllerObserver to be removed
   */
  @Override
  public void removeObserver(ControllerObserver controller) {
    observers.remove(controller);
  }

  /**
   * Notifies all registered observers of the given ControllerEvent.
   *
   * @param event the ControllerEvent that observers should be notified of
   */
  @Override
  public void update(ControllerEvent event) {
    for (ControllerObserver observer : observers) {
      observer.onUpdate(event);
    }
  }

  /**
   * Notifies only those registered observers of
   * the given ControllerEvent that satisfy the given filter.
   *
   * @param event the ControllerEvent that observers should be notified of
   * @param filter a Predicate that observers must satisfy in order to be notified
   */
  @Override
  public void update(ControllerEvent event, Predicate<ControllerObserver> filter) {
    for (ControllerObserver observer : observers) {
      if (filter.test(observer)) {
        observer.onUpdate(event);
      }
    }
  }
}
