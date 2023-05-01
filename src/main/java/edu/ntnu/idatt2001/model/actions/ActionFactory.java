package edu.ntnu.idatt2001.model.actions;

public class ActionFactory {
  public enum ActionType {
    GOLDACTION, SCOREACTION, HEALTHACTION, INVENTORYACTION
  }
  private ActionFactory() {
  }

  public static Action get(ActionType actionType, String value) {
    return switch (actionType) {
      case GOLDACTION -> new GoldAction(Integer.parseInt(value));
      case SCOREACTION -> new ScoreAction(Integer.parseInt(value));
      case HEALTHACTION -> new HealthAction(Integer.parseInt(value));
      case INVENTORYACTION -> new InventoryAction(value);
    };
  }
}
