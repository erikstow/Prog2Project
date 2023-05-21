package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.view.MenubarScreenBuilder;
import edu.ntnu.idatt2001.view.SettingsScreenBuilder;
import javafx.scene.layout.Region;

public class MenubarController extends Controller {
  private final Region view;

  public MenubarController() {
    view = new MenubarScreenBuilder(this::buttonAction).build();
  }

  private void buttonAction(String action) {
    update(new DataUpdateEvent(action, null));
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    throw new UnsupportedOperationException("Can't call onUpdate on MenubarController");
  }

  public Region getView() {
    return view;
  }
}
