package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.gui.TitleScreenModel;
import edu.ntnu.idatt2001.util.filehandling.text.StoryReader;
import edu.ntnu.idatt2001.view.TitleScreenBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.scene.layout.Region;

public class TitleScreenController
    extends Controller {
  private Region view;
  private TitleScreenModel model;
  private static final String STORIES_PATH = "src/main/resources/stories/";
  private static final String FILE_EXTENSION = ".paths";
  private Story story;

  public TitleScreenController() {
    initModelAndView();
  }

  private void initModelAndView() {
    List<String> storyNameList = getStoryList();
    model = new TitleScreenModel();
    view = new TitleScreenBuilder(model, storyNameList, this::startGame).build();
    model.storyNameProperty().addListener((observable, oldValue, newValue) -> {
      try {
        readStory();
        displayStoryInformation();
        model.setStartAllowed(!newValue.isEmpty());
      } catch (IOException e) {
        update(new DataUpdateEvent("error", e));
      }
    });
  }

  private void readStory() throws IOException {
    story = StoryReader.read(STORIES_PATH + model.getStoryName() + FILE_EXTENSION);
  }

  private void displayStoryInformation() {
    model.setBrokenLinks(FXCollections.observableList(story.getBrokenLinks()));
    model.setFilePath(getAbsoluteStoryFilePath());
  }

  private String getAbsoluteStoryFilePath() {
    File f = new File(STORIES_PATH + model.getStoryName() + FILE_EXTENSION);
    return f.getAbsolutePath();
  }

  private List<String> getStoryList() {
    File[] files = new File(STORIES_PATH).listFiles();
    Objects.requireNonNull(files, "Could not find any files at " + STORIES_PATH);
    return Stream.of(files)
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .filter(file -> file.contains(FILE_EXTENSION))
        .map(file -> file.substring(0, file.length() - FILE_EXTENSION.length()))
        .toList();
  }

  private void startGame() {
    update(new DataUpdateEvent("story", story));
  }

  @Override
  public void onUpdate(ControllerEvent event) {
    if (event.getKey().equals("reset")) {
      initModelAndView();
    }
  }

  public Region getView() {
    return view;
  }
}
