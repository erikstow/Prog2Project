package edu.ntnu.idatt2001.controller;

import java.util.ArrayList;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.goals.GoalFactory;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenType;
import edu.ntnu.idatt2001.view.characterScreen.CharacterDifficultyScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterGoalsScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterInfoScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterSummaryScreenBuilder;
import javafx.scene.layout.Region;

public class CharacterScreenController extends Controller  {

  private final Region infoView;
  private final Region difficultyView;
  private final Region goalsView;
  private final Region summaryView;
  private final Region view;
  private final CharacterScreenModel model;
  

  public CharacterScreenController() {
    model = new CharacterScreenModel();
    infoView = new CharacterInfoScreenBuilder(model).build();
    difficultyView = new CharacterDifficultyScreenBuilder(model).build();
    goalsView = new CharacterGoalsScreenBuilder(model, this::addGoal, this::undoGoal).build();
    summaryView = new CharacterSummaryScreenBuilder(model).build();
    model.setCurrentScreen(infoView);
    view = new CharacterScreenBuilder(model, this::back, this::next).build();
    model.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        model.sethealth(30 / model.getDifficulty());
        model.setScore(0 / model.getDifficulty());
        model.setGold(300 / model.getDifficulty());
        model.setInventory(new ArrayList<>());
      }
    });
    model.currentScreen().addListener((observable, oldValue, newValue) -> isStartAllowed());
  }

  private void back() {
    if (model.getCurrentScreen() == difficultyView) {
      this.changeScreen(CharacterScreenType.INFO_SCREEN);
    } else if (model.getCurrentScreen() == goalsView) {
      this.changeScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (model.getCurrentScreen() == summaryView) {
      this.changeScreen(CharacterScreenType.GOALS_SCREEN);
    }
  }

  private void next() {  
    if (model.getCurrentScreen() == infoView) {
      this.changeScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (model.getCurrentScreen() == difficultyView) {
      this.changeScreen(CharacterScreenType.GOALS_SCREEN);
    } else if (model.getCurrentScreen() == goalsView) {
      this.changeScreen(CharacterScreenType.SUMMARY_SCREEN);
    } else if (model.getCurrentScreen() == summaryView) {
      this.start();
    }
  }
  
  private void start() {
    Player player = new Player.PlayerBuilder(model.getName())
      .gold(300 / model.getDifficulty())
      .health(30 / model.getDifficulty())
      .build();

    DataUpdateEvent createdPlayer = new DataUpdateEvent("createdPlayer", player);
    DataUpdateEvent chosenGoals = new DataUpdateEvent("chosenGoals", model.getGoals());
    DataUpdateEvent startPressed = new DataUpdateEvent("startGamePressed", null);
    update(createdPlayer);
    update(chosenGoals);
    update(startPressed);
  }

  private void addGoal() {
    Goal goal = GoalFactory.get(
        GoalFactory.GoalType.valueOf(model.getGoalType().toUpperCase() + "GOAL"),
         model.getGoalValue());
    model.goals().get().add(goal);
  }

  private void undoGoal() {
    if (!model.goals().get().isEmpty()) {
      model.goals().get().remove(model.goals().get().size() - 1);
    }
  }

  private void changeScreen(CharacterScreenType screen) {
    switch (screen) {
      case INFO_SCREEN -> model.setCurrentScreen(infoView);
      case DIFFICULTY_SCREEN -> model.setCurrentScreen(difficultyView);
      case GOALS_SCREEN -> model.setCurrentScreen(goalsView);
      case SUMMARY_SCREEN -> model.setCurrentScreen(summaryView);
    }
  }
    
  public Region getView() {
    return view;
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    if (event.getKey().equals("reset")) {
      System.out.println("soie");
    }
  }

  private void isStartAllowed() {
    if (model.getCurrentScreen() == summaryView && (model.getDifficulty() == 0 || model.getName().isEmpty())) {
        model.setNextAllowed(false);
      } 
      else {model.setNextAllowed(true);}
    }
  }
