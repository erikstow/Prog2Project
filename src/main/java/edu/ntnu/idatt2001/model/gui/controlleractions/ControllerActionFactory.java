package edu.ntnu.idatt2001.model.gui.controlleractions;

public class ControllerActionFactory {
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
      default -> throw new IllegalArgumentException("Can't parse action: " + actionType);
    };
  }
}
