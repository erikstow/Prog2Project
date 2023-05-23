package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.actions.controller.ControllerAction;
import edu.ntnu.idatt2001.model.actions.controller.ControllerActionFactory;
import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.model.screentype.ApplicationScreenType;
import edu.ntnu.idatt2001.model.state.ApplicationState;
import edu.ntnu.idatt2001.util.MusicManager;
import edu.ntnu.idatt2001.view.ApplicationScreenBuilder;
import javafx.scene.layout.Region;

/**
 * The ApplicationController class extends Controller and
 * manages different aspects of the application like the current view,
 * state, music and events. It is responible for updating other
 * controllers when needed.
 */
public class ApplicationController extends Controller {
  private final Region view;
  private final ApplicationState state;
  private final ControllerActionFactory actionFactory;
  private final MusicManager musicManager;

  /**
   * ApplicationController constructor initializing application state, view,
   * controller actions, and music manager.
   *
   * @param titleView     the title view region
   * @param gameView      the game view region
   * @param characterView the character view region
   * @param settingsView  the settings view region
   * @param menubarView   the menubar view region
   * @param musicManager  the music manager for handling application music
   */
  public ApplicationController(Region titleView,
                               Region gameView,
                               Region characterView,
                               Region settingsView,
                               Region menubarView,
                               MusicManager musicManager) {
    state = new ApplicationState();
    state.setCurrentScreen(ApplicationScreenType.TITLE_SCREEN);

    view = new ApplicationScreenBuilder(state,
      titleView,
      gameView,
      characterView,
      settingsView,
      menubarView).build();

    actionFactory = new ControllerActionFactory();

    this.musicManager = musicManager;
    musicManager.playTrack("title");
  }

  /**
   * Handles controller events. If the event key matches a controller action,
   * the action is executed.
   * If an exception occurs during the execution of an action,
   * an error event is created and the method calls itself
   * recursively with the error event.
   *
   * @param event the controller event
   */
  @Override
  public void onUpdate(ControllerEvent event) {
    String key = event.getKey();
    ControllerAction action = actionFactory.createAction(key);
    if (action != null) {
      try {
        action.execute(event, this, state);
      } catch (Exception e) {
        onUpdate(new DataUpdateEvent("error", e));
      }
    } else {
      onUpdate(new DataUpdateEvent("error", new IllegalArgumentException("Unknown key: " + key)));
    }
  }

  /**
   * Returns the MusicManager instance.
   *
   * @return the music manager
   */
  public MusicManager getMusicManager() {
    return musicManager;
  }

  /**
   * Returns the view Region.
   *
   * @return the current view
   */
  public Region getView() {
    return view;
  }
}
