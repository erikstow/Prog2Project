package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.events.ErrorEvent;
import edu.ntnu.idatt2001.model.events.ScreenChangeEvent;
import edu.ntnu.idatt2001.model.gui.GameModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.view.GameBuilder;
import javafx.scene.control.Alert;
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
    titleScreenController = new TitleScreenController();
    titleScreenController.addObserver(this);

    model = new GameModel();
    model.setCurrentScreen(titleScreenController.getView());

    view = new GameBuilder(this::dummyAction, model).build();
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
    String type = event.getClass().getSimpleName();
    switch (type) {
      case "ScreenChangeEvent" -> handleScreenChangeEvent((ScreenChangeEvent) event);
      case "DataUpdateEvent" -> handleDataUpdateEvent((DataUpdateEvent) event);
      case "ErrorEvent" -> handleErrorEvent((ErrorEvent) event);

      default -> {
        ErrorEvent error = new ErrorEvent(this,
            new IllegalArgumentException("Can't handle Event of type :" + type));
        handleErrorEvent(error);
      }
    }
  }

  private void handleErrorEvent(ErrorEvent event) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error of type " + event.getException().getClass().getSimpleName()
        +
        " in " + event.getSource().getClass().getSimpleName());
    alert.setContentText(event.getException().getMessage());

    alert.showAndWait();
  }

  private void handleScreenChangeEvent(ScreenChangeEvent event) {
    String sourceName = event.getSource().getClass().getSimpleName();
    String identifier = event.getIdentifier();

    switch (sourceName) {
      case "TitleScreenController" -> {
        switch (identifier) {
          case "start" -> changeScreen(ScreenType.CREATION_SCREEN);
          default -> handleErrorEvent(new ErrorEvent(this,
              new IllegalArgumentException("Unknown identifier: " + identifier)));
        }
      }
      default -> handleErrorEvent(new ErrorEvent(this,
          new IllegalArgumentException("Unknown sourceName: " + sourceName)));
    }
  }

  private void handleDataUpdateEvent(DataUpdateEvent event) {
    String key = event.getKey();

    switch (key) {
      case "storyTitle" -> System.out.println(event.getValue());
      default -> handleErrorEvent(new ErrorEvent(this,
          new IllegalArgumentException("Unknown key: " + key)));
    }
  }

  public Region getView() {
    return view;
  }
}
