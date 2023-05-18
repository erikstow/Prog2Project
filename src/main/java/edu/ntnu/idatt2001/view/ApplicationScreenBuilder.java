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
    results.getStylesheets().add("application.css");
    results.getStyleClass().add("app-pane");
    results.setTop(Widgets.createButtonBar("buttonbar-top", Widgets.createButton("", settingsAction, "top-button")));
    results.setBottom(Widgets.createLabel("All Rights Reserved ©","bottom-text"));
    results.setCenter(gameModel.getCurrentScreen());
    results.setAlignment(results.getTop(), Pos.TOP_RIGHT);
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }
}