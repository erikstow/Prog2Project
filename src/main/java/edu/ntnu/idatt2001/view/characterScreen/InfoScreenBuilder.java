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

/**
 * This class provides a builder for the character information screen in the
 * game.
 * The screen displays the character's name and appearance, *
 * and provides an interface for changing these attributes.
 */
public class InfoScreenBuilder implements Builder<Region> {

  private final CharacterScreenState state;

  /**
   * Constructor for CharacterInfoScreenBuilder.
   *
   * @param state the CharacterScreenState model containing the current character
   *              state
   */
  public InfoScreenBuilder(CharacterScreenState state) {
    this.state = state;
  }

  /**
   * Builds the character information screen as a Region.
   * Sets up the layout and bindings for all UI elements on the screen.
   *
   * @return a Region containing the character information screen
   */
  @Override
  public Region build() {
    VBox results = new VBox();
    results.getStyleClass().add("vbox");
    results.getStylesheets().add("/css/playerinfo.css");

    Label name = Widgets.createLabel("What is your name?*", "character-info-label");
    TextField nameField = Widgets.createTextField("Name...", state.name(), "character-info-text-field");
    Label appearance = Widgets.createLabel("What do you look like?*", "character-info-label");
    Node appearanceField = createSpritePicker();

    name.getStyleClass().add("character-info-label");
    nameField.getStyleClass().add("character-info-text-field");
    appearance.getStyleClass().add("character-info-label");

    results.getChildren().addAll(name, nameField, appearance, appearanceField);
    results.getStyleClass().add("vbox");

    return results;
  }

  /**
   * Creates an HBox for choosing the character's sprite image.
   * The HBox is populated with ImageViews for each available sprite,
   * and clicking on an ImageView sets the corresponding image as the character's
   * chosen sprite.
   *
   * @return a Node representing the HBox
   */
  private Node createSpritePicker() {
    HBox results = new HBox();
    results.getStyleClass().add("sprite-box");
    state.playerImagesProperty().addListener((observable, oldValue, newValue) -> {
      for (Image image : state.getPlayerImages()) {
        ImageView view = new ImageView(image);
        view.getStyleClass().add("sprite-image");
        view.setPreserveRatio(true);
        view.setFitHeight(250);
        view.setOnMouseClicked(event -> {
          clearLastClicked(results);
          view.setStyle(
              "-fx-effect: dropshadow(gaussian, #00FF00, 15, 0, 0, 0); -fx-border-color: #00FF00; -fx-border-width: 2px;");
          state.setChosenPlayerImage(image);
        });
        results.getChildren().add(view);
      }
    });

    return results;
  }

  private void clearLastClicked(HBox hbox) {
    for (Node node : hbox.getChildren()) {
      if (node instanceof ImageView) {
        node.setStyle("");
      }
    }
  }
}