package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterGoalsScreenBuilder implements Builder<Region> {
  
  private final CharacterScreenModel model;

  public CharacterGoalsScreenBuilder(CharacterScreenModel model) {
    this.model = model;
  }

  @Override
  public Region build() {
    VBox results = new VBox();
    Label goals = Widgets.createLabel("Define your goals", "");
    results.getChildren().addAll(goals, createGoalBox());
    return results;
  }

  private Node createGoalBox() {
    HBox results = new HBox();
    VBox goalBox = new VBox();
    VBox textfieldBox = new VBox();
    
    Label healthPrompt = Widgets.createLabel("Health:", "");
    TextField healthField = Widgets.createTextField("Health", model.health(), "");
    Label goldPrompt = Widgets.createLabel("Gold:", "");
    TextField goldField = Widgets.createTextField("Gold", model.gold(), "");
    Label scorePrompt = Widgets.createLabel("Score:", "");
    TextField scoreField = Widgets.createTextField("Score", model.score(), "");
    Label inventoryPrompt = Widgets.createLabel("Inventory:", "");
    TextField inventoryField = Widgets.createTextField("Inventory", model.inventory(), "");
    goalBox.getChildren().addAll(healthPrompt, goldPrompt, scorePrompt, inventoryPrompt);
    textfieldBox.getChildren().addAll(healthField, goldField, scoreField, inventoryField);
    results.getChildren().addAll(goalBox, textfieldBox);
    return results;
  }
}
