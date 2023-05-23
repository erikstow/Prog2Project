package edu.ntnu.idatt2001.model.actions.controller;

/**
 * The ControllerActionFactory class provides functionality to create
 * various types of ControllerAction objects based on a given action type.
 * This class simplifies the process of creating different ControllerActions
 * and reduces the need for a series of if-else statements elsewhere in the code.
 */
public class ControllerActionFactory {
  /**
   * Creates a new ControllerAction object of the type specified by the actionType parameter.
   *
   * @param actionType A String indicating the type of action to be created.
   * @return A ControllerAction of the appropriate type.
   * @throws IllegalArgumentException If the actionType parameter does not match
   *                                  any of the defined action types.
   */
  public ControllerAction createAction(String actionType) {
    return switch (actionType) {
      case "story" -> new SetStoryAction();
      case "linkToNextPassage" -> new GetNextPassageFromLink();
      case "resumeGame" -> new ResumeGameAction();
      case "returnToTitle" -> new ReturnToTitleAction();
      case "restartGame" -> new RestartGameAction();
      case "error" -> new ErrorAction();
      case "createdPlayer" -> new SetCreatedPlayerAction();
      case "chosenGoals" -> new SetChosenGoalsAction();
      case "startGamePressed" -> new StartGamePressedAction();
      case "exitGame" -> new ExitGameAction();
      case "settings" -> new ClickSettingsAction();
      case "help" -> new ClickHelpAction();
      case "music" -> new ClickMuteAction();
      case "chosenSprite" -> new SetChosenSpriteAction();
      default -> throw new IllegalArgumentException("Can't parse action: " + actionType);
    };
  }
}
