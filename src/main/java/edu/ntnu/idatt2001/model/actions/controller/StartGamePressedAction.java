package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;

/**
 * The StartGamePressedAction class represents an action
 * to start the game when the 'Start Game' button is pressed.
 * It sets up a new Game instance and changes the current screen to the game screen.
 * It also plays the game's background music.
 */
public class StartGamePressedAction implements ControllerAction {
  /**
   * Executes the action to start the game.
   * It sets up a new Game instance using the starting player, the story and the goals
   * provided by the ApplicationState.
   * The GameController and SettingsController are then updated with the relevant data.
   * It also plays the game's background music.
   *
   * @param event      The ControllerEvent that triggered this action.
   *                   The event is not used in this method since
   *                   starting the game does not require any additional data from the event.
   * @param controller The ApplicationController that is controlling the flow of the application.
   * @param state      The ApplicationState representing the current state of the application.
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state) {

    Player player = new Player(state.getStartingPlayer());
    Game game = new Game(player, state.getStory(), state.getGoals());
    state.setGame(game);
    controller.update(
        new DataUpdateEvent("passage", game.begin()), GameController.class::isInstance);
    controller.update(
        new DataUpdateEvent("player", player), GameController.class::isInstance);
    state.setCurrentScreen(ApplicationScreenType.GAME_SCREEN);
    controller.update(
        new DataUpdateEvent("gameStarted", null), SettingsController.class::isInstance);

    controller.getMusicManager().stopAllTracks();
    controller.getMusicManager().playTrack("game");
  }
}
