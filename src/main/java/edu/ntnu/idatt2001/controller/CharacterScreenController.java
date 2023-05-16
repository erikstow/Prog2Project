package edu.ntnu.idatt2001.controller;

import java.util.ArrayList;

import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Player;
import edu.ntnu.idatt2001.model.game.Player.PlayerBuilder;
import edu.ntnu.idatt2001.model.gui.CharacterScreenModel;
import edu.ntnu.idatt2001.model.gui.CharacterScreenType;
import edu.ntnu.idatt2001.view.characterScreen.CharacterDifficultyScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterGoalsScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterInfoScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterScreenBuilder;
import edu.ntnu.idatt2001.view.characterScreen.CharacterSummaryScreenBuilder;
import javafx.scene.layout.Region;

public class CharacterScreenController extends ObservableController  {

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
    goalsView = new CharacterGoalsScreenBuilder(model).build();
    summaryView = new CharacterSummaryScreenBuilder(model).build();
    model.setCurrentScreen(infoView);
    view = new CharacterScreenBuilder(model, this::back, this::next).build();
    model.difficulty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        model.sethealth(15);
        model.setScore(0);
        model.setGold(0);
        model.setInventory(new ArrayList<>());
      }
    });
  }

  public void back() {
    if (model.getCurrentScreen() == difficultyView) {
      this.changeScreen(CharacterScreenType.INFO_SCREEN);
    } else if (model.getCurrentScreen() == goalsView) {
      this.changeScreen(CharacterScreenType.DIFFICULTY_SCREEN);
    } else if (model.getCurrentScreen() == summaryView) {
      this.changeScreen(CharacterScreenType.GOALS_SCREEN);
    }
  }

  public void next() {  
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
    Player player = new Player.PlayerBuilder("Erik").build();
    DataUpdateEvent createdPlayer = new DataUpdateEvent(this, "createdPlayer", player);
    DataUpdateEvent chosenGoals = new DataUpdateEvent(this, "chosenGoals", model.getGoals());
    update(createdPlayer);
    update(chosenGoals);
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

}
