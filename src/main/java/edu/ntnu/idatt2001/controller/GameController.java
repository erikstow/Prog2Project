package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.gui.GameModel;
import edu.ntnu.idatt2001.view.GameScreenBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

public class GameController extends Controller implements ControllerObserver {
  private final GameModel model;
  private final Region view;

  public GameController() {
    this.model = new GameModel();
    this.view = new GameScreenBuilder(model, this::linkClicked).build();
  }

  private void linkClicked(Link link) {
    update(new DataUpdateEvent(this, "linkToNextPassage", link));
  }

  public void onUpdate(ControllerEvent event) {
    if (event instanceof DataUpdateEvent due) {
      if (due.getKey().equals("passage") && due.getValue() instanceof Passage passage) {
        updatePassage(passage);
      } else if (due.getKey().equals("player") && due.getValue() instanceof Player player) {
        updatePlayer(player);
      }
    }
  }

  private void updatePassage(Passage passage) {
    model.setTitle(passage.getTitle());
    model.setContent(passage.getContent());
    ObservableList<Link> links = FXCollections.observableList(passage.getLinks());
    model.setLinks(links);
  }

  private void updatePlayer(Player player) {
    model.setHealth(String.valueOf(player.getHealth()));
    model.setGold(String.valueOf(player.getGold()));
    ObservableList<String> inventory = FXCollections.observableList(player.getInventory());
    model.setInventory(inventory);
    model.setScore(String.valueOf(player.getScore()));
  }

  public Region getView() {
    return view;
  }
}
