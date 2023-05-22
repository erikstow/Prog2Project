package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

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
    TextField appearanceField = Widgets.createTextField("Appearance..",
        model.appearence(), "character-info-text-field");

    name.getStyleClass().add("character-info-label");
    nameField.getStyleClass().add("character-info-text-field");
    appearance.getStyleClass().add("character-info-label");
    appearanceField.getStyleClass().add("character-info-text-field");

    results.getChildren().addAll(name, nameField, appearance, appearanceField);
    results.getStyleClass().add("vbox");

    return results;
  }
}