package edu.ntnu.idatt2001.model.gui;

import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.game.Story;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;

public class ApplicationModel {
  private Game game;
  private final ObjectProperty<Region> currentScreen = new SimpleObjectProperty<>();
  private Region previousScreen = new Region();
  private Story story;
  private Player startingPlayer;

  public ObjectProperty<Region> currentScreenProperty() {
    return currentScreen;
  }

  public Region getCurrentScreen() {
    return currentScreen.get();
  }

  public void setCurrentScreen(Region screen) {
    currentScreen.set(screen);
  }

  public Region getPreviousScreen() {
    return previousScreen;
  }

  public void setPreviousScreen(Region previousScreen) {
    this.previousScreen = previousScreen;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Story getStory() {
    return story;
  }

  public void setStory(Story story) {
    this.story = story;
  }

  public Player getStartingPlayer() {
    return startingPlayer;
  }

  public void setStartingPlayer(Player startingPlayer) {
    this.startingPlayer = startingPlayer;
  }
}

