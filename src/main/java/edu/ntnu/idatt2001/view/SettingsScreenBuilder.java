package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.state.SettingsState;
import edu.ntnu.idatt2001.util.Widgets;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class SettingsScreenBuilder implements Builder<Region> {
  private final SettingsState model;
  private final Consumer<String> buttonAction;

  public SettingsScreenBuilder(SettingsState model, Consumer<String> buttonAction) {
    this.model = model;
    this.buttonAction = buttonAction;
  }

  private Runnable action(String command) {
    return () -> buttonAction.accept(command);
  }

  public Region build() {
    Button restartButton = Widgets.createButton("Restart game", action("restartGame"), "");
    restartButton.disableProperty().bind(model.restartAllowedProperty().not());

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
