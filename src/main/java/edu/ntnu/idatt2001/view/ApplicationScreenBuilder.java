package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ApplicationScreenBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final ApplicationModel gameModel;

  public ApplicationScreenBuilder(Runnable settingsAction, ApplicationModel gameModel) {
    this.settingsAction = settingsAction;
    this.gameModel = gameModel;
  }

  public Region build() {
    BorderPane results = new BorderPane();
    results.setTop(Widgets.createButtonBar("", Widgets.createButton("Settings", settingsAction, "")));
    results.setBottom(new Label("All Rights Reserved."));
    results.setCenter(gameModel.getCurrentScreen());
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }
}