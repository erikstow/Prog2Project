package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.GoalFactory;
import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.model.screentype.CharacterScreenType;
import edu.ntnu.idatt2001.view.characterScreen.CharacterDifficultyScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterGoalsScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterInfoScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterSummaryScreenBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

public class CharacterScreenController extends Controller {

  private final Region view;
  private final CharacterScreenState model;


  public CharacterScreenController() {
    model = new CharacterScreenState();
    model.setCurrentScreen(CharacterScreenType.INFO_SCREEN);

    view = new CharacterScreenBuilder(model, this::back, this::next,
        new CharacterInfoScreenBuilder(model).build(),
        new CharacterDifficultyScreenBuilder(model).build(),
        new CharacterGoalsScreenBuilder(model, this::addGoal, this::undoGoal).build(),
        new CharacterSummaryScreenBuilder(model).build()
    ).build();

    model.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null && newValue.intValue() != 0) {
        model.sethealth(30 / model.getDifficulty());
        model.setScore(0 / model.getDifficulty());
        model.setGold(300 / model.getDifficulty());
        model.setInventory(new ArrayList<>());
      } else {
        model.sethealth(0);
        model.setScore(0);
        model.setGold(0);
        model.setInventory(new ArrayList<>());
      }
    });
    model.currentScreen().addListener((observable, oldValue, newValue) -> isStartAllowed());
    model.setPlayerImages(FXCollections.observableList(getPlayerImages()));
    System.out.println(model.getPlayerImages().size());
  }

  private void back() {
    if (model.getCurrentScreen() == CharacterScreenType.DIFFICULTY_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.INFO_SCREEN);
    } else if (model.getCurrentScreen() == CharacterScreenType.GOALS_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (model.getCurrentScreen() == CharacterScreenType.SUMMARY_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.GOALS_SCREEN);
    } else {
      update(new DataUpdateEvent("returnToTitle", null));
    }
  }

  private void next() {
    if (model.getCurrentScreen() == CharacterScreenType.INFO_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (model.getCurrentScreen() == CharacterScreenType.DIFFICULTY_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.GOALS_SCREEN);
    } else if (model.getCurrentScreen() == CharacterScreenType.GOALS_SCREEN) {
      model.setCurrentScreen(CharacterScreenType.SUMMARY_SCREEN);
    } else {
      start();
    }
  }

  private void start() {
    Player player = new Player.PlayerBuilder(model.getName())
        .gold(300 / model.getDifficulty())
        .health(30 / model.getDifficulty())
        .build();

    DataUpdateEvent createdPlayer = new DataUpdateEvent("createdPlayer", player);
    DataUpdateEvent chosenGoals = new DataUpdateEvent("chosenGoals", model.getGoals());
    DataUpdateEvent chosenSprite = new DataUpdateEvent("chosenSprite", model.getChosenPlayerImage());
    DataUpdateEvent startPressed = new DataUpdateEvent("startGamePressed", null);

    update(createdPlayer);
    update(chosenGoals);
    update(chosenSprite);
    update(startPressed);
  }

  private void addGoal() {
    try {
      Goal goal = GoalFactory.get(
          GoalFactory.GoalType.valueOf(model.getGoalType().toUpperCase() + "GOAL"),
          model.getGoalValue());
      model.goals().get().add(goal);
    } catch (NumberFormatException | NullPointerException e) {
      update(new DataUpdateEvent("error", e));
    }
  }

  private void undoGoal() {
    if (!model.goals().get().isEmpty()) {
      model.goals().get().remove(model.goals().get().size() - 1);
    }
  }

  public Region getView() {
    return view;
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    if (event.getKey().equals("reset")) {
      model.reset();
      model.setCurrentScreen(CharacterScreenType.INFO_SCREEN);
    }
  }

  private void isStartAllowed() {
    boolean notSummaryScreen = model.getCurrentScreen() != CharacterScreenType.SUMMARY_SCREEN;
    boolean validName = !model.getName().isEmpty();
    boolean validDifficulty =  model.getDifficulty() != 0;
    boolean hasSprite = model.getChosenPlayerImage() != null;
    boolean startAllowed = validDifficulty && validName && hasSprite;
    model.setNextAllowed(notSummaryScreen || startAllowed);
  }

  private List<Image> getPlayerImages() {
    return Arrays.asList(getImage("female-sprite.png"), getImage("male-sprite.png"));
  }

  private Image getImage(String path) {
    return new Image(getClass().getResourceAsStream("/images/" + path));
  }
}
