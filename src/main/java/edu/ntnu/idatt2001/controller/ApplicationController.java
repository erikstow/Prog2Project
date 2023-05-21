package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.model.gui.controlleractions.*;
import edu.ntnu.idatt2001.util.MusicManager;
import edu.ntnu.idatt2001.view.ApplicationScreenBuilder;
import javafx.scene.layout.Region;

public class ApplicationController extends Controller {
  private final Region view;
  private final ApplicationModel model;
  private final TitleScreenController titleScreenController;
  private final GameController gameController;
  private final CharacterScreenController characterScreenController;
  private final SettingsController settingsController;
  private final MenubarController menubarController;
  private final ControllerActionFactory actionFactory;
  private final MusicManager musicManager;

  public ApplicationController() {
    titleScreenController = new TitleScreenController();
    gameController = new GameController();
    characterScreenController = new CharacterScreenController();
    settingsController = new SettingsController();
    menubarController = new MenubarController();

    initObservers();

    model = new ApplicationModel();
    model.setCurrentScreen(ScreenType.TITLE_SCREEN);

    view = new ApplicationScreenBuilder(model,
      titleScreenController.getView(),
      gameController.getView(),
      characterScreenController.getView(),
      settingsController.getView(),
      menubarController.getView()).build();

    actionFactory = new ControllerActionFactory();

    this.musicManager = new MusicManager();
    initMusic();
    musicManager.playTrack("title");
  }

  private void initMusic() {
    musicManager.loadTrack("title", "src/main/resources/sound/1.MainTheme-320bit.mp3");
    musicManager.loadTrack("game", "src/main/resources/sound/the-epic-2-by-rafael-krux.mp3");
    musicManager.loadTrack("boss", "src/main/resources/sound/Dragon-Castle.mp3");
  }

  private void initObservers() {
    titleScreenController.addObserver(this);
    gameController.addObserver(this);
    settingsController.addObserver(this);
    characterScreenController.addObserver(this);
    menubarController.addObserver(this);
    addObserver(gameController);
    addObserver(characterScreenController);
    addObserver(settingsController);
    addObserver(titleScreenController);
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
