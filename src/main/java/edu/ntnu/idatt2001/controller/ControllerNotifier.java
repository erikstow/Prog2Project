package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import java.util.function.Predicate;

/**
 * The ControllerNotifier interface defines methods
 * for managing ControllerObservers and notifying them
 * of ControllerEvents.
 */
public interface ControllerNotifier {
  /**
   * Notifies all registered observers of the provided ontrollerEvent.
   *
   * @param event the event to be sent to all observers
   */
  void update(ControllerEvent event);

  /**
   * Notifies a subset of registered observers of the provided
   * ControllerEvent, where the subset is determined
   * by a specified filter.
   *
   * @param event  the event to be sent to the filtered observers
   * @param filter a {@link Predicate} determining which observers should receive the event
   */
  void update(ControllerEvent event, Predicate<ControllerObserver> filter);

  /**
   * Registers a new observer that will be notified of future ontrollerEvents.
   *
   * @param controller the observer to be added
   */
  void addObserver(ControllerObserver controller);

  /**
   * Unregisters an observer, so it will no longer be notified of ControllerEvents.
   *
   * @param controller the observer to be removed
   */
  void removeObserver(ControllerObserver controller);
}
