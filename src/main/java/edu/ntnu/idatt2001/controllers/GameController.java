package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.GameModel;
import edu.ntnu.idatt2001.views.GameView;
import edu.ntnu.idatt2001.views.TitleScreenView;
import javafx.scene.layout.Region;

public class GameController {
  private final GameView view;
  private final GameModel model;

  public GameController() {
    model = new GameModel();
    view = new GameView(this::dummyAction);
  }

  private void dummyAction() {
    System.out.println("Hello world!");

  }

  public Region getView() {
    return view;
  }
}
