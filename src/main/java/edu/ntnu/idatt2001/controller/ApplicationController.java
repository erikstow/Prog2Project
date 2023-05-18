package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.model.gui.controlleractions.*;
import edu.ntnu.idatt2001.view.ApplicationScreenBuilder;
import javafx.scene.layout.Region;
import java.util.HashMap;
import java.util.Map;

public class ApplicationController extends Controller {
  private final Region view;
  private final ApplicationModel model;
  private final TitleScreenController titleScreenController;
  private final GameController gameController;
  private final CharacterScreenController characterScreenController;
  private final SettingsController settingsController;
  private final Map<String, ControllerAction> actions = new HashMap<>();

  public ApplicationController() {
    titleScreenController = new TitleScreenController();
    gameController = new GameController();
    characterScreenController = new CharacterScreenController();
    settingsController = new SettingsController();
    initObservers();

    model = new ApplicationModel();
    model.setCurrentScreen(titleScreenController.getView());

    view = new ApplicationScreenBuilder(model, this::settingsAction, this::helpAction).build();

    initActions();
  }

  private void initActions() {
    actions.put("story", new SetStoryAction());
    actions.put("linkToNextPassage", new GetNextPassageFromLink());
    actions.put("resumeGame", new ResumeGameAction());
    actions.put("returnToTitle", new ReturnToTitleAction());
    actions.put("restartGame", new RestartGameAction());
    actions.put("error", new ErrorAction());
    actions.put("createdPlayer", new SetCreatedPlayerAction());
    actions.put("chosenGoals", new SetChosenGoalsAction());
    actions.put("startGamePressed", new StartGamePressedAction());
    actions.put("exitGame", new ExitGameAction());
  }

  private void initObservers() {
    titleScreenController.addObserver(this);
    gameController.addObserver(this);
    settingsController.addObserver(this);
    characterScreenController.addObserver(this);
    addObserver(gameController);
    addObserver(characterScreenController);
    addObserver(settingsController);
    addObserver(titleScreenController);
  }

  private void settingsAction() {
    if (model.getCurrentScreen() == settingsController.getView()) {
      model.setCurrentScreen(model.getPreviousScreen());
    } else {
      model.setPreviousScreen(model.getCurrentScreen());
      changeScreen(ScreenType.SETTINGS_SCREEN);
    }
  }

  private void helpAction() {
    ScreenType currentScreenType = null;
    Region currentScreen = model.getCurrentScreen();
    if (currentScreen == titleScreenController.getView()) {
      currentScreenType = ScreenType.TITLE_SCREEN;
    } else if (currentScreen == characterScreenController.getView()) {
      currentScreenType = ScreenType.CREATION_SCREEN;
    } else if (currentScreen == gameController.getView()) {
      currentScreenType = ScreenType.PASSAGE_SCREEN;
    } else if (currentScreen == settingsController.getView()) {
      currentScreenType = ScreenType.SETTINGS_SCREEN;
    }
    try {
      new HelpAction().execute(new DataUpdateEvent("currentScreenType", currentScreenType), this, model);
    } catch (Exception e) {
      onUpdate(new DataUpdateEvent("error", e));
    }
  }

  public void changeScreen(ScreenType screen) {
    switch (screen) {
      case TITLE_SCREEN -> model.setCurrentScreen(titleScreenController.getView());
      case CREATION_SCREEN -> model.setCurrentScreen(characterScreenController.getView());
      case SETTINGS_SCREEN -> model.setCurrentScreen(settingsController.getView());
      case PASSAGE_SCREEN -> model.setCurrentScreen(gameController.getView());
    }
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    String key = event.getKey();
    ControllerAction action = actions.get(key);
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

  public Region getView() {
    return view;
  }
}
