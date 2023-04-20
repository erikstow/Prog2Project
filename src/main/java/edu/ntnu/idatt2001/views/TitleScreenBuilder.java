package edu.ntnu.idatt2001.views;

import edu.ntnu.idatt2001.models.TitleScreenModel;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;


public class TitleScreenBuilder implements Builder<Region> {
  private final TitleScreenModel model;
  private final Runnable actionHandler;
  private final List<String> storyTitles;

  public TitleScreenBuilder(TitleScreenModel model, List<String> storyTitles, Runnable action) {
    this.model = model;
    this.actionHandler = action;
    this.storyTitles = storyTitles;
  }

  @Override
  public Region build() {
    VBox results = new VBox();
    Label title = new Label("Paths of Glory");
    Node storySelect = createComboBox("Choose Story", storyTitles);
    Node startButton = createButton("Start", actionHandler);
    results.getChildren().addAll(title, storySelect, startButton);
    return results;
  }

  private Node createComboBox(String prompt, List<String> options) {
    ComboBox<String> results = new ComboBox<>();
    results.setPromptText(prompt);
    results.getItems().addAll(options);
    results.valueProperty().bind(model.storyNameProperty());
    
    return results;
  }

  private Node createButton(String text, Runnable action) {
    Button results = new Button();
    results.setText(text);
    results.setOnAction(event -> action.run());
    return results;
  }
}
