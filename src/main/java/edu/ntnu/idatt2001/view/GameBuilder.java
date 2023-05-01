package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.GameModel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class GameBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final GameModel gameModel;

  public GameBuilder(Runnable settingsAction, GameModel gameModel) {
    this.settingsAction = settingsAction;
    this.gameModel = gameModel;
  }

  private Node createMenuBar(Runnable action) {
    HBox results = new HBox(createButton(action, "Settings"));
    results.alignmentProperty().set(Pos.TOP_RIGHT);
    return results;
  }

  private Node createButton(Runnable action, String label) {
    Button results = new Button(label);
    results.setOnAction(event -> action.run());
    return results;
  }

  public Region build() {
    BorderPane results = new BorderPane();
    results.setTop(createMenuBar(settingsAction));
    results.setBottom(new Label("All Rights Reserved."));
    results.setCenter(gameModel.getCurrentScreen());
    gameModel.currentScreenProperty()
        .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }
}