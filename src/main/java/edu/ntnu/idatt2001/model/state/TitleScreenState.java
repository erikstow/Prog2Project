package edu.ntnu.idatt2001.model.state;

import edu.ntnu.idatt2001.model.game.Link;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the state of the title screen at any given time.
 * This includes properties such as the name of the story, if a start is allowed,
 * broken links, and the file path.
 */
public class TitleScreenState {
  private final StringProperty storyName = new SimpleStringProperty("");
  private final BooleanProperty startAllowed = new SimpleBooleanProperty(false);
  private final ListProperty<Link> brokenLinks =
      new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));
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