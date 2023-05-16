package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterScreenBuilder implements Builder<Region> {
  private final CharacterScreenModel model;
  private final Runnable backAction;
  private final Runnable nextAction;

  public CharacterScreenBuilder(
      CharacterScreenModel model, Runnable backAction, Runnable nextAction) {
    
    this.model = model;
    this.backAction = backAction;
    this.nextAction = nextAction;
   
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    Label title = new Label("Welcome to Story Name!"); //TODO: Get story name from file
    results.setTop(title);
    model.currentScreen().addListener((observable, oldValue, newValue) -> {
      results.setCenter(newValue);
    });
    results.setCenter(model.getCurrentScreen());
    results.setBottom(Widgets.createButtonBar(
        "", Widgets.createButton("Back", backAction, ""), 
        Widgets.createButton("Next", nextAction, "")));
    return results;
  }
}