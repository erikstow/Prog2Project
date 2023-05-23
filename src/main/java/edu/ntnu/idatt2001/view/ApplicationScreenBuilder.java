package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

/**
 * This class provides a builder for the main application screen in the game.
 * The screen can display various sub-views such as the title,
 * game, character, and settings screens.
 */
public class ApplicationScreenBuilder implements Builder<Region> {
  private final ApplicationState state;
  private final Region titleView;
  private final Region gameView;
  private final Region characterView;
  private final Region settingsView;
  private final Region menubarView;

  /**
   * Constructor for ApplicationScreenBuilder.
   *
   * @param state the ApplicationState model containing the current application state
   * @param titleView the Region to display as the title screen
   * @param gameView the Region to display as the game screen
   * @param characterView the Region to display as the character screen
   * @param settingsView the Region to display as the settings screen
   * @param menubarView the Region to display as the menu bar
   */
  public ApplicationScreenBuilder(ApplicationState state,
                                  Region titleView,
                                  Region gameView,
                                  Region characterView,
                                  Region settingsView,
                                  Region menubarView) {
    this.state = state;
    this.titleView = titleView;
    this.gameView = gameView;
    this.characterView = characterView;
    this.settingsView = settingsView;
    this.menubarView = menubarView;
  }

  /**
   * Builds the main application screen as a Region.
   * Sets up the layout and binds the current screen to the model's current screen.
   *
   * @return a Region containing the application screen
   */
  public Region build() {
    BorderPane results = new BorderPane();

    results.getStylesheets().add("application.css");
    results.getStyleClass().add("app-pane");
    results.setTop(menubarView);

    results.setBottom(Widgets.createLabel("All Rights Reserved ©", "bottom-text"));
    results.setCenter(getScreen(state.getCurrentScreen()));
    results.setAlignment(results.getTop(), Pos.TOP_RIGHT);
    results.setCenter(getScreen(ApplicationScreenType.TITLE_SCREEN));
    state.currentScreenProperty()
        .addListener((observable, oldValue, newValue) -> results.setCenter(getScreen(newValue)));

    return results;
  }

  /**
   * Retrieves the appropriate screen based on the provided ApplicationScreenType.
   *
   * @param screen the type of screen to retrieve
   * @return the Region corresponding to the provided screen type
   */
  private Region getScreen(ApplicationScreenType screen) {
    return switch (screen) {
      case TITLE_SCREEN -> titleView;
      case CREATION_SCREEN -> characterView;
      case SETTINGS_SCREEN -> settingsView;
      case GAME_SCREEN -> gameView;
    };
  }
}