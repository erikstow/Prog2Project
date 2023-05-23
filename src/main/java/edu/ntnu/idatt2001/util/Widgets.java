package edu.ntnu.idatt2001.util;

import java.io.File;
import java.util.List;
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

/**
 * Utility class Widgets provides static methods for
 * creating various JavaFX controls with custom styles.
 * The controls include Labels, TextFields, ButtonBars, and Buttons.
 */
public class Widgets {

  /**
   * Private constructor to prevent instantiation of utility class.
   */
  private Widgets() {
  }

  /**
   * Creates a Label with the provided text and style class.
   *
   * @param text The text of the Label.
   * @param styleClass The style class to apply to the Label.
   * @return The created Label.
   */
  public static Label createLabel(String text, String styleClass) {
    Label results = new Label(text);
    results.getStyleClass().add(styleClass);
    return results;
  }

  /**
   * Creates a TextField with the provided prompt text, property to bind, and style class.
   *
   * @param promptText The prompt text of the TextField.
   * @param property The StringProperty to bind bidirectionally to the TextField.
   * @param styleClass The style class to apply to the TextField.
   * @return The created TextField.
   */
  public static TextField createTextField(String promptText,
                                          StringProperty property,
                                          String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    property.bindBidirectional(results.textProperty());
    results.getStyleClass().add(styleClass);
    return results;
  }

  /**
   * Creates a TextField with the provided prompt text, integer property to bind, and style class.
   *
   * @param promptText The prompt text of the TextField.
   * @param property The IntegerProperty to bind to the TextField.
   * @param styleClass The style class to apply to the TextField.
   * @return The created TextField.
   */
  public static TextField createTextField(String promptText,
                                          IntegerProperty property,
                                          String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    results.textProperty().addListener((observable, oldValue, newValue) -> 
        property.set(Integer.parseInt(newValue)));
    results.getStyleClass().add(styleClass);
    return results;
  }

  /**
   * Creates a TextField with the provided prompt text, list property to bind, and style class.
   *
   * @param promptText The prompt text of the TextField.
   * @param property The ListProperty to bind to the TextField.
   * @param styleClass The style class to apply to the TextField.
   * @return The created TextField.
   */
  public static TextField createTextField(String promptText,
                                          ListProperty property,
                                          String styleClass) {
    TextField results = new TextField();
    results.setPromptText(promptText);
    results.textProperty().addListener((observable, oldValue, newValue) -> 
        property.add(newValue));
    results.getStyleClass().add(styleClass);
    return results;
  }

  /**
   * Creates a ButtonBar with the provided style class and buttons.
   *
   * @param styleClass The style class to apply to the ButtonBar.
   * @param buttons The buttons to add to the ButtonBar.
   * @return The created ButtonBar.
   */
  public static ButtonBar createButtonBar(String styleClass, Button... buttons) {
    ButtonBar results = new ButtonBar();
    results.getStyleClass().add(styleClass);
    results.getButtons().addAll(buttons);
    return results;
  }

  /**
   * Creates a Button with the provided text, action to run when clicked, and style class.
   * The button also plays a click sound when clicked.
   *
   * @param text The text of the Button.
   * @param action The action to run when the button is clicked.
   * @param styleClass The style class to apply to the Button.
   * @return The created Button.
   */
  public static Button createButton(String text,
                                    Runnable action,
                                    String styleClass) {
    Button results = new Button(text);
    results.getStyleClass().add(styleClass);
    results.setOnAction(event -> {
      String clickFile = new File("src/main/resources/sound/click.mp3").toURI().toString();
      Media clickSound = new Media(clickFile);
      MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
      mediaPlayer.seek(Duration.ZERO);
      mediaPlayer.play();
      action.run();
    });
    return results;
  }

  /**
   * Creates a Label that is bound to the provided property and has the provided style class.
   * If the property is a ListProperty and is empty, the Label text will be "Empty".
   *
   * @param property The Property to bind to the Label.
   * @param styleClass The style class to apply to the Label.
   * @return The created Label.
   */
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
}
