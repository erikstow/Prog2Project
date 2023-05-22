package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;

public class SetChosenSpriteAction implements ControllerAction {
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model)
      throws Exception {
    controller.update(new DataUpdateEvent("chosenSprite", event.getValue()), GameController.class::isInstance);
  }
}
