package edu.ntnu.idatt2001.view.characterScreen;

import java.util.List;

import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    return new VBox(createDifficultySection(), createStatsBox());
  }

  private Node createDifficultySection() {
    Label difficulty = new Label("Choose difficulty:");
    Node difficultyButtons = Widgets.createButtonBar("",
        Widgets.createDifficultyButton("Easy", 1, model),
        Widgets.createDifficultyButton("Medium", 2, model),
        Widgets.createDifficultyButton("Hard", 3, model));
    return new VBox(difficulty, difficultyButtons);
  }

  private Node createStatsBox() {
    HBox stats = new HBox(createStatsLabelBox("Health", "Gold", "Score", "Inventory"),
        createStatsValueLabelBox(model.health(), model.gold(), model.score(), model.inventory()));
    return new VBox(Widgets.createLabel("Your stats", ""), stats);
  }

  private Node createStatsLabelBox(String... prompts) {
    VBox results = new VBox();
    for (String prompt : prompts) {
      results.getChildren().add(Widgets.createLabel(prompt + ": ", ""));
    }
    return results;
  }

  private Node createStatsValueLabelBox(Property... properties) {
    VBox results = new VBox();
    for (Property property : properties) {
      Label label = Widgets.createLabelWithBinding(property);
      results.getChildren().add(label);
    }
    return results;
  }
}
