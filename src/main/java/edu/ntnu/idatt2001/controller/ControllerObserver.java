package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;

/**
 * The ControllerObserver interface defines a method for handling ontrollerEvents.
 * This interface is typically implemented by classes that need
 * to react to changes or events within the application.
 */
public interface ControllerObserver {
  /**
   * This method is called when a ontrollerEvent occurs that the observer is registered to handle.
   *
   * @param event the event that has occurred
   */

  void onUpdate(ControllerEvent event);
}
