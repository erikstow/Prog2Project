package edu.ntnu.idatt2001.model.goals;

import java.util.Arrays;

/**
 * The GoalFactory is a factory class that creates instances of Goal types
 * based on the provided GoalType enumeration. This class follows the factory pattern
 * to provide a way to encapsulate the instantiation of concrete classes.
 * It contains an enumeration, GoalType, representing different types of goals.
 * The class is not meant to be instantiated, hence the private constructor.
 */
public class GoalFactory {

  /**
   * Enum representing various types of Goals that can be created.
   */
  public enum GoalType {
    GOLDGOAL, SCOREGOAL, HEALTHGOAL, INVENTORYGOAL
  }

  /**
   * Private constructor to prevent instantiation of the factory class.
   */
  private GoalFactory() {
  }

  /**
   * This method returns an instance of the specific Goal
   * type based on the provided GoalType and value.
   *
   * @param goalType the type of Goal to create, must be one of the values in GoalType.
   * @param value the value associated with the specific Goal type.
   *              The value is used when creating the Goal instance.
   * @return an instance of the Goal corresponding to the provided GoalType.
   */
  public static Goal get(GoalType goalType, String value) {
    return switch (goalType) {
      case GOLDGOAL -> new GoldGoal(Integer.parseInt(value));
      case SCOREGOAL -> new ScoreGoal(Integer.parseInt(value));
      case HEALTHGOAL -> new HealthGoal(Integer.parseInt(value));
      case INVENTORYGOAL -> new InventoryGoal(Arrays.asList(value.split(",")));
    };
  }
}
