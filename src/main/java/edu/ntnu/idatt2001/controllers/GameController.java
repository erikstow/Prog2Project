package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.events.ControllerEvent;
import edu.ntnu.idatt2001.models.events.DataUpdateEvent;
import edu.ntnu.idatt2001.models.events.ScreenChangeEvent;
import edu.ntnu.idatt2001.models.GameModel;
import edu.ntnu.idatt2001.models.ScreenType;
import edu.ntnu.idatt2001.views.GameViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GameController
    extends ObservableController
    implements ControllerObserver {
  private final Region view;
  private final GameModel model;
  private final TitleScreenController titleScreenController;

  public GameController() {
    model = new GameModel();
    this.titleScreenController = new TitleScreenController();
    model.setCurrentScreen(titleScreenController.getView());
    view = new GameViewBuilder(this::dummyAction, model).build();
    this.titleScreenController.addObserver(this);
  }

  private void dummyAction() {
    System.out.println("Hello world!");
    changeScreen(ScreenType.TITLE_SCREEN);
  }

  private void changeScreen(ScreenType screen) {
    switch (screen) {
      case TITLE_SCREEN -> model.setCurrentScreen(titleScreenController.getView());
      case CREATION_SCREEN -> model.setCurrentScreen(new VBox());
      case SETTINGS_SCREEN -> model.setCurrentScreen(new Region());
      case GAME_SCREEN -> model.setCurrentScreen(new HBox());
    }
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    if (event instanceof ScreenChangeEvent sce) {
      handleScreenChangeEvent(sce);
    } else if (event instanceof DataUpdateEvent due) {
      handleDataUpdateEvent(due);
    }
  }

  private void handleScreenChangeEvent(ScreenChangeEvent event) {
    if (event.getSource() == titleScreenController) {
      String identifier = event.getIdentifier();
      if (identifier.equals("start")) {
        changeScreen(ScreenType.CREATION_SCREEN);
      } else if (identifier.equals("settings")) {
        changeScreen(ScreenType.SETTINGS_SCREEN);
      }
    }
  }

  private void handleDataUpdateEvent(DataUpdateEvent event) {
    String key = event.getKey();

    if (key.equals("storyTitle")) {
      System.out.println(event.getValue());
    }
  }

  public Region getView() {
    return view;
  }
}
