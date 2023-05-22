package edu.ntnu.idatt2001.model.state;

import edu.ntnu.idatt2001.model.game.Game;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.goals.Goal;
import java.util.List;

import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ApplicationState {
  private Game game;
  private final ObjectProperty<ApplicationScreenType> currentScreen =
      new SimpleObjectProperty<>(ApplicationScreenType.TITLE_SCREEN);
  private ApplicationScreenType previousScreen;
  private Story story;
  private Player startingPlayer;
  private List<Goal> goals;
  private final BooleanProperty isMusicOn = new SimpleBooleanProperty(true);


  public ApplicationScreenType getPreviousScreen() {
    return previousScreen;
  }

  public void setPreviousScreen(ApplicationScreenType previousScreen) {
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

  public ApplicationScreenType getCurrentScreen() {
    return currentScreen.get();
  }

  public ObjectProperty<ApplicationScreenType> currentScreenProperty() {
    return currentScreen;
  }

  public void setCurrentScreen(ApplicationScreenType currentScreen) {
    this.currentScreen.set(currentScreen);
  }
}


