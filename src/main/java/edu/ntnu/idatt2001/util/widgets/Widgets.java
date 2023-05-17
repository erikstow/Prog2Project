package edu.ntnu.idatt2001.util.widgets;

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

public class Widgets {
  
  public static Label createLabel(String text, String styleClass) {
    Label results = new Label(text);
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, StringProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    property.bind(results.textProperty());
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
    results.getButtons().addAll(buttons);
    return results;
  }

  public static Button createButton(String text, Runnable action, String styleClass) {
    Button results = new Button(text);
    results.setOnAction(event -> action.run());
    return results;
  }

  public static Label createLabelWithBinding(Property property) {
    Label label = Widgets.createLabel("0", "");
    property.addListener((observable, oldValue, newValue) -> {
      if (property instanceof ListProperty && ((List) newValue).isEmpty()) {
        label.setText("Empty");
      } else {
        label.setText(newValue.toString());
      }
    });
    return label;
  }

  public static Button createDifficultyButton(String label, int difficulty, CharacterScreenModel model) {
    return Widgets.createButton(label, () -> model.setDifficulty(difficulty), "");
  }

}
