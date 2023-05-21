package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.model.gui.controlleractions.ControllerAction;
import edu.ntnu.idatt2001.model.gui.controlleractions.ControllerActionFactory;
import edu.ntnu.idatt2001.util.MusicManager;
import edu.ntnu.idatt2001.view.ApplicationScreenBuilder;
import javafx.scene.layout.Region;

public class ApplicationController extends Controller {
  private final Region view;
  private final ApplicationModel model;
  private final ControllerActionFactory actionFactory;
  private final MusicManager musicManager;

  public ApplicationController(Region titleView,
                               Region gameView,
                               Region characterView,
                               Region settingsView,
                               Region menubarView,
                               MusicManager musicManager) {
    model = new ApplicationModel();
    model.setCurrentScreen(ScreenType.TITLE_SCREEN);

    view = new ApplicationScreenBuilder(model, titleView, gameView, characterView, settingsView, menubarView).build();

    actionFactory = new ControllerActionFactory();

    this.musicManager = musicManager;
    musicManager.playTrack("title");
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    String key = event.getKey();
    ControllerAction action = actionFactory.createAction(key);
    if (action != null) {
      try {
        action.execute(event, this, model);
      } catch (Exception e) {
        onUpdate(new DataUpdateEvent("error", e));
      }
    } else {
      onUpdate(new DataUpdateEvent("error", new IllegalArgumentException("Unknown key: " + key)));
    }
  }

  public MusicManager getMusicManager() {
    return musicManager;
  }

  public Region getView() {
    return view;
  }
}
