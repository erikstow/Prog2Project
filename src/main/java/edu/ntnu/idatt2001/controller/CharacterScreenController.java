package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.GoalFactory;
import edu.ntnu.idatt2001.model.screentype.CharacterScreenType;
import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.view.characterScreen.DifficultyScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.GoalsScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.InfoScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.SummaryScreenBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

/**
 * The CharacterScreenController class extends Controller and
 * manages different aspects of the Character Creation Screen like handling events,
 * transitioning between screens and maintaining the state of the character screen.
 */
public class CharacterScreenController extends Controller {

  private final Region view;
  private final CharacterScreenState state;

  /**
   * CharacterScreenController constructor initializes the character screen state,
   * and builds the views for different screens.
   */
  public CharacterScreenController() {
    state = new CharacterScreenState();
    state.setCurrentScreen(CharacterScreenType.INFO_SCREEN);

    view = new CharacterScreenBuilder(state, this::back, this::next,
        new InfoScreenBuilder(state).build(),
        new DifficultyScreenBuilder(state).build(),
        new GoalsScreenBuilder(state, this::addGoal, this::undoGoal).build(),
        new SummaryScreenBuilder(state).build()
    ).build();

    state.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null && newValue.intValue() != 0) {
        state.sethealth(30 / state.getDifficulty());
        state.setScore(0 / state.getDifficulty());
        state.setGold(300 / state.getDifficulty());
        state.setInventory(new ArrayList<>());
      } else {
        state.sethealth(0);
        state.setScore(0);
        state.setGold(0);
        state.setInventory(new ArrayList<>());
      }
    });
    state.currentScreen().addListener((observable, oldValue, newValue) -> isStartAllowed());
    state.setPlayerImages(FXCollections.observableList(getPlayerImages()));
  }

  /**
   * Handles the action when back button is pressed on the character screen.
   * Changes the current screen or returns to the title screen based on the current screen.
   */
  private void back() {
    if (state.getCurrentScreen() == CharacterScreenType.DIFFICULTY_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.INFO_SCREEN);
    } else if (state.getCurrentScreen() == CharacterScreenType.GOALS_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (state.getCurrentScreen() == CharacterScreenType.SUMMARY_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.GOALS_SCREEN);
    } else {
      update(new DataUpdateEvent("returnToTitle", null));
    }
  }

  /**
   * Handles the action when next button is pressed on the character screen.
   * Changes the current screen or starts the game based on the current screen.
   */
  private void next() {
    if (state.getCurrentScreen() == CharacterScreenType.INFO_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (state.getCurrentScreen() == CharacterScreenType.DIFFICULTY_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.GOALS_SCREEN);
    } else if (state.getCurrentScreen() == CharacterScreenType.GOALS_SCREEN) {
      state.setCurrentScreen(CharacterScreenType.SUMMARY_SCREEN);
    } else {
      start();
    }
  }

  /**
   * Starts the game by building the player and firing the necessary events
   * to send back the player, goals and character sprite.
   */
  private void start() {
    Player player = new Player.PlayerBuilder(state.getName())
        .gold(300 / state.getDifficulty())
        .health(30 / state.getDifficulty())
        .build();

    DataUpdateEvent createdPlayer = new DataUpdateEvent("createdPlayer", player);
    update(createdPlayer);

    DataUpdateEvent chosenGoals = new DataUpdateEvent("chosenGoals", state.getGoals());
    update(chosenGoals);

    DataUpdateEvent chosenSprite = new DataUpdateEvent("chosenSprite", state.getChosenPlayerImage());
    update(chosenSprite);

    DataUpdateEvent startPressed = new DataUpdateEvent("startGamePressed", null);
    update(startPressed);
  }

  /**
   * Adds a goal to the character screen state based on the current goal type and value.
   */
  private void addGoal() {
    try {
      Goal goal = GoalFactory.get(
          GoalFactory.GoalType.valueOf(state.getGoalType().toUpperCase() + "GOAL"),
          state.getGoalValue());
      state.goals().get().add(goal);
    } catch (NumberFormatException | NullPointerException e) {
      update(new DataUpdateEvent("error", e));
    }
  }

  /**
   * Removes the last added goal from the character screen state.
   */
  private void undoGoal() {
    if (!state.goals().get().isEmpty()) {
      state.goals().get().remove(state.goals().get().size() - 1);
    }
  }

  /**
   * Returns the view of the character screen controller.
   *
   * @return the current view
   */
  public Region getView() {
    return view;
  }

  /**
   * Handles the controller event. If the event key is 'reset',
   * resets the model and sets the current screen to INFO_SCREEN.
   *
   * @param event the controller event
   */
  @Override
  public void onUpdate(ControllerEvent event) {
    if (event.getKey().equals("reset")) {
      state.reset();
      state.setCurrentScreen(CharacterScreenType.INFO_SCREEN);
    }
  }

  /**
   * Determines whether the start button should be allowed on the
   * summary screen.
   * Start button is allowed only if all necessary fields are filled,
   * unless the current screen is not the summary screen.
   */
  private void isStartAllowed() {
    boolean notSummaryScreen = state.getCurrentScreen() != CharacterScreenType.SUMMARY_SCREEN;
    boolean validName = !state.getName().isEmpty();
    boolean validDifficulty =  state.getDifficulty() != 0;
    boolean hasSprite = state.getChosenPlayerImage() != null;
    boolean startAllowed = validDifficulty && validName && hasSprite;
    state.setNextAllowed(notSummaryScreen || startAllowed);
  }

  /**
   * Returns a list of player images available for selection.
   *
   * @return the list of player images
   */
  private List<Image> getPlayerImages() {
    return Arrays.asList(getImage("female-sprite.png"), getImage("male-sprite.png"));
  }

  /**
   * Retrieves an image from the specified path.
   *
   * @param path the path of the image
   * @return the image at the specified path
   */
  private Image getImage(String path) {
    return new Image(getClass().getResourceAsStream("/images/" + path));
  }
}
