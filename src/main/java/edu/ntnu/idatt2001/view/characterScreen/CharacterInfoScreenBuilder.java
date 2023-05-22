package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.List;

public class CharacterInfoScreenBuilder implements Builder<Region> {

  private final CharacterScreenState model;

  public CharacterInfoScreenBuilder(CharacterScreenState model) {
    this.model = model;
  }

  @Override
  public Region build() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    results.getStylesheets().add("playerinfo.css");

    Label name = Widgets.createLabel("Who are you?", "character-info-label");
    TextField nameField =
        Widgets.createTextField("Name..", model.name(), "character-info-text-field");
    Label appearance = Widgets.createLabel("What do you look like?", "character-info-label");
    Node appearanceField = createSpritePicker();

    name.getStyleClass().add("character-info-label");
    nameField.getStyleClass().add("character-info-text-field");
    appearance.getStyleClass().add("character-info-label");

    results.getChildren().addAll(name, nameField, appearance, appearanceField);
    results.getStyleClass().add("vbox");

    return results;
  }

  private Node createSpritePicker() {
    HBox results = new HBox();
    model.playerImagesProperty().addListener((observable, oldValue, newValue) -> {
      for (Image image : model.getPlayerImages()) {
        ImageView view = new ImageView(image);
        view.setOnMouseClicked(event -> model.setChosenPlayerImage(image));
        results.getChildren().add(view);
      }
    });

    return results;
  }
}