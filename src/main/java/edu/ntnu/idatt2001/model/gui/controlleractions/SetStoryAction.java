package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

import java.util.ArrayList;

public class SetStoryAction implements ControllerAction {

  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    model.setStory((Story) event.getValue());
    model.setGame(new Game(new Player(model.getStartingPlayer()), model.getStory(), new ArrayList<>()));
    controller.update(new DataUpdateEvent("passage", model.getGame().begin()), GameController.class::isInstance);
    controller.update(new DataUpdateEvent("player", model.getGame().getPlayer()), GameController.class::isInstance);
    controller.changeScreen(ScreenType.CREATION_SCREEN);
  }
}
