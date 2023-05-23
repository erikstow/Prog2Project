package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * This class represents an action in the controller to restart the game.
 * This action is expected to be triggered when a restart game event occurs.
 * It creates a new game with the initial settings
 * and sends updated passage and player data to the GameController.
 */
public class RestartGameAction implements ControllerAction {
  /**
   * Executes the restart game action. A new Game instance is created with a new Player object
   * based on the starting player of the model. The game is then set in the model.
   * The GameController is updated with the first passage of the new game and the new player.
   * The current screen of the model is set to the game screen.
   *
   * @param event      The ControllerEvent that triggered this action.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   * @throws Exception If an error occurs during the action execution.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {
    Player player = new Player(state.getStartingPlayer());
    Game game = new Game(player, state.getStory(), state.getGoals());
    state.setGame(game);

    DataUpdateEvent next = new DataUpdateEvent("passage", state.getGame().begin());
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", state.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
    state.setCurrentScreen(ApplicationScreenType.GAME_SCREEN);
  }
}
