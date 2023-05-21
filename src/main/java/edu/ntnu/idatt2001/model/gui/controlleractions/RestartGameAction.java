package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

import java.util.ArrayList;

public class RestartGameAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    model.setGame(new Game(new Player(model.getStartingPlayer()), model.getStory(), new ArrayList<>()));

    DataUpdateEvent next = new DataUpdateEvent("passage", model.getGame().begin());
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", model.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
    model.setCurrentScreen(ScreenType.PASSAGE_SCREEN);
  }
}
