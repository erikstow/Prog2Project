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
   * @param name The name of the player.
   * @param health The health of the player.
   * @param score The score of the player.
   * @param gold The gold of the player.
   */
  public Player(String name, int health, int score, int gold) {
    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
    inventory = new ArrayList<>();//TODO(Erik) Arraylist maybe not optimal?
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
   * @param health The amount of health to be added.
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /** Method to add score to the player.
   * @param points The amount of score to be added.
   */
  public void addScore(int points) {
    this.score += points;
  }

  /** Method to add gold to the player.
   * @param gold The amount of gold to be added.
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /** Method to add an item to the inventory.
   * @param item The item to be added.
   */
  public void addToInventory(String item) {
    inventory.add(item);
  }
}
