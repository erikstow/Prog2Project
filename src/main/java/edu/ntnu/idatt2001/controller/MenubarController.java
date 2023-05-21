package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.view.MenubarScreenBuilder;
import javafx.scene.layout.Region;

public class MenubarController extends Controller {
  private final Region view;

  MenubarController() {
    view = new MenubarScreenBuilder(this::settingsAction, this::helpAction, this::musicAction).build();
  }

  private void settingsAction() {
    update(new DataUpdateEvent("settings", null));
  }

  private void helpAction() {
    update(new DataUpdateEvent("help", null));
  }

  private void musicAction() {
    update(new DataUpdateEvent("music", null));
  }


  @Override
  public void onUpdate(ControllerEvent event) {
  }

  public Region getView() {
    return view;
  }
}
