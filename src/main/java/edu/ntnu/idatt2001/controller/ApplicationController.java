package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.events.ErrorEvent;
import edu.ntnu.idatt2001.model.game.*;
import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.view.ApplicationScreenBuilder;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ApplicationController
  extends Controller
  implements ControllerObserver {
  private final Region view;
  private final ApplicationModel model;
  private final TitleScreenController titleScreenController;
  private final GameController gameController;
  private final CharacterScreenController characterScreenController;
  private Game game;
  private Story story;
  private Player startingPlayer;


  public ApplicationController() {
    titleScreenController = new TitleScreenController();
    gameController = new GameController();
    characterScreenController = new CharacterScreenController();
    initObservers();

    model = new ApplicationModel();
    model.setCurrentScreen(titleScreenController.getView());

    view = new ApplicationScreenBuilder(this::dummyAction, model).build();

    // temp
    startingPlayer = new Player.PlayerBuilder("name").build();
  }

  private void initObservers() {
    titleScreenController.addObserver(this);
    gameController.addObserver(this);
    addObserver(gameController);
  }

  private void dummyAction() {
    changeScreen(ScreenType.TITLE_SCREEN);
  }

  private void changeScreen(ScreenType screen) {
    switch (screen) {
      case TITLE_SCREEN -> model.setCurrentScreen(titleScreenController.getView());
      case CREATION_SCREEN -> model.setCurrentScreen(characterScreenController.getView());
      case SETTINGS_SCREEN -> model.setCurrentScreen(new Region());
      case PASSAGE_SCREEN -> model.setCurrentScreen(gameController.getView());
    }
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    if (event instanceof DataUpdateEvent due) {
      handleDataUpdateEvent(due);
    } else if (event instanceof ErrorEvent ee) {
      handleErrorEvent(ee);
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

  private void handleDataUpdateEvent(DataUpdateEvent event) {
    String key = event.getKey();

    switch (key) {
      case "story" -> {
        story = (Story) event.getValue();
        game = new Game(startingPlayer, story, new ArrayList<>());
        update(new DataUpdateEvent(this, "passage", game.begin()), GameController.class::isInstance);
        changeScreen(ScreenType.CREATION_SCREEN);
      }
      case "linkToNextPassage" -> {
        Link link = (Link) event.getValue();
        Passage passage = game.go(link);
        DataUpdateEvent next = new DataUpdateEvent(this, "passage", passage);
        DataUpdateEvent updatedPlayer = new DataUpdateEvent(this, "player", game.getPlayer());
        update(next, GameController.class::isInstance);
        update(updatedPlayer, GameController.class::isInstance);
      }

      default -> throw new IllegalArgumentException("Unknown key: " + key);
    }
  }

  public Region getView() {
    return view;
  }
}
