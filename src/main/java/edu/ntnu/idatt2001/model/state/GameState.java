package edu.ntnu.idatt2001.model.state;

import edu.ntnu.idatt2001.model.game.Link;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Represents the state of the game at any given time. This includes properties
 * like the game's title, content, associated links, player health, score, gold,
 * and inventory contents.
 */
public class GameState {
  private final StringProperty title = new SimpleStringProperty();
  private final StringProperty content = new SimpleStringProperty();
  private final ListProperty<Link> links = new SimpleListProperty<>();
  private final StringProperty health = new SimpleStringProperty();
  private final StringProperty score = new SimpleStringProperty();
  private final StringProperty gold = new SimpleStringProperty();
  private final ListProperty<String> inventory = new SimpleListProperty<>();
  private final StringProperty name = new SimpleStringProperty();
  private final ObjectProperty<Image> playerSprite = new SimpleObjectProperty<>();
  private final StringProperty storyTitle = new SimpleStringProperty();

  public String getStoryTitle() {
    return storyTitle.get();
  }

  public StringProperty storyTitleProperty() {
    return storyTitle;
  }

  public void setStoryTitle(String storyTitle) {
    this.storyTitle.set(storyTitle);
  }

  public String getTitle() {
    return title.get();
  }

  public StringProperty titleProperty() {
    return title;
  }

  public String getContent() {
    return content.get();
  }

  public StringProperty contentProperty() {
    return content;
  }

  public ObservableList<Link> getLinks() {
    return links.get();
  }

  public ListProperty<Link> linksProperty() {
    return links;
  }

  public void setTitle(String title) {
    this.title.set(title);
  }

  public void setContent(String content) {
    this.content.set(content);
  }

  public void setLinks(ObservableList<Link> links) {
    this.links.set(links);
  }

  public String getHealth() {
    return health.get();
  }

  public StringProperty healthProperty() {
    return health;
  }

  public void setHealth(String health) {
    this.health.set(health);
  }

  public String getScore() {
    return score.get();
  }

  public StringProperty scoreProperty() {
    return score;
  }

  public void setScore(String score) {
    this.score.set(score);
  }

  public String getGold() {
    return gold.get();
  }

  public StringProperty goldProperty() {
    return gold;
  }

  public void setGold(String gold) {
    this.gold.set(gold);
  }

  public ObservableList<String> getInventory() {
    return inventory.get();
  }

  public ListProperty<String> inventoryProperty() {
    return inventory;
  }

  public void setInventory(ObservableList<String> inventory) {
    this.inventory.set(inventory);
  }

  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public Image getPlayerSprite() {
    return playerSprite.get();
  }

  public ObjectProperty<Image> playerSpriteProperty() {
    return playerSprite;
  }

  public void setPlayerSprite(Image playerSprite) {
    this.playerSprite.set(playerSprite);
  }

}
