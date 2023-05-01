package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.events.ScreenChangeEvent;
import edu.ntnu.idatt2001.model.gui.TitleScreenModel;
import edu.ntnu.idatt2001.view.TitleScreenBuilder;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Region;

public class TitleScreenController
    extends ObservableController {
  private final Region view;
  private final TitleScreenModel model;

  public TitleScreenController() {
    List<String> storyNameList = getStoryList();
    model = new TitleScreenModel();
    view = new TitleScreenBuilder(model, storyNameList, this::startGame).build();
    model.storyNameProperty().addListener((observable, oldValue, newValue) ->
        model.setStartAllowed(!newValue.isEmpty()));
  }

  private List<String> getStoryList() { //TODO: get list from file
    List<String> storyNameList = new ArrayList<>();
    storyNameList.add("Story 1");
    storyNameList.add("Story 2");
    storyNameList.add("Story 3");
    return storyNameList;
  }

  private void startGame() {
    update(new DataUpdateEvent(this, "storyTitle", model.getStoryName()));
    update(new ScreenChangeEvent(this, "start"));
  }

  public Region getView() {
    return view;
  }
}
