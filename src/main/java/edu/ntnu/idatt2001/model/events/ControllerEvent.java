package edu.ntnu.idatt2001.model.events;

/**
 * The ControllerEvent interface represents an event in the Controller layer.
 * Implementing classes should provide a key and a value
 * which together represent the details of an event.
 * This interface is typically used to pass data within the
 * MVC architecture of an application, specifically between the Controller and the Model.
 */
public interface ControllerEvent {
  String getKey();

  Object getValue();
}
