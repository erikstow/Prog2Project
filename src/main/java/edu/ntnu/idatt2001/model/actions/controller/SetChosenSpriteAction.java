package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The SetChosenSpriteAction class represents an
 * action to set the chosen sprite in the game controller.
 * This action is typically triggered when a
 * user chooses a sprite for the player character in the game.
 */
public class SetChosenSpriteAction implements ControllerAction {
  /**
   * Executes the action to set the chosen sprite in the game controller.
   *
   * @param event      The ControllerEvent that triggered this action.
   *                   The chosen sprite is expected to be
   *                   stored in the value of the event.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws Exception If an error occurs during the action execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state)
      throws Exception {
    controller.update(new DataUpdateEvent("chosenSprite", event.getValue()),
        GameController.class::isInstance);
  }
}
