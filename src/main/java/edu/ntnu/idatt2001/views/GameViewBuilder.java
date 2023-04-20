package edu.ntnu.idatt2001.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class GameViewBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final Region titleView;

  public GameViewBuilder(Runnable settingsAction, Region titleView) {
    this.settingsAction = settingsAction;
    this.titleView = titleView;
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

  public Region build() {
    BorderPane results = new BorderPane();
    results.setTop(createMenuBar(settingsAction));
    results.setBottom(createLabel("All Rights Reserved."));
    results.setCenter(titleView);

    return results;
  }
}


