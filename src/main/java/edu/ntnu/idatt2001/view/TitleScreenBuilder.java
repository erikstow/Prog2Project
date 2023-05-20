package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.gui.TitleScreenModel;
import java.util.List;

import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    results.getStylesheets().add("title.css");
    results.getStyleClass().add("title-pane");
    Label title = Widgets.createLabel("Paths", "title-label");
    Label subtitle = Widgets.createLabel("Into the Unknown", "subtitle-label");
    Node storySelect = createComboBox("Choose Story", storyTitles);
    storySelect.getStyleClass().add("story-select");
    Button startButton = Widgets.createButton("START", actionHandler, "start-button");
    startButton.disableProperty().bind(model.startAllowedPorperty().not());
    Node info = createInfoBox();
    results.getChildren().addAll(title, subtitle, storySelect, startButton, info);
    return results;
  }

  private Node createComboBox(String prompt, List<String> options) {
    ComboBox<String> results = new ComboBox<>();
    results.setPromptText(prompt);
    results.getItems().addAll(options);
    model.storyNameProperty().bind(results.valueProperty());

    return results;
  }

  private Node createInfoBox() {
    VBox results = new VBox();
    results.getStyleClass().add("info-box");
    Label filePathLabel = new Label();
    filePathLabel.textProperty().bind(model.filePathProperty());
    filePathLabel.getStyleClass().add("file-path-label");
  
    VBox brokenLinksBox = new VBox();
    brokenLinksBox.getStyleClass().add("broken-links-box");
    model.brokenLinksProperty().addListener((observable, oldValue, newValue) -> {
      brokenLinksBox.getChildren().clear();
      brokenLinksBox.getChildren().add(formatBrokenLinksBox());
    });
  
    results.getChildren().add(filePathLabel);
    results.getChildren().add(brokenLinksBox);
    VBox.setMargin(brokenLinksBox, new Insets(20, 0, 0, 0)); // Set a fixed margin for the brokenLinksBox
    return results;
  }


  private Node formatBrokenLinksBox() {
    VBox results = new VBox();
    for (Link link : model.getBrokenLinks()) {
      Label label = new Label(link.getAsString());
      label.getStyleClass().add("broken-links-label");
      results.getChildren().add(label);
      
    }
    return results;
  }
}
