package edu.ntnu.idatt2001.util.widgets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
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
    property.addListener((observable, oldValue, newValue) -> results.setText(newValue));
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, IntegerProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    property.addListener((observable, oldValue, newValue) -> results.setText(newValue.toString()));
    results.getStyleClass().add(styleClass);
    return results;
  }

  public static TextField createTextField(String promptText, ListProperty property, String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    property.addListener((observable, oldValue, newValue) -> results.setText(newValue.toString()));
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

}
