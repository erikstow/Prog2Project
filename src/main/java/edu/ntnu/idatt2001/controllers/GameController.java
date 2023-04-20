package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.GameModel;
import edu.ntnu.idatt2001.views.GameViewBuilder;
import javafx.scene.layout.Region;

public class GameController {
  private final Region view;
  private final GameModel model;
  private final TitleScreenController titleScreenController;

  public GameController() {
    model = new GameModel();
    this.titleScreenController = new TitleScreenController(this::dummyAction, model);
    view = new GameViewBuilder(this::dummyAction, titleScreenController.getView()).build();
  }

  private void dummyAction() {
    System.out.println("Hello world!");
    System.out.println(model.getStoryName());
  }

  public Region getView() {
    return view;
  }
}
