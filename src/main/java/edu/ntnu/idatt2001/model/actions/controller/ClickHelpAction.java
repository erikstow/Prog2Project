package edu.ntnu.idatt2001.model.actions.controller;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Alert;

/**
 * This class is responsible for executing the 'Help' action in different game screens.
 * It implements the ControllerAction interface, thereby overriding the execute method.
 * It creates a help alert based on the current screen type and reads help text from the
 * respective file paths for each screen type.
 */
public class ClickHelpAction implements ControllerAction {
  private static final String HELP_FILE_DIRECTORY = "src/main/resources/helpText/";
  private static final String TITLE_HELP_FILE = "titleHelp.txt";
  private static final String CREATION_HELP_FILE = "creationHelp.txt";
  private static final String SETTING_HELP_FILE = "settingHelp.txt";
  private static final String GAME_HELP_FILE = "gameHelp.txt";

  /**
   * Executes the 'Help' action based on the current screen of the game.
   * The respective help text is fetched and a help alert is shown to the user.
   *
   * @param event      the event that triggers this action
   * @param controller the application controller
   * @param state      the current state of the application
   * @throws Exception if an error occurs during execution
   */
  @Override
  public void execute(ControllerEvent event,
                      ApplicationController controller,
                      ApplicationState state)
      throws Exception {
    switch (state.getCurrentScreen()) {
      case TITLE_SCREEN ->
        createAlert("Title screen help", getHelpText(HELP_FILE_DIRECTORY + TITLE_HELP_FILE));
      case CREATION_SCREEN ->
        createAlert("Character creation help",
          getHelpText(HELP_FILE_DIRECTORY + CREATION_HELP_FILE));
      case SETTINGS_SCREEN ->
        createAlert("Setting screen help", getHelpText(HELP_FILE_DIRECTORY + SETTING_HELP_FILE));
      case GAME_SCREEN ->
        createAlert("Game help", getHelpText(HELP_FILE_DIRECTORY + GAME_HELP_FILE));
    }
  }

  /**
   * Creates an alert with the provided header and message.
   *
   * @param header the header text of the alert
   * @param message the message to be shown in the alert
   */
  private void createAlert(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.getDialogPane().getStylesheets().add("/css/alert.css");
    alert.setTitle("Help");
    alert.setHeaderText(header);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Reads the text from a file located at the provided path.
   * The text lines are joined with newline characters.
   *
   * @param path the file path from where the text is read
   * @return a String containing the text read from the file
   * @throws IOException if an error occurs during file reading
   */
  private String getHelpText(String path) throws IOException {
    return String.join("\n", Files.readAllLines(Paths.get(path)));
  }
}
