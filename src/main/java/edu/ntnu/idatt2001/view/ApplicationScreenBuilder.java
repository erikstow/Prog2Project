package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ApplicationScreenBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final Runnable helpAction;
  private final ApplicationModel gameModel;

  public ApplicationScreenBuilder(ApplicationModel gameModel, Runnable settingsAction, Runnable helpAction) {
    this.settingsAction = settingsAction;
    this.helpAction = helpAction;
    this.gameModel = gameModel;
  }

  public Region build() {
    BorderPane results = new BorderPane();

    results.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        settingsAction.run();
      } else if (event.getCode() == KeyCode.F1) {
        helpAction.run();
      }
    });

    results.getStylesheets().add("application.css");
    results.getStyleClass().add("app-pane");
    results.setTop(Widgets.createButtonBar("buttonbar-top",
        Widgets.createButton("", settingsAction, "top-button"),
        Widgets.createButton("Help", helpAction, ""))
      );
    results.setBottom(Widgets.createLabel("All Rights Reserved ©","bottom-text"));
    results.setCenter(gameModel.getCurrentScreen());
    results.setAlignment(results.getTop(), Pos.TOP_RIGHT);
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }
}