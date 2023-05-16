package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.gui.TitleScreenModel;
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
    Node info = createInfoBox();
    results.getChildren().addAll(title, storySelect, startButton, info);
    return results;
  }

  private Node createComboBox(String prompt, List<String> options) {
    ComboBox<String> results = new ComboBox<>();
    results.setPromptText(prompt);
    results.getItems().addAll(options);
    model.storyNameProperty().bind(results.valueProperty());

    return results;
  }

  private Node createButton(String text, Runnable action) {
    Button results = new Button();
    results.setText(text);
    results.disableProperty().bind(model.startAllowedPorperty().not());
    results.setOnAction(event -> action.run());
    return results;
  }

  private Node createInfoBox() {
    VBox results = new VBox();

    // model.storyNameProperty().addListener((observable, oldValue, newValue) -> );

    Label filePathLabel = new Label();
    filePathLabel.textProperty().bind(model.filePathProperty());

    VBox brokenLinksBox = new VBox();

    model.brokenLinksProperty().addListener((observable, oldValue, newValue) -> {
      brokenLinksBox.getChildren().clear();
      brokenLinksBox.getChildren().add(formatBrokenLinksBox());
    });

    results.getChildren().add(filePathLabel);
    results.getChildren().add(brokenLinksBox);
    return results;
  }

  private Node formatBrokenLinksBox() {
    VBox results = new VBox();
    for (Link link : model.getBrokenLinks()) {
      Label label = new Label(link.getAsString());
      results.getChildren().add(label);
    }

    return results;
  }
}
