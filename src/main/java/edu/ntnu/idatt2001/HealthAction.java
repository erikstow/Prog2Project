package edu.ntnu.idatt2001;

public class HealthAction implements Action {
  private final int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) throws IllegalArgumentException {
    if (player.getHealth() + this.health < 0) {
      player.addHealth(-player.getHealth());
    }
    player.addHealth(this.health);
  }
}
