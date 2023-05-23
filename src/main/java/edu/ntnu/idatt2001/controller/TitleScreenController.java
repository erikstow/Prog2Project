package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.game.Story;
import edu.ntnu.idatt2001.model.state.TitleScreenState;
import edu.ntnu.idatt2001.util.StoryReader;
import edu.ntnu.idatt2001.view.TitleScreenBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.scene.layout.Region;

/**
 * The TitleScreenController class manages the interactions of the title screen
 * including loading available stories and handling user interactions.
 * It is responsibe for loading the chosen story.
 * It extends the Controller class.
 */
public class TitleScreenController
    extends Controller {
  private final Region view;
  private final TitleScreenState state;
  private static final String STORIES_PATH = "src/main/resources/stories/";
  private static final String FILE_EXTENSION = ".paths";
  private Story story;

  /**
   * Constructor for TitleScreenController. Initializes the model and view, and
   * populates the story list.
   */
  public TitleScreenController() {
    List<String> storyNameList = getStoryList();
    state = new TitleScreenState();
    view = new TitleScreenBuilder(state, storyNameList, this::startGame).build();
    state.storyNameProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null && !newValue.isEmpty() && !newValue.isBlank()) {
        try {
          readStory();
          displayStoryInformation();
          state.setStartAllowed(true);
        } catch (IOException e) {
          update(new DataUpdateEvent("error", e));
        }
      }
    });
  }

  /**
   * Reads the story selected by the user.
   *
   * @throws IOException if there is an issue reading the story file.
   */
  private void readStory() throws IOException {
    story = StoryReader.read(STORIES_PATH + state.getStoryName() + FILE_EXTENSION);
  }

  /**
   * Displays the information of the read story.
   */
  private void displayStoryInformation() {
    state.setBrokenLinks(FXCollections.observableList(story.getBrokenLinks()));
    state.setFilePath(getAbsoluteStoryFilePath());
  }

  /**
   * Gets the absolute path of the story file.
   *
   * @return the absolute file path of the story file.
   */
  private String getAbsoluteStoryFilePath() {
    File f = new File(STORIES_PATH + state.getStoryName() + FILE_EXTENSION);
    return f.getAbsolutePath();
  }

  /**
   * Fetches the list of available stories from the predefined stories directory.
   *
   * @return the list of available stories.
   */
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

  /**
   * Starts the game with the selected story and notifies observers.
   */
  private void startGame() {
    update(new DataUpdateEvent("story", story));
  }

  /**
   * Handles the events received by this controller. If the event is a reset event,
   * it resets the title screen to its initial state.
   *
   * @param event the event to handle.
   */
  @Override
  public void onUpdate(ControllerEvent event) {
    if (event.getKey().equals("reset")) {
      state.storyNameProperty().set(null);
      state.setBrokenLinks(FXCollections.observableList(new ArrayList<>()));
      state.setFilePath("");
      state.setStartAllowed(false);
    }
  }

  /**
   * Returns the view of this controller.
   *
   * @return the view of this controller.
   */
  public Region getView() {
    return view;
  }
}
