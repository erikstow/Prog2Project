package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;

public class SetCreatedPlayerAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    Player player = (Player) event.getValue();
    model.setStartingPlayer(player);
  }
}
