package edu.ntnu.idatt2001.model.actions.player;

/**
 * The ActionFactory is a factory class that creates instances of Action types
 * based on the provided ActionType enumeration. This class follows the factory pattern
 * to provide a way to encapsulate the instantiation of concrete classes.
 * It contains an enumeration, ActionType, representing different types of actions.
 * The class is not meant to be instantiated, hence the private constructor.
 */
public class ActionFactory {

  /**
   * Enum representing various types of Actions that can be created.
   */
  public enum ActionType {
    GOLDACTION, SCOREACTION, HEALTHACTION, INVENTORYACTION
  }

  /**
   * Private constructor to prevent instantiation of the factory class.
   */
  private ActionFactory() {
  }

  /**
   * This method returns an instance of the specific
   * Action type based on the provided ActionType and value.
   *
   * @param actionType the type of Action to create, must be one of the values in ActionType.
   * @param value the value associated with the specific Action type.
   *              The value is used when creating the Action instance.
   * @return an instance of the Action corresponding to the provided ActionType.
   */
  public static Action get(ActionType actionType, String value) {
    return switch (actionType) {
      case GOLDACTION -> new GoldAction(Integer.parseInt(value));
      case SCOREACTION -> new ScoreAction(Integer.parseInt(value));
      case HEALTHACTION -> new HealthAction(Integer.parseInt(value));
      case INVENTORYACTION -> new InventoryAction(value);
    };
  }
}
