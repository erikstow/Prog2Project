package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.beans.property.Property;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterDifficultyScreenBuilder implements Builder<Region> {
  private final CharacterScreenState model;

  public CharacterDifficultyScreenBuilder(CharacterScreenState model) {
    this.model = model;
  }

  @Override
  public Region build() {
    VBox results = new VBox(createDifficultySection(), createStatsBox());
    results.setAlignment(Pos.CENTER);
    results.getStylesheets().add("difficultysection.css");
    return results;
  }

  private Node createDifficultySection() {
    return new HBox(
        Widgets.createLabel("Choose difficulty:", "choose-difficulty-label"),
        createDifficultyButton("Easy", 1, model, "difficulty-button-easy"),
        createDifficultyButton("Medium", 2, model, "difficulty-button-medium"),
        createDifficultyButton("Hard", 3, model, "difficulty-button-hard"));
  }

  private Node createStatsBox() {
    HBox stats = new HBox(createStatsLabelBox("Health", "Gold", "Score", "Inventory"),
        createStatsValueLabelBox(model.health(), model.gold(), model.score(), model.inventory()));
    return new VBox(Widgets.createLabel("Your stats", "your-stats-label"), stats);
  }

  private Node createStatsLabelBox(String... prompts) {
    VBox results = new VBox();
    for (String prompt : prompts) {
      results.getChildren().add(Widgets.createLabel(prompt + ": ", "stats-label"));
    }
    return results;
  }

  private Node createStatsValueLabelBox(Property... properties) {
    VBox results = new VBox();
    for (Property property : properties) {
      Label label = Widgets.createLabelWithBinding(property, "stats-value");
      label.getStyleClass().add("stats-value");
      results.getChildren().add(label);
    }
    return results;
  }

  public static Button createDifficultyButton(String label,
                                              int difficulty,
                                              CharacterScreenState model,
                                              String styleClass) {
    return Widgets.createButton(label, () -> model.setDifficulty(difficulty), styleClass);
  }
}
