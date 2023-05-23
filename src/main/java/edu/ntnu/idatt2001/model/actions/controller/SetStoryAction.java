package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The SetStoryAction class represents an action to
 * set the story for the game in the application state.
 * This action is typically triggered when a new story is chosen or created for the game.
 */
public class SetStoryAction implements ControllerAction {

  /**
   * Executes the action to set the story for the game in the application state.
   * After setting the story, it also changes the current screen to the creation screen.
   *
   * @param event      The ControllerEvent that triggered this action.
   *                   The chosen story is expected to be
   *                   stored in the value of the event.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   */
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    state.setStory((Story) event.getValue());
    state.setCurrentScreen(ApplicationScreenType.CREATION_SCREEN);
  }
}
