package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;

public class StartGamePressedAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    Game game = new Game(new Player(model.getStartingPlayer()), model.getStory(), model.getGoals());
    model.setGame(game);
    controller.update(new DataUpdateEvent("passage", model.getGame().begin()), GameController.class::isInstance);
    controller.update(new DataUpdateEvent("player", model.getGame().getPlayer()), GameController.class::isInstance);
    controller.changeScreen(ScreenType.PASSAGE_SCREEN);
    System.out.println(model.getGoals());
  }
}
