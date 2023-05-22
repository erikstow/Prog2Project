package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;

public class StartGamePressedAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {

    Player player = new Player(model.getStartingPlayer());
    Game game = new Game(player, model.getStory(), model.getGoals());
    model.setGame(game);
    controller.update(
        new DataUpdateEvent("passage", game.begin()), GameController.class::isInstance);
    controller.update(
        new DataUpdateEvent("player", player), GameController.class::isInstance);
    model.setCurrentScreen(ApplicationScreenType.PASSAGE_SCREEN);
    controller.update(
        new DataUpdateEvent("gameStarted", null), SettingsController.class::isInstance);

    controller.getMusicManager().stopAllTracks();
    controller.getMusicManager().playTrack("game");
  }
}
