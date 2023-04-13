package edu.ntnu.idatt2001.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TitleScreenModel {
  private final StringProperty storyName = new SimpleStringProperty("");
  private final BooleanProperty startAllowed = new SimpleBooleanProperty(false);

  public String getStoryName() {
    return storyName.get();
  }

  public StringProperty storyNameProperty() {
    return storyName;
  }

  public void setStartAllowed(boolean startAllowed) {
    this.startAllowed.set(startAllowed);
  }

  public BooleanProperty StartAllowedPorperty() {
    return startAllowed;
  }
}