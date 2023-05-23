package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.state.SettingsState;
import edu.ntnu.idatt2001.util.Widgets;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

/**
 * This class builds a settings screen for the application.
 * The settings screen contains buttons to resume,
 * restart, return to the title screen, and exit the game.
 */
public class SettingsScreenBuilder implements Builder<Region> {
  private final SettingsState state;
  private final Consumer<String> buttonAction;

  /**
   * Constructor for SettingsScreenBuilder.
   *
   * @param state the SettingsState model that contains the current state of settings
   * @param buttonAction a Consumer accepting a String
   *                     representing the action to be performed on button press
   */
  public SettingsScreenBuilder(SettingsState state, Consumer<String> buttonAction) {
    this.state = state;
    this.buttonAction = buttonAction;
  }

  /**
   * Generates a Runnable for a specific command that can be used as an action for a button.
   *
   * @param command a String that represents the command to be executed
   * @return a Runnable that encapsulates the command to be executed
   */
  private Runnable action(String command) {
    return () -> buttonAction.accept(command);
  }

  /**
   * Builds and returns a Region representing the settings screen.
   * The screen includes buttons for resuming, restarting,
   * returning to the title screen, and exiting the game.
   *
   * @return a Region containing the built settings screen
   */
  public Region build() {
    Button restartButton = Widgets.createButton("Restart game", action("restartGame"), "");
    restartButton.disableProperty().bind(state.restartAllowedProperty().not());

    VBox results = new VBox(
        Widgets.createLabel("Options", "settings-title"),
        Widgets.createButton("Resume game", action("resumeGame"), ""),
        Widgets.createButton("Return to title screen", action("returnToTitle"), ""),
        Widgets.createButton("Exit game", action("exitGame"), ""),
        restartButton
    );
    results.getStylesheets().add("settings.css");
    results.getStyleClass().add("settings-box");
    return results;
  }
}
