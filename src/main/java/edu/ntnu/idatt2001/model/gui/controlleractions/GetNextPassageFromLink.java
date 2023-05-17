package edu.ntnu.idatt2001.model.gui.controlleractions;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;

public class GetNextPassageFromLink implements ControllerAction {

  @Override
  public void execute(ControllerEvent event, ApplicationController controller, ApplicationModel model) {
    Link link = (Link) event.getValue();
    Passage passage = model.getGame().go(link);
    DataUpdateEvent next = new DataUpdateEvent("passage", passage);
    DataUpdateEvent updatedPlayer = new DataUpdateEvent("player", model.getGame().getPlayer());
    controller.update(next, GameController.class::isInstance);
    controller.update(updatedPlayer, GameController.class::isInstance);
  }
}
