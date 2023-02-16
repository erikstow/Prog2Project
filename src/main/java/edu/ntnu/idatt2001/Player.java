package edu.ntnu.idatt2001;

import java.util.ArrayList;
import java.util.List;

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
  public Player(String name, int health, int score, int gold) {
    if (name == null || health < 0 || score < 0 || gold < 0) {
      throw new IllegalArgumentException("Name cannot be null or empty, health, score and gold cannot be negative");
    }
    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
    this.inventory = new ArrayList<>();
    }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public int getScore() {
    return score;
  }

  public int getGold() {
    return gold;
  }

  public List<String> getInventory() {
    return inventory;
  }

  /** Method to add health to the player.
   *
   * @param health The amount of health to be added.
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /** Method to add score to the player.
   *
   * @param points The amount of score to be added.
   */
  public void addScore(int points) {
    this.score += points;
  }

  /** Method to add gold to the player.
   *
   * @param gold The amount of gold to be added.
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /** Method to add an item to the inventory.
   *
   * @param item The item to be added.
   */
  public void addToInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    inventory.add(item);
  }
}
