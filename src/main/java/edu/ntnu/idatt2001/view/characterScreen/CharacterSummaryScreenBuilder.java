package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterSummaryScreenBuilder implements Builder<Region> {
  private final CharacterScreenModel model;
  public CharacterSummaryScreenBuilder(CharacterScreenModel model) {
    this.model = model;
  }

  @Override
  public Region build() {
    VBox results = new VBox();
    HBox hbox = new HBox();
    hbox.getStyleClass().add("hbox");
    results.getStylesheets().add("summaryscreen.css");
    hbox.getChildren().addAll(
      createDifficultyBox(),
      createGoalsBox()
    );
    results.getChildren().addAll(
      createPlayerInfoBox(),
      hbox
    );
    return results;
  }

  private Node createPlayerInfoBox() {
    HBox results = new HBox();
    results.getStyleClass().add("hbox");
    Label name = Widgets.createLabel("Name", "info-label");
    name.textProperty().bindBidirectional(model.name());
    Label appearance = Widgets.createLabel("Appearance", "info-label");
    appearance.textProperty().bindBidirectional(model.appearence());
    results.getChildren().addAll(appearance, name);
    return results;
  }

  private Node createDifficultyBox() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    Label difficulty = Widgets.createLabel("", "info-label");
    model.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        difficulty.setText("Difficulty: " + getDifficultyString());
      }
    });
    results.getChildren().addAll(difficulty, createStatsBox());
    return results;
  }

  private Node createStatsBox() {
    HBox results = new HBox();
    results.getStyleClass().add("hbox");
    VBox statsTypeLabel = new VBox();
    statsTypeLabel.getStyleClass().add("stats-label-box");
    statsTypeLabel.getChildren().addAll(
      Widgets.createLabel("Health: ", "stats-label"),
      Widgets.createLabel("Gold: ", "stats-label"),
      Widgets.createLabel("Score: ", "stats-label"),
      Widgets.createLabel("Inventory: ", "stats-label")
    );
    VBox statsValueLabel = new VBox();
    statsValueLabel.getStyleClass().add("stats-label-box");
    statsValueLabel.getChildren().addAll(
      Widgets.createLabelWithBinding(model.health(), "stats-value"),
      Widgets.createLabelWithBinding(model.gold(), "stats-value"),
      Widgets.createLabelWithBinding(model.score(), "stats-value"),
      Widgets.createLabelWithBinding(model.inventory(), "stats-value")
    );
    results.getChildren().addAll(statsTypeLabel, statsValueLabel);
    return results;
  }

  private String getDifficultyString() {
    return switch (model.getDifficulty()) {
      case 1 -> "Easy";
      case 2 -> "Medium";
      case 3 -> "Hard";
      default -> "";
    };
  }

  private Node createGoalsBox() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    Label goals = Widgets.createLabel("Goals: ", "info-label");
    ListView<Goal> goalListSummary = new ListView<>();
    goalListSummary.setItems(model.goals());
    goalListSummary.getStyleClass().add("goals-list-view");
    results.getChildren().addAll(goals, goalListSummary);
    return results;
  }
}
