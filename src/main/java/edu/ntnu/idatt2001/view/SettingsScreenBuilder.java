package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.SettingsModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.function.Consumer;

public class SettingsScreenBuilder implements Builder<Region> {
  private final SettingsModel model;
  private final Consumer<String> buttonAction;

  public SettingsScreenBuilder(SettingsModel model, Consumer<String> buttonAction) {
    this.model = model;
    this.buttonAction = buttonAction;
  }

  public Region build() {
    Button restartButton = Widgets.createButton("Restart game", () -> buttonAction.accept("restartGame"), "");
    restartButton.disableProperty().bind(model.restartAllowedProperty().not());

    VBox results = new VBox(
      Widgets.createLabel("Options", "settings-title"),
      Widgets.createButton("Resume game", () -> buttonAction.accept("resumeGame"), ""),
      Widgets.createButton("Return to title screen", () -> buttonAction.accept("returnToTitle"), ""),
      Widgets.createButton("Exit game", () -> buttonAction.accept("exitGame"), ""),
      restartButton
    );
    results.getStylesheets().add("settings.css");
    results.getStyleClass().add("settings-box");
    return results;
  }
}
