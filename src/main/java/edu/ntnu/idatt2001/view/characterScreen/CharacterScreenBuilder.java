package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.model.screentype.CharacterScreenType;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CharacterScreenBuilder implements Builder<Region> {
  private final CharacterScreenState model;
  private final Runnable backAction;
  private final Runnable nextAction;
  private final Region infoView;
  private final Region difficultyView;
  private final Region goalsView;
  private final Region summaryView;

  public CharacterScreenBuilder(
    CharacterScreenState model, Runnable backAction, Runnable nextAction, Region infoView,
    Region difficultyView, Region goalsView, Region summaryView) {
    this.difficultyView = difficultyView;
    this.summaryView = summaryView;
    this.infoView = infoView;
    this.goalsView = goalsView;

    this.model = model;
    this.backAction = backAction;
    this.nextAction = nextAction;
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();

    Label title = Widgets.createLabel("Welcome to Paths", "label-title");
    results.setTop(title);

    Node buttonBarNode = createNextBackButtonBar();
    results.setBottom(buttonBarNode);

    model.currentScreen().addListener((observable, oldValue, newValue) ->
        results.setCenter(getScreen(newValue)));

    results.setCenter(getScreen(model.getCurrentScreen()));

    results.getStylesheets().add("characterscreen.css"); // Add this line to link the CSS file
    results.setAlignment(title, Pos.CENTER);
    results.setAlignment(buttonBarNode, Pos.CENTER);
    results.setAlignment(getScreen(model.getCurrentScreen()), Pos.CENTER);

    return results;
  }

  private Node createNextBackButtonBar() {
    HBox results = new HBox();
    results.setAlignment(Pos.CENTER);
    results.getStyleClass().add("character-screen-button-hbox");
    Button next = Widgets.createButton("Next", nextAction, "character-screen-button");
    next.disableProperty().bind(model.nextAllowed().not());
    Button back = Widgets.createButton("Title", backAction, "character-screen-button");
    model.currentScreen().addListener((observable, oldValue, newValue) -> {
      switch (newValue) {
        case INFO_SCREEN -> {
          back.setText("Title");
          next.setText("Next");
        }
        case SUMMARY_SCREEN -> next.setText("Start!");
        default -> {
          next.setText("Next");
          back.setText("Back");
        }
      }
    });
    results.getChildren().addAll(back, next);
    return results;
  }

  private Region getScreen(CharacterScreenType screenType) {
    return switch (screenType) {
      case INFO_SCREEN -> infoView;
      case DIFFICULTY_SCREEN -> difficultyView;
      case GOALS_SCREEN -> goalsView;
      case SUMMARY_SCREEN -> summaryView;
    };
  }
}