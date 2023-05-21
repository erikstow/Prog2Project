package edu.ntnu.idatt2001.model.gui;

import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.goals.Goal;
import javafx.beans.property.*;

import java.util.List;

public class ApplicationModel {
  private Game game;
  private final ObjectProperty<ScreenType> currentScreen = new SimpleObjectProperty<>(ScreenType.TITLE_SCREEN);
  private ScreenType previousScreen;
  private Story story;
  private Player startingPlayer;
  private List<Goal> goals;
  private final BooleanProperty isMusicOn = new SimpleBooleanProperty(true);


  public ScreenType getPreviousScreen() {
    return previousScreen;
  }

  public void setPreviousScreen(ScreenType previousScreen) {
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

  public List<Goal> getGoals() {
    return goals;
  }

  public void setGoals(List<Goal> goals) {
    this.goals = goals;
  }

  public BooleanProperty isMusicOnProperty() {
    return isMusicOn;
  }

  public ScreenType getCurrentScreen() {
    return currentScreen.get();
  }

  public ObjectProperty<ScreenType> currentScreenProperty() {
    return currentScreen;
  }

  public void setCurrentScreen(ScreenType currentScreen) {
    this.currentScreen.set(currentScreen);
  }
}


