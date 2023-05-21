package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.gui.SettingsModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class SettingsScreenBuilder implements Builder<Region> {
  private final SettingsModel model;
  private final Runnable resume;
  private final Runnable title;
  private final Runnable restart;
  private final Runnable exit;

  public SettingsScreenBuilder(SettingsModel model, Runnable resume, Runnable title, Runnable restart, Runnable exit) {
    this.model = model;
    this.resume = resume;
    this.title = title;
    this.restart = restart;
    this.exit = exit;
  }

  public Region build() {
    Button restartButton = Widgets.createButton("Restart", restart, "");
    restartButton.disableProperty().bind(model.restartAllowedProperty().not());
    VBox results = new VBox(
      Widgets.createLabel("Options", "settings-title"),
      Widgets.createButton("Resume", resume, ""),
      Widgets.createButton("Title Screen", title, ""),
      restartButton, Widgets.createButton("Exit", exit, "")
    );
    results.getStylesheets().add("settings.css");
    results.getStyleClass().add("settings-box");
    return results;
  }
}
