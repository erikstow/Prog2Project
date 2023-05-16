package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterDifficultyScreenBuilder implements Builder<Region> {
  
  private final CharacterScreenModel model;

  public CharacterDifficultyScreenBuilder(CharacterScreenModel model) {
    this.model = model;
  }
  
  @Override
  public Region build() {
    VBox results = new VBox();
    results.getChildren().addAll(createDifficultySection(), createStatsBox());
    return results;
  }

  private Node createDifficultySection() {
    VBox results = new VBox();
    Label difficulty = new Label("Choose difficulty:");
    Node difficultyButtons = Widgets.createButtonBar("", 
        Widgets.createButton("Easy", () -> model.setDifficulty(1), ""), 
        Widgets.createButton("Medium", () -> model.setDifficulty(2), ""), 
        Widgets.createButton("Hard", () -> model.setDifficulty(3), ""));
    results.getChildren().addAll(difficulty, difficultyButtons);
    return results;
  }

  private Node createStatsBox() {
    VBox results = new VBox(Widgets.createLabel("Your stats", ""));
    HBox stats = new HBox(createStatsPomptLabelBox(), createStatsValueLabelBox());
    results.getChildren().addAll(stats);
    return results;
  }

  private Node createStatsPomptLabelBox() {
    VBox results = new VBox();
    Label healthPrompt = Widgets.createLabel("health", "");
    Label goldPrompt = Widgets.createLabel("gold", "");
    Label scorePrompt = Widgets.createLabel("score", "");
    Label inventoryPrompt = Widgets.createLabel("inventory", "");
    results.getChildren().addAll(healthPrompt, goldPrompt, scorePrompt, inventoryPrompt);
    return results;
  }

  private Node createStatsValueLabelBox() {
    VBox results = new VBox();
    Label healthValue = Widgets.createLabel("", "");
    model.health().addListener(
        (observable, oldValue, newValue) -> healthValue.setText(newValue.toString()));
    Label goldValue = Widgets.createLabel("", "");
    model.gold().addListener(
        (observable, oldValue, newValue) -> goldValue.setText(newValue.toString()));
    Label scoreValue = Widgets.createLabel("", "");
    model.score().addListener(
        (observable, oldValue, newValue) -> scoreValue.setText(newValue.toString()));
    Label inventoryValue = Widgets.createLabel("", "");
    model.inventory().addListener(
        (observable, oldValue, newValue) -> inventoryValue.setText(newValue.toString()));
    results.getChildren().addAll(healthValue, goldValue, scoreValue, inventoryValue);
    return results;
  }
}
