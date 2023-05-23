package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.screentype.CharacterScreenType;
import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Builder;

/**
 * This class provides a builder for the character creation screen in the game.
 * The screen serves as the hub for character customization,
 * including setting character info, difficulty, and goals.
 */
public class CharacterScreenBuilder implements Builder<Region> {
  private final CharacterScreenState state;
  private final Runnable backAction;
  private final Runnable nextAction;
  private final Region infoView;
  private final Region difficultyView;
  private final Region goalsView;
  private final Region summaryView;

  /**
   * Constructor for CharacterScreenBuilder.
   *
   * @param state          the CharacterScreenState model containing the current
   *                       character state
   * @param backAction     a Runnable to be executed when going back to the
   *                       previous screen
   * @param nextAction     a Runnable to be executed when advancing to the next
   *                       screen
   * @param infoView       the view for setting character info
   * @param difficultyView the view for setting game difficulty
   * @param goalsView      the view for setting character goals
   * @param summaryView    the view for displaying a summary of the character's
   *                       current state
   */
  public CharacterScreenBuilder(
      CharacterScreenState state, Runnable backAction, Runnable nextAction, Region infoView,
      Region difficultyView, Region goalsView, Region summaryView) {
    this.difficultyView = difficultyView;
    this.summaryView = summaryView;
    this.infoView = infoView;
    this.goalsView = goalsView;

    this.state = state;
    this.backAction = backAction;
    this.nextAction = nextAction;
  }

  /**
   * Builds the character screen as a Region.
   * Sets up the layout and bindings for all UI elements on the screen.
   *
   * @return a Region containing the character screen
   */
  @Override
  public Region build() {
    BorderPane results = new BorderPane();

    Label title = Widgets.createLabel("Choose your Destiny", "label-title");
    results.setTop(title);

    Node buttonBarNode = createNextBackButtonBar();
    results.setBottom(buttonBarNode);

    state.currentScreen().addListener(
        (observable, oldValue, newValue) -> results.setCenter(getScreen(newValue)));

    results.setCenter(getScreen(state.getCurrentScreen()));

    results.getStylesheets().add("/css/characterscreen.css");
    results.setAlignment(title, Pos.CENTER);
    results.setAlignment(buttonBarNode, Pos.CENTER);
    results.setAlignment(getScreen(state.getCurrentScreen()), Pos.CENTER);

    return results;
  }

  /**
   * Creates a bar with back and next buttons.
   * The buttons are bound to the backAction and nextAction Runnables
   * respectively,
   * and their text changes depending on the current screen.
   *
   * @return a Node representing the button bar
   */
  private Node createNextBackButtonBar() {
    HBox results = new HBox();
    results.setAlignment(Pos.CENTER);
    results.getStyleClass().add("character-screen-button-hbox");
    Button next = Widgets.createButton("Next", nextAction, "character-screen-button");
    next.disableProperty().bind(state.nextAllowed().not());
    Button back = Widgets.createButton("Title", backAction, "character-screen-button");
    state.currentScreen().addListener((observable, oldValue, newValue) -> {
      switch (newValue) {
        case INFO_SCREEN -> {
          back.setText("Title");
          next.setText("Next");
        }
        case SUMMARY_SCREEN -> next.setText("Start!");
        default -> {
          next.setText("Next");
          back.setText("Back");
        }
      }
    });
    results.getChildren().addAll(back, next);
    return results;
  }

  /**
   * Returns the appropriate view for the given screen type.
   *
   * @param screenType the type of screen for which to get the view
   * @return the view for the given screen type
   */
  private Region getScreen(CharacterScreenType screenType) {
    return switch (screenType) {
      case INFO_SCREEN -> infoView;
      case DIFFICULTY_SCREEN -> difficultyView;
      case GOALS_SCREEN -> goalsView;
      case SUMMARY_SCREEN -> summaryView;
    };
  }
}