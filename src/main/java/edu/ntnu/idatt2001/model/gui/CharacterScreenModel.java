package edu.ntnu.idatt2001.model.gui;

import edu.ntnu.idatt2001.model.goals.Goal;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

public class CharacterScreenModel {
  
  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty appearence = new SimpleStringProperty();
  private final ListProperty<Goal> goals = new SimpleListProperty<>();
  private final IntegerProperty difficulty = new SimpleIntegerProperty();
  private final IntegerProperty health = new SimpleIntegerProperty();
  private final IntegerProperty gold = new SimpleIntegerProperty();
  private final IntegerProperty score = new SimpleIntegerProperty();
  private final ListProperty<String> inventory = new SimpleListProperty<>();
  private final ObjectProperty<Region> currentScreen = new SimpleObjectProperty<>();


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

  public ObjectProperty<Region> currentScreen() {
    return currentScreen;
  }

  public void setCurrentScreen(Region currentScreen) {
    this.currentScreen.set(currentScreen);
  }
  
  public Region getCurrentScreen() {
    return this.currentScreen.get();
  }
  
  public String getAppearence() {
    return appearence.get();
  }
  
  public void setAppearence(String appearence) {
    this.appearence.set(appearence);
  }
  
}
