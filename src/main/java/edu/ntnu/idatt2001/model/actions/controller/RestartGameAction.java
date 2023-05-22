package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;

public class RestartGameAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {
    Player player = new Player(model.getStartingPlayer());
    Game game = new Game(player, model.getStory(), model.getGoals());
    model.setGame(game);

    DataUpdateEvent next = new DataUpdateEvent("passage", model.getGame().begin());
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", model.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
    model.setCurrentScreen(ApplicationScreenType.PASSAGE_SCREEN);
  }
}
