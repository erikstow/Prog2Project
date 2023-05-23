package edu.ntnu.idatt2001.model.events;

/**
 * The DataUpdateEvent class represents an event that signifies a data update.
 * It implements the ControllerEvent interface and is used to
 * pass data from the Controller to the Model in the MVC architecture.
 * Instances of this class hold a key and a value which together
 * represent the details of a data update event.
 */
public class DataUpdateEvent implements ControllerEvent {
  private final String key;
  private final Object value;

  /**
   * Constructs a new DataUpdateEvent with a specified key and value.
   *
   * @param key   the key of the event, typically a String that
   *              gives context or categorization to the event.
   * @param value the value of the event, an Object that typically
   *              holds the data or the details for the event.
   */
  public DataUpdateEvent(String key, Object value) {
    this.key = key;
    this.value = value;
  }


  /**
   * Returns the key of this event.
   *
   * @return a String key for this event.
   */
  public String getKey() {
    return key;
  }

  /**
   * Returns the value associated with this event.
   *
   * @return an Object that represents the value of this event.
   */
  public Object getValue() {
    return value;
  }
}
