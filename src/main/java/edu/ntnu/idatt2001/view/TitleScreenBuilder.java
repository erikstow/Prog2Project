package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.state.TitleScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

/**
 * This class is responsible for building the title screen of the game.
 * The title screen includes the title and subtitle of the game,
 * a dropdown to choose the story, a start button, and information about the game state.
 */
public class TitleScreenBuilder implements Builder<Region> {
  private final TitleScreenState state;
  private final Runnable actionHandler;
  private final List<String> storyTitles;

  /**
   * Constructor for TitleScreenBuilder.
   *
   * @param state the TitleScreenState model that contains the current state of the title screen
   * @param storyTitles a list of story titles
   * @param action a Runnable to handle actions
   */
  public TitleScreenBuilder(TitleScreenState state, List<String> storyTitles, Runnable action) {
    this.state = state;
    this.actionHandler = action;
    this.storyTitles = storyTitles;
  }

  /**
   * Builds and returns a Region representing the title screen.
   * The screen includes the title and subtitle of the game,
   * a dropdown to choose the story, a start button, and information about the game state.
   *
   * @return a Region containing the built title screen
   */
  @Override
  public Region build() {
    VBox results = new VBox();
    results.getStylesheets().add("title.css");
    results.getStyleClass().add("title-pane");
    Label title = Widgets.createLabel("Paths", "title-label");
    Label subtitle = Widgets.createLabel("Hawk & Dust", "subtitle-label");
    Node storySelect = createComboBox("Choose Story", storyTitles);
    storySelect.getStyleClass().add("story-select");

    Button startButton = Widgets.createButton("START", actionHandler, "start-button");
    startButton.disableProperty().bind(state.startAllowedPorperty().not());

    Node info = createInfoBox();
    info.getStyleClass().add("info-box");
    results.getChildren().addAll(title, subtitle, storySelect, startButton, info);
    return results;
  }

  /**
   * Creates and returns a ComboBox Node allowing the user to select a story.
   *
   * @param prompt the text to be displayed in the ComboBox when no option is selected
   * @param options a list of story titles to be displayed as options in the ComboBox
   * @return a Node containing the created ComboBox
   */
  private Node createComboBox(String prompt, List<String> options) {
    ComboBox<String> results = new ComboBox<>();
    results.getStyleClass().add("story-select");
    results.setPromptText(prompt);
    results.getItems().addAll(options);
    state.storyNameProperty().bindBidirectional(results.valueProperty());

    return results;
  }


  /**
   * Creates and returns an InfoBox Node displaying information about the game state.
   *
   * @return a Node containing the created InfoBox
   */
  private Node createInfoBox() {
    VBox results = new VBox();
    Label filePathPrompt = new Label("File Path:");
    filePathPrompt.getStyleClass().add("file-path-label");
    Label filePathLabel = new Label();
    filePathLabel.textProperty().bind(state.filePathProperty());
    filePathLabel.getStyleClass().add("file-path-label");
    
    Label brokenLinksPrompt = new Label("Broken Links:");
    brokenLinksPrompt.getStyleClass().add("broken-links-label");
    VBox brokenLinksBox = new VBox();
    brokenLinksBox.getStyleClass().add("broken-links-box");
    state.brokenLinksProperty().addListener((observable, oldValue, newValue) -> {
      brokenLinksBox.getChildren().clear();
      brokenLinksBox.getChildren().add(formatBrokenLinksBox());
    });
  
    results.getChildren().addAll(filePathPrompt, filePathLabel, brokenLinksPrompt, brokenLinksBox);
    VBox.setMargin(brokenLinksBox, new Insets(20, 0, 0, 0));
    return results;
  }


  /**
   * Formats and returns a VBox Node containing information about broken links in the game.
   *
   * @return a Node containing the formatted VBox with broken link information
   */
  private Node formatBrokenLinksBox() {
    VBox results = new VBox();
    for (Link link : state.getBrokenLinks()) {
      Label label = new Label(link.getAsString());
      label.getStyleClass().add("broken-links-label");
      results.getChildren().add(label);

    }
    return results;
  }
}
