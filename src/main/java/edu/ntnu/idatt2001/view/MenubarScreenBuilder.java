package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.util.Widgets;
import java.io.File;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;

/**
 * This class builds a menu bar screen with specific controls for the application.
 * These controls include buttons for settings, help, and toggling music.
 */
public class MenubarScreenBuilder implements Builder<Region> {
  private final Consumer<String> buttonAction;

  /**
   * Constructor for MenubarScreenBuilder.
   *
   * @param buttonAction a Consumer accepting a String
   *                     representing the action to be performed on button press
   */
  public MenubarScreenBuilder(Consumer<String> buttonAction) {
    this.buttonAction = buttonAction;
  }

  /**
   * Builds and returns a Region representing the menu bar screen.
   * The screen includes a settings button, help button, and a music toggle button.
   * It also handles key press events for quick access to settings (ESC key) and help (F1 key).
   *
   * @return a Region containing the built menu bar screen
   */
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

  /**
   * Creates and returns a ToggleButton with specific text, action, and style.
   * When the button is clicked, it plays a click sound and runs the provided action.
   *
   * @param text the text to display on the button
   * @param action the action to perform when the button is clicked
   * @param styleClass the style class to apply to the button
   * @return a Node representing the created ToggleButton
   */
  private Node createToggleButton(String text, Runnable action, String styleClass) {
    ToggleButton button = new ToggleButton(text);
    button.getStyleClass().add(styleClass);
    button.setOnAction(event -> {
      String clickFile = new File("src/main/resources/sound/click.mp3").toURI().toString();
      Media clickSound = new Media(clickFile);
      MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
      mediaPlayer.seek(Duration.ZERO);
      mediaPlayer.play();
      action.run();
    });
    return button;
  }
}
