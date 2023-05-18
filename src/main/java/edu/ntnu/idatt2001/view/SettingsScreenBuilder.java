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
    Button restartButton = Widgets.createButton("Restart game", restart, "");
    restartButton.disableProperty().bind(model.restartAllowedProperty().not());

    return new VBox(
      Widgets.createButton("Resume game", resume, ""),
      Widgets.createButton("Return to title screen", title, ""),
      Widgets.createButton("Exit game", exit, ""),
      restartButton
    );
  }
}
