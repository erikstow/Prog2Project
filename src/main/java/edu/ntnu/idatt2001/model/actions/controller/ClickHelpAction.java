package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Alert;

public class ClickHelpAction implements ControllerAction {
  private static final String HELP_FILE_DIRECTORY = "src/main/resources/helpText/";
  private static final String TITLE_HELP_FILE = "titleHelp.txt";
  private static final String CREATION_HELP_FILE = "creationHelp.txt";
  private static final String SETTING_HELP_FILE = "settingHelp.txt";
  private static final String GAME_HELP_FILE = "gameHelp.txt";

  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState model)
      throws Exception {
    switch (model.getCurrentScreen()) {
      case TITLE_SCREEN ->
        createAlert("Title screen help", getHelpText(HELP_FILE_DIRECTORY + TITLE_HELP_FILE));
      case CREATION_SCREEN ->
        createAlert("Character creation help",
          getHelpText(HELP_FILE_DIRECTORY + CREATION_HELP_FILE));
      case SETTINGS_SCREEN ->
        createAlert("Setting screen help", getHelpText(HELP_FILE_DIRECTORY + SETTING_HELP_FILE));
      case PASSAGE_SCREEN ->
        createAlert("Game help", getHelpText(HELP_FILE_DIRECTORY + GAME_HELP_FILE));
    }
  }

  private void createAlert(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Help");
    alert.setHeaderText(header);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private String getHelpText(String path) throws IOException {
    return String.join("\n", Files.readAllLines(Paths.get(path)));
  }
}
