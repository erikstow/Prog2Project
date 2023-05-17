package edu.ntnu.idatt2001.model.goals;

import java.util.Arrays;

public class GoalFactory {
  
  public enum GoalType {
    GOLDGOAL, SCOREGOAL, HEALTHGOAL, INVENTORYGOAL
  }

  private GoalFactory() {
  }

  public static Goal get(GoalType goalType, String value) {
    return switch (goalType) {
      case GOLDGOAL -> new GoldGoal(Integer.parseInt(value));
      case SCOREGOAL -> new ScoreGoal(Integer.parseInt(value));
      case HEALTHGOAL -> new HealthGoal(Integer.parseInt(value));
      case INVENTORYGOAL -> new InventoryGoal(Arrays.asList(value.split(",")));
    };
  }

}
