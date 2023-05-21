package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.ApplicationModel;
import edu.ntnu.idatt2001.model.gui.ScreenType;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

import java.io.File;

public class ApplicationScreenBuilder implements Builder<Region> {
  private final ApplicationModel gameModel;
  private final Region titleView;
  private final Region gameView;
  private final Region characterView;
  private final Region settingsView;
  private final Region menubarView;

  public ApplicationScreenBuilder(ApplicationModel gameModel,
                                  Region titleView,
                                  Region gameView,
                                  Region characterView,
                                  Region settingsView,
                                  Region menubarView) {
    this.gameModel = gameModel;
    this.titleView = titleView;
    this.gameView = gameView;
    this.characterView = characterView;
    this.settingsView = settingsView;
    this.menubarView = menubarView;
  }

  public Region build() {
    BorderPane results = new BorderPane();

    results.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        //settingsAction.run();
      } else if (event.getCode() == KeyCode.F1) {
        //helpAction.run();
      }
    });

    results.getStylesheets().add("application.css");
    results.getStyleClass().add("app-pane");
    results.setTop(menubarView);

    results.setBottom(Widgets.createLabel("All Rights Reserved ©","bottom-text"));
    results.setCenter(changeScreen(gameModel.getCurrentScreen()));
    results.setAlignment(results.getTop(), Pos.TOP_RIGHT);
    results.setCenter(changeScreen(ScreenType.TITLE_SCREEN));
    gameModel.currentScreenProperty()
      .addListener((observable, oldValue, newValue) -> results.setCenter(changeScreen(newValue)));

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

  public Region changeScreen(ScreenType screen) {
    return switch (screen) {
      case TITLE_SCREEN -> titleView;
      case CREATION_SCREEN -> characterView;
      case SETTINGS_SCREEN -> settingsView;
      case PASSAGE_SCREEN -> gameView;
    };
  }
}