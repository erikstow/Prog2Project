package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.game.Game;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameModel {
  private final StringProperty storyName = new SimpleStringProperty();
  private Game game;

}


