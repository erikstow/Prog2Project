package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

import java.io.File;

public class MenubarScreenBuilder implements Builder<Region> {
  private final Runnable settingsAction;
  private final Runnable helpAction;
  private final Runnable musicAction;
  public MenubarScreenBuilder(Runnable settingsAction, Runnable helpAction, Runnable musicAction) {
    this.helpAction = helpAction;
    this.musicAction = musicAction;
    this.settingsAction = settingsAction;

  }

  public Region build() {
    return new HBox(
      Widgets.createButton("", settingsAction, "button-settings"),
      Widgets.createButton("", helpAction, "button-help"),
      createToggleButton("", musicAction, "button-music")
    );
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
