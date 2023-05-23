package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.game.Passage;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.state.GameState;
import edu.ntnu.idatt2001.view.GameScreenBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

/**
 * The GameController class is responsible for managing the GameState and interaction between the
 * game model and the view.
 * It extends the Controller class and implements the ControllerObserver interface.
 * It updates the view when it recieves a new passage or player object.
 */
public class GameController extends Controller implements ControllerObserver {
  private final GameState state;
  private final Region view;

  /**
   * Constructor for GameController. Initializes the game state and the view.
   */
  public GameController() {
    this.state = new GameState();
    this.view = new GameScreenBuilder(state, this::linkClicked).build();
  }

  /**
   * Handles a click on a Link in the game.
   *
   * @param link The clicked Link.
   */
  private void linkClicked(Link link) {
    update(new DataUpdateEvent("linkToNextPassage", link));
  }

  /**
   * Updates the GameController based on incoming events. The possible updates include
   * updating the current Passage, the Player, or the Player's sprite.
   *
   * @param event The ControllerEvent that triggers the update.
   */
  public void onUpdate(ControllerEvent event) {
    if (event instanceof DataUpdateEvent due) {
      if (due.getKey().equals("passage") && due.getValue() instanceof Passage passage) {
        updatePassage(passage);
      } else if (due.getKey().equals("player") && due.getValue() instanceof Player player) {
        updatePlayer(player);
      } else if (due.getKey().equals("chosenSprite") && due.getValue() instanceof Image image) {
        // model.setPlayerSprite(image);
      }
    }
  }

  /**
   * Updates the current Passage in the GameState.
   *
   * @param passage The new Passage to set in the GameState.
   */
  private void updatePassage(Passage passage) {
    state.setTitle(passage.getTitle());
    state.setContent(passage.getContent());
    ObservableList<Link> links = FXCollections.observableList(passage.getLinks());
    state.setLinks(links);
  }

  /**
   * Updates the Player in the GameState.
   *
   * @param player The new Player to set in the GameState.
   */
  private void updatePlayer(Player player) {
    state.setHealth(String.valueOf(player.getHealth()));
    state.setGold(String.valueOf(player.getGold()));
    ObservableList<String> inventory = FXCollections.observableList(player.getInventory());
    state.setInventory(inventory);
    state.setScore(String.valueOf(player.getScore()));
  }

  /**
   * Returns the view for this GameController.
   *
   * @return The view for this GameController.
   */
  public Region getView() {
    return view;
  }
}
