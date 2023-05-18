package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
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

    results.setTop(Widgets.createButtonBar("",
        Widgets.createButton("Settings", settingsAction, ""),
        Widgets.createButton("Help", helpAction, ""))
      );
    results.setBottom(new Label("All Rights Reserved."));
    results.setCenter(gameModel.getCurrentScreen());
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }
}