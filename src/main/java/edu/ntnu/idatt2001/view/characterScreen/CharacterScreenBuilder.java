package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Pos;
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
    Label title = Widgets.createLabel("Welcome to Paths", "label-title"); // TODO: Get story name from file
    Node buttonBarNode = createNextBackButtonBar();
    model.currentScreen().addListener((observable, oldValue, newValue) -> {
      results.setCenter(newValue);
    });
    results.setTop(title);
    results.setCenter(model.getCurrentScreen());
    results.setBottom(buttonBarNode);

    results.getStylesheets().add("characterscreen.css"); // Add this line to link the CSS file
    results.setAlignment(title, Pos.CENTER);
    results.setAlignment(buttonBarNode, Pos.CENTER);
    results.setAlignment(model.getCurrentScreen(), Pos.CENTER);
    return results;
  }

  private Node createNextBackButtonBar() {
    HBox results = new HBox();
    results.setAlignment(Pos.CENTER);
    results.getStyleClass().add("character-screen-button-bar");
    Button next = Widgets.createButton("Next", nextAction, "character-screen-button");
    next.disableProperty().bind(model.nextAllowed().not());
    Button back = Widgets.createButton("Back", backAction, "character-screen-button");
    results.getChildren().addAll(back, next);
    return results;
  }
}