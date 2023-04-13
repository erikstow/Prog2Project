package edu.ntnu.idatt2001.models.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A player is a character in the game. It contains the name of the player, 
 * the health of the player, the score of the player, the gold of the player.
 * Aswell as the inventory of the player.
 * It uses the builder pattern to create a player.
 */
public class Player {
  private final String name;
  private int health;
  private int score;
  private int gold;
  private final List<String> inventory;

  /**
   * A private constructor for the player class.
   * To create a player, use the PlayerBuilder class.
   * 
   * @param builder The builder to create the player.
   */
  private Player(PlayerBuilder builder) {
    this.name = builder.name;
    this.health = builder.health;
    this.score = builder.score;
    this.gold = builder.gold;
    this.inventory = new ArrayList<>(builder.inventory);
  }

  /**
   * A builder class for the player class.
   */
  public static class PlayerBuilder {
    private final String name;
    private int health;
    private int score;
    private int gold;
    private final List<String> inventory;

    /**
     * A constructor for the PlayerBuilder class.
     *
     * @param name The name of the player.
     */
    public PlayerBuilder(String name) {
    Objects.requireNonNull(name, "Name cannot be null");
      
      this.name = name;
      this.health = 100;
      this.score = 0;
      this.gold = 0;
      this.inventory = new ArrayList<>();
    }

    /**
     * Method to set the health of the player.
     * 
     * @param health The health of the player.
     * 
     * @return The builder.
     */
    public PlayerBuilder health(int health) {
      if (health <= 0) {
        throw new IllegalArgumentException("Health must be positive");
      }

      this.health = health;
      return this;
    }

    /**
     * Method to set the score of the player.
     * 
     * @param score The score of the player.
     * 
     * @return The builder.
     */
    public PlayerBuilder score(int score) {
      if (score < 0) {
        throw new IllegalArgumentException("Score cannot be negative");
      }
      this.score = score;
      return this;
    }

    /**
     * Method to set the gold of the player.
     * 
     * @param gold The gold of the player.
     * 
     * @return The builder.
     */
    public PlayerBuilder gold(int gold) {
      if (gold < 0) {
        throw new IllegalArgumentException("Gold cannot be negative");
      }
      this.gold = gold;
      return this;
    }

    /**
     * Method to set the inventory of the player.
     * 
     * @param inventory The inventory of the player.
     * 
     * @return The builder.
     */
    public PlayerBuilder inventory(List<String> inventory) {
      Objects.requireNonNull(inventory, "Inventory cannot be null");

      this.inventory.clear();
      this.inventory.addAll(inventory);
      return this;
    }

    /**
     * A method for retunring the built player.
     * 
     * @return The built player.
     */
    public Player build() {
      return new Player(this);
    }
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