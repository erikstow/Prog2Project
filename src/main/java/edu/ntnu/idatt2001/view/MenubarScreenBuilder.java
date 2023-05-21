package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

import java.io.File;
import java.util.function.Consumer;

public class MenubarScreenBuilder implements Builder<Region> {
  private final Consumer<String> buttonAction;
  public MenubarScreenBuilder(Consumer<String> buttonAction) {
    this.buttonAction = buttonAction;
  }

  public Region build() {
    HBox results = new HBox(
      Widgets.createButton("", () -> buttonAction.accept("settings"), "button-settings"),
      Widgets.createButton("", () -> buttonAction.accept("help"), "button-help"),
      createToggleButton("", () -> buttonAction.accept("music"), "button-music")
    );

    results.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        buttonAction.accept("settings");
      } else if (event.getCode() == KeyCode.F1) {
        buttonAction.accept("help");
      }
    });

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
