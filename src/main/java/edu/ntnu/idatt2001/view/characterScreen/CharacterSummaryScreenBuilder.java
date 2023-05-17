package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    TabPane results = new TabPane();
    Tab infoTab = new Tab("Player Info", createPlayerInfoBox());
    Tab difficultyTab = new Tab("Difficulty", createDifficultyBox());
    Tab goalsTab = new Tab("Goals", createGoalsBox());
    infoTab.setClosable(false);
    difficultyTab.setClosable(false);
    goalsTab.setClosable(false);
    results.getTabs().addAll(infoTab, difficultyTab, goalsTab);
    return results;
  }

  private Node createPlayerInfoBox() {
    VBox results = new VBox();
    Label name = Widgets.createLabel("Name", "");
    name.textProperty().bind(model.name());
    Label appearence = Widgets.createLabel("Appearence", "");
    appearence.textProperty().bind(model.appearence());
    results.getChildren().addAll(name, appearence);
    return results;
  }

  private Node createDifficultyBox() {
    VBox results = new VBox();
    Label difficulty = Widgets.createLabel("", "");
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
    VBox statsTypeLabel = new VBox();
    statsTypeLabel.getChildren().addAll(
        Widgets.createLabel("Health: ", ""),
        Widgets.createLabel("Gold: ", ""),
        Widgets.createLabel("Score: ", ""),
        Widgets.createLabel("Inventory: ", ""));
    VBox statsValueLabel = new VBox();
    statsValueLabel.getChildren().addAll(
        Widgets.createLabelWithBinding(model.health()),
        Widgets.createLabelWithBinding(model.gold()),
        Widgets.createLabelWithBinding(model.score()),
        Widgets.createLabelWithBinding(model.inventory()));
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
    Label goals = Widgets.createLabel("Goals: ", "");
    ListView<Goal> goalListSummary = new ListView<>();
    goalListSummary.setItems(model.goals());
    results.getChildren().addAll(goals, goalListSummary);
    return results;
  }
}
