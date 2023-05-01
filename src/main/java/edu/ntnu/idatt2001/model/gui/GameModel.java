package edu.ntnu.idatt2001.model.gui;

import edu.ntnu.idatt2001.model.game.Game;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;

public class GameModel {
  private Game game;
  private final ObjectProperty<Region> currentScreen = new SimpleObjectProperty<>();

  public ObjectProperty<Region> currentScreenProperty() {
    return currentScreen;
  }

  public Region getCurrentScreen() {
    return currentScreen.get();
  }

  public void setCurrentScreen(Region screen) {
    currentScreen.set(screen);
  }
}
