package edu.ntnu.idatt2001;

public class GoldAction implements Action {
  private final int gold;

  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public void execute(Player player) {
    player.addGold(this.gold);
  }
}
