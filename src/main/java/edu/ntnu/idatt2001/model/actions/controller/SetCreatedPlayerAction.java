package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The SetCreatedPlayerAction class represents an action to set the
 * initial player for the game in the application state.
 * This action is typically triggered when a new player is created in the game.
 */
public class SetCreatedPlayerAction implements ControllerAction {
  /**
   * Executes the action to set the initial player for the game in the application state.
   *
   * @param event      The ControllerEvent that triggered this action.
   *                   The newly created player is expected to be
   *                   stored in the value of the event.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    Player player = (Player) event.getValue();
    state.setStartingPlayer(player);
  }
}
