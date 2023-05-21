package edu.ntnu.idatt2001.util.widgets;

import java.io.File;
import java.util.List;

import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Widgets {

  private Widgets() {
    // Prevents instantiation of utility class
  }

  public static Label createLabel(String text, String styleClass) {
    Label results = new Label(text);
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, StringProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    property.bindBidirectional(results.textProperty());
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, IntegerProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    results.textProperty().addListener((observable, oldValue, newValue) -> 
        property.set(Integer.parseInt(newValue)));
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, ListProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    results.textProperty().addListener((observable, oldValue, newValue) -> 
        property.add(newValue));
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static ButtonBar createButtonBar(String styleClass, Button... buttons) {
    ButtonBar results = new ButtonBar();
    results.getStyleClass().add(styleClass);
    results.getButtons().addAll(buttons);
    return results;
  }

  public static Button createButton(String text, Runnable action, String styleClass) {
    Button results = new Button(text);
    results.getStyleClass().add(styleClass);
    results.setOnAction(event -> {
      Media clickSound = new Media(new File("src/main/resources/sound/click.mp3").toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
      mediaPlayer.seek(Duration.ZERO);
      mediaPlayer.play();
      action.run();
    });
    return results;
  }

  public static Label createLabelWithBinding(Property property, String styleClass) {
    Label label = Widgets.createLabel("0", styleClass);
    property.addListener((observable, oldValue, newValue) -> {
      if (property instanceof ListProperty && ((List) newValue).isEmpty()) {
        label.setText("Empty");
      } else {
        label.setText(newValue.toString());
      }
    });
    return label;
  }

  public static Button createDifficultyButton(String label, int difficulty, CharacterScreenModel model, String styleClass) {
    return Widgets.createButton(label, () -> model.setDifficulty(difficulty), styleClass);
  }
}
