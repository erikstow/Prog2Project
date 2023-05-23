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

/**
 * CharacterDifficultyScreenBuilder is a utility class used to
 * build a UI screen related to character difficulty
 * in a game. It implements the Builder interface with Region as its type parameter.
 * The class provides methods for creating sections of the screen
 * related to difficulty selection and character stats,
 * as well as the utility for creating difficulty selection buttons.
 */
public class CharacterDifficultyScreenBuilder implements Builder<Region> {
  private final CharacterScreenState state;

  /**
   * Constructs a new CharacterDifficultyScreenBuilder using the provided CharacterScreenState.
   *
   * @param state the current state of the character screen.
   */
  public CharacterDifficultyScreenBuilder(CharacterScreenState state) {
    this.state = state;
  }

  /**
   * Builds and returns a Region that represents the difficulty screen. The Region consists
   * of a VBox with the difficulty section and the stats box.
   *
   * @return the built Region representing the difficulty screen.
   */
  @Override
  public Region build() {
    VBox results = new VBox(createDifficultySection(), createStatsBox());
    results.setAlignment(Pos.CENTER);
    results.getStylesheets().add("difficultysection.css");
    return results;
  }

  /**
   * Creates and returns a Node representing the difficulty selection section of the screen.
   *
   * @return the Node representing the difficulty selection section.
   */
  private Node createDifficultySection() {
    return new HBox(
        Widgets.createLabel("Choose difficulty:", "choose-difficulty-label"),
        createDifficultyButton("Easy", 1, state, "difficulty-button-easy"),
        createDifficultyButton("Medium", 2, state, "difficulty-button-medium"),
        createDifficultyButton("Hard", 3, state, "difficulty-button-hard"));
  }

  /**
   * Creates and returns a Node representing the stats box section of the screen.
   *
   * @return the Node representing the stats box section.
   */
  private Node createStatsBox() {
    HBox stats = new HBox(createStatsLabelBox("Health", "Gold", "Score", "Inventory"),
        createStatsValueLabelBox(state.health(), state.gold(), state.score(), state.inventory()));
    return new VBox(Widgets.createLabel("Your stats", "your-stats-label"), stats);
  }


  /**
   * Creates and returns a Node representing a VBox of labels for stats.
   *
   * @param prompts The prompts for the stats.
   * @return the Node representing the stats labels.
   */

  private Node createStatsLabelBox(String... prompts) {
    VBox results = new VBox();
    for (String prompt : prompts) {
      results.getChildren().add(Widgets.createLabel(prompt + ": ", "stats-label"));
    }
    return results;
  }

  /**
   * Creates and returns a Node representing a VBox of labels for stats values.
   *
   * @param properties The properties to bind to the labels.
   * @return the Node representing the stats values.
   */
  private Node createStatsValueLabelBox(Property... properties) {
    VBox results = new VBox();
    for (Property property : properties) {
      Label label = Widgets.createLabelWithBinding(property, "stats-value");
      label.getStyleClass().add("stats-value");
      results.getChildren().add(label);
    }
    return results;
  }

  /**
   * Creates and returns a Button for setting the difficulty level.
   *
   * @param label The text of the button.
   * @param difficulty The difficulty level associated with the button.
   * @param model The CharacterScreenState to be updated when the button is clicked.
   * @param styleClass The style class to apply to the Button.
   * @return the created Button.
   */
  public static Button createDifficultyButton(String label,
                                              int difficulty,
                                              CharacterScreenState model,
                                              String styleClass) {
    return Widgets.createButton(label, () -> model.setDifficulty(difficulty), styleClass);
  }
}
