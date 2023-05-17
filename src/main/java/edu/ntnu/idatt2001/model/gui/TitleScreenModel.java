package edu.ntnu.idatt2001.model.gui;

import edu.ntnu.idatt2001.model.game.Link;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TitleScreenModel {
  private final StringProperty storyName = new SimpleStringProperty("");
  private final BooleanProperty startAllowed = new SimpleBooleanProperty(false);
  private final ListProperty<Link> brokenLinks = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));
  private final StringProperty filePath = new SimpleStringProperty("");

  public String getStoryName() {
    return storyName.get();
  }

  public StringProperty storyNameProperty() {
    return storyName;
  }

  public void setStartAllowed(boolean startAllowed) {
    this.startAllowed.set(startAllowed);
  }

  public BooleanProperty startAllowedPorperty() {
    return startAllowed;
  }

  public ListProperty<Link> brokenLinksProperty() {
    return brokenLinks;
  }

  public StringProperty filePathProperty() {
    return filePath;
  }

  public List<Link> getBrokenLinks() {
    return brokenLinks.get();
  }

  public void setBrokenLinks(ObservableList<Link> brokenLinks) {
    this.brokenLinks.set(brokenLinks);
  }

  public String getFilePath() {
    return filePath.get();
  }

  public void setFilePath(String filePath) {
    this.filePath.set(filePath);
  }
}