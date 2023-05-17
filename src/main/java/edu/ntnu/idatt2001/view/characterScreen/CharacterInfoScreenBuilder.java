package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterInfoScreenBuilder implements Builder<Region> {

  private final CharacterScreenModel model;

  public CharacterInfoScreenBuilder(CharacterScreenModel model) {
    this.model = model;
  }

  @Override
  public Region build() {
    VBox results = new VBox();
    Node name = Widgets.createLabel("Who are you?", "character-info-label");
    Node nameField = Widgets.createTextField("Name", model.name(), "character-info-text-field");
    Node appearance = Widgets.createLabel("What do you look like?", "character-info-label");
    Node appearanceField = Widgets.createTextField("Appearance", model.appearence(),
        "character-info-text-field");
    results.getChildren().addAll(name, nameField, appearance, appearanceField);
    return results;
  }
}
