package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A player is a character in the game. It contains the name of the player, 
 * the health of the player, the score of the player, the gold of the player.
 * Aswell as the inventory of the player.
 */
public class Player {
  private final String name;
  private int health;
  private int score;
  private int gold;
  private final List<String> inventory;


  /** Constructor for Player.
   *
   * @param name The name of the player.
   * @param health The health of the player, must non-negative.
   * @param score The score of the player, must non-negative.
   * @param gold The gold of the player, must non-negative.
   */
  public Player(String name, int health, int score, int gold) 
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(name, "Name cannot be null");
    
    if (health <= 0 || score < 0 || gold < 0) {
      throw new IllegalArgumentException(
        "Health must be positiv, score and gold cannot be negative");
    }
    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
    this.inventory = new ArrayList<>();
  }

  /**
   * Method to get the name of the player.
   *
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Method to get the health of the player.
   *
   * @return The health of the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Method to get the score of the player.
   *
   * @return The score of the player.
   */
  public int getScore() {
    return score;
  }

  /**
   * Method to get the gold of the player.
   *
   * @return The gold of the player.
   */
  public int getGold() {
    return gold;
  }

  /**
   * Method to get the inventory of the player.
   * 
   * @return The inventory of the player.
   */
  public List<String> getInventory() {
    return inventory;
  }

  /** 
   * Method to add health to the player.
   *
   * @param health The amount of health to be added.
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * Method to add score to the player.
   *
   * @param points The amount of score to be added.
   */
  public void addScore(int points) {
    this.score += points;
  }

  /**
   * Method to add gold to the player.
   *
   * @param gold The amount of gold to be added.
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /**
   * Method to add an item to the inventory.
   *
   * @param item The item to be added.
   */
  public void addToInventory(String item) throws NullPointerException {
    Objects.requireNonNull(item, "Item cannot be null");
    
    inventory.add(item);
  }
}
