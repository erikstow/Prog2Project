package edu.ntnu.idatt2001.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameView extends BorderPane {

  public GameView(Runnable settingsAction) {
    setTop(createMenuBar(settingsAction));
    setBottom(createLabel("All Rights Reserved."));
  }

  private HBox createMenuBar(Runnable action) {
    HBox results = new HBox();
    results.getChildren().add(createButton(action, "Settings"));
    results.alignmentProperty().set(Pos.TOP_RIGHT);
   return results;
  }

  private Button createButton(Runnable action, String label) {
    Button results = new Button(label);
    results.setOnAction(event -> action.run());
    return results;
  }

  private Label createLabel(String text) {
    return new Label(text);
  }
}


