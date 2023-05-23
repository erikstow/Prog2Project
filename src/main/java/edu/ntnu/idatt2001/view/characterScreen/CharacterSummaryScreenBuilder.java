package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

/**
 * This class provides a builder for the character summary screen in the game.
 * The screen provides an overview of the character's current stats, goals, and other attributes.
 */
public class CharacterSummaryScreenBuilder implements Builder<Region> {
  private final CharacterScreenState state;

  /**
   * Constructor for CharacterSummaryScreenBuilder.
   *
   * @param state the CharacterScreenState model containing the current character state
   */
  public CharacterSummaryScreenBuilder(CharacterScreenState state) {
    this.state = state;
  }

  /**
   * Builds the character summary screen as a Region.
   * Sets up the layout and bindings for all UI elements on the screen.
   *
   * @return a Region containing the character summary screen
   */
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

  /**
   * Creates a box with the player's name and appearance.
   *
   * @return a Node representing the player info box
   */
  private Node createPlayerInfoBox() {
    HBox results = new HBox();
    results.getStyleClass().add("hbox");
    Label name = Widgets.createLabel("Name", "info-label");
    name.textProperty().bindBidirectional(state.name());
    Label appearance = Widgets.createLabel("Appearance", "info-label");
    appearance.textProperty().bindBidirectional(state.appearence());
    results.getChildren().addAll(appearance, name);
    return results;
  }

  /**
   * Creates a box displaying the game's difficulty level.
   *
   * @return a Node representing the difficulty box
   */
  private Node createDifficultyBox() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    Label difficulty = Widgets.createLabel("", "info-label");
    state.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        difficulty.setText("Difficulty: " + getDifficultyString());
      }
    });
    results.getChildren().addAll(difficulty, createStatsBox());
    return results;
  }


  /**
   * Creates a box displaying the player's current stats.
   *
   * @return a Node representing the stats box
   */
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
        Widgets.createLabelWithBinding(state.health(), "stats-value"),
        Widgets.createLabelWithBinding(state.gold(), "stats-value"),
        Widgets.createLabelWithBinding(state.score(), "stats-value"),
        Widgets.createLabelWithBinding(state.inventory(), "stats-value")
    );
    results.getChildren().addAll(statsTypeLabel, statsValueLabel);
    return results;
  }

  /**
   * Converts the model's difficulty level to a String.
   *
   * @return the String representation of the difficulty level
   */
  private String getDifficultyString() {
    return switch (state.getDifficulty()) {
      case 1 -> "Easy";
      case 2 -> "Medium";
      case 3 -> "Hard";
      default -> "";
    };
  }

  /**
   * Creates a box displaying the player's current goals.
   *
   * @return a Node representing the goals box
   */
  private Node createGoalsBox() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    Label goals = Widgets.createLabel("Goals: ", "info-label");
    ListView<Goal> goalListSummary = new ListView<>();
    goalListSummary.setItems(state.goals());
    goalListSummary.getStyleClass().add("goals-list-view");
    results.getChildren().addAll(goals, goalListSummary);
    return results;
  }
}
