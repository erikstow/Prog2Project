package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

import java.io.File;

public class ApplicationScreenBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final Runnable helpAction;
  private final Runnable musicAction;
  private final ApplicationModel gameModel;

  public ApplicationScreenBuilder(ApplicationModel gameModel, Runnable settingsAction, Runnable helpAction, Runnable musicAction) {
    this.settingsAction = settingsAction;
    this.helpAction = helpAction;
    this.musicAction = musicAction;
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
    results.setTop(new HBox(
        Widgets.createButton("", settingsAction, "button-settings"), 
        Widgets.createButton("", helpAction, "button-help"),
        createToggleButton("", musicAction, "button-music")
        ));

    results.setBottom(Widgets.createLabel("All Rights Reserved ©","bottom-text"));
    results.setCenter(gameModel.getCurrentScreen());
    results.setAlignment(results.getTop(), Pos.TOP_RIGHT);
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(newValue));

    return results;
  }

  private Node createToggleButton(String text, Runnable action, String styleClass) {
    ToggleButton button = new ToggleButton(text);
    button.getStyleClass().add(styleClass);
    button.setOnAction(event -> {
      Media clickSound = new Media(new File("src/main/resources/sound/click.mp3").toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
      mediaPlayer.seek(Duration.ZERO);
      mediaPlayer.play();
      action.run();
    });
    return button;
  }
}