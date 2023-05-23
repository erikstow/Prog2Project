package edu.ntnu.idatt2001.model.state;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.screentype.CharacterScreenType;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

/**
 * Represents the state of the character creation. This state includes properties
 * like the character's name, appearance, goals, health, inventory, and more.
 * It also contains a boolean property that represents whether the next action is allowed or not.
 */
public class CharacterScreenState {
  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty appearence = new SimpleStringProperty();
  private final ListProperty<Goal> goals =
      new SimpleListProperty<>(FXCollections.observableArrayList());
  private final IntegerProperty difficulty = new SimpleIntegerProperty();
  private final IntegerProperty health = new SimpleIntegerProperty();
  private final IntegerProperty gold = new SimpleIntegerProperty();
  private final IntegerProperty score = new SimpleIntegerProperty();
  private final ListProperty<String> inventory =
      new SimpleListProperty<>(FXCollections.observableArrayList());
  private final StringProperty goalType = new SimpleStringProperty();
  private final StringProperty goalValue = new SimpleStringProperty();
  private final ObjectProperty<CharacterScreenType> currentScreen = new SimpleObjectProperty<>();
  private final BooleanProperty nextAllowed = new SimpleBooleanProperty(true);
  private final ListProperty<Image> playerImages =
      new SimpleListProperty<>(FXCollections.observableArrayList());
  private Image chosenPlayerImage;

  /**
   * Resets the character's state properties to their default values.
   */
  public void reset() {
    setName("");
    setAppearence("");
    setDifficulty(0);
    goals().set(FXCollections.observableList(new ArrayList<>()));
    goalType().set(null);
  }

  public StringProperty name() {
    return name;
  }

  public String getName() {
    return name.get();
  }

  public StringProperty appearence() {
    return appearence;
  }

  public ListProperty<Goal> goals() {
    return goals;
  }

  public List<Goal> getGoals() {
    return goals.get();
  }

  public void addGoal(Goal goal) {
    goals.add(goal);
  }

  public IntegerProperty difficulty() {
    return difficulty;
  }

  public int getDifficulty() {
    return difficulty.get();
  }

  public void setDifficulty(int difficulty) {
    this.difficulty.set(difficulty);
  }

  public IntegerProperty health() {
    return health;
  }

  public void sethealth(int health) {
    this.health.set(health);
  }

  public IntegerProperty gold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold.set(gold);
  }

  public IntegerProperty score() {
    return score;
  }

  public void setScore(int score) {
    this.score.set(score);
  }

  public ListProperty<String> inventory() {
    return inventory;
  }

  public List<String> getInventory() {
    return inventory.get();
  }

  public void setInventory(List<String> inventory) {
    this.inventory.setAll(inventory);
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public ObjectProperty<CharacterScreenType> currentScreen() {
    return currentScreen;
  }

  public void setCurrentScreen(CharacterScreenType currentScreen) {
    this.currentScreen.set(currentScreen);
  }
  
  public CharacterScreenType getCurrentScreen() {
    return this.currentScreen.get();
  }
  
  public String getAppearence() {
    return appearence.get();
  }
  
  public void setAppearence(String appearence) {
    this.appearence.set(appearence);
  }
  
  public StringProperty goalType() {
    return goalType;
  }

  public String getGoalType() {
    return goalType.get();
  }

  public StringProperty goalValue() {
    return goalValue;
  }

  public String getGoalValue() {
    return goalValue.get();
  }

  public BooleanProperty nextAllowed() {
    return nextAllowed;
  } 

  public boolean getNextAllowed() {
    return nextAllowed.get();
  }

  public void setNextAllowed(boolean nextAllowed) {
    this.nextAllowed.set(nextAllowed);
  }

  public List<Image> getPlayerImages() {
    return playerImages.get();
  }

  public ListProperty<Image> playerImagesProperty() {
    return playerImages;
  }

  public void setPlayerImages(List<Image> playerImages) {
    this.playerImages.set(FXCollections.observableArrayList(playerImages));
  }

  public Image getChosenPlayerImage() {
    return chosenPlayerImage;
  }

  public void setChosenPlayerImage(Image chosenPlayerImage) {
    this.chosenPlayerImage = chosenPlayerImage;
  }
}
