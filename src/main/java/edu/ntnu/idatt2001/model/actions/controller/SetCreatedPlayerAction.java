package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.state.ApplicationState;

public class SetCreatedPlayerAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model) {
    Player player = (Player) event.getValue();
    model.setStartingPlayer(player);
  }
}
