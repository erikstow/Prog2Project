package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.GameModel;
import edu.ntnu.idatt2001.models.TitleScreenModel;
import edu.ntnu.idatt2001.views.TitleScreenBuilder;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class TitleScreenController {
  private final Region view;
  private final TitleScreenModel model;
  private final GameModel gameModel;
  private final Runnable startAction;

  public TitleScreenController(Runnable startAction, GameModel gameModel) {
    List<String> storyNameList = getStoryList();
    model = new TitleScreenModel();
    view = new TitleScreenBuilder(model, storyNameList, startAction).build();
    this.gameModel = gameModel;
    this.startAction = startAction;
    model.storyNameProperty().addListener((observable, oldValue, newValue) -> 
        model.setStartAllowed(!newValue.isEmpty())); //TODO: check if story exists
  }

  public Region getView() {
    return view;
  }

  private List<String> getStoryList() { //TODO: get list from file
    List<String> storyNameList = new ArrayList<>();
    storyNameList.add("Story 1"); 
    storyNameList.add("Story 2"); 
    storyNameList.add("Story 3"); 
    return storyNameList;
  }

  private void startGame() {
    gameModel.setStoryName(model.getStoryName());
    startAction.run();
  }
}
