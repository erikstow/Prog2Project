package edu.ntnu.idatt2001.controller;

import edu.ntnu.idatt2001.model.events.ControllerEvent;
import edu.ntnu.idatt2001.model.events.DataUpdateEvent;
import edu.ntnu.idatt2001.view.MenubarScreenBuilder;
import javafx.scene.layout.Region;

/**
 * The MenubarController class extends ontroller and
 * provides a user interface for interacting with the menu bar.
 * It is responsible for handling actions that occur in
 * the menu bar and relaying them as DataUpdateEvents.
 */
public class MenubarController extends Controller {
  private final Region view;

  /**
   * Constructs a new MenubarController and initializes its view by using the MenubarScreenBuilder.
   */
  public MenubarController() {
    view = new MenubarScreenBuilder(this::buttonAction).build();
  }

  /**
   * Performs the action associated with a button click on the menu bar.
   *
   * @param action The action to be performed.
   */
  private void buttonAction(String action) {
    update(new DataUpdateEvent(action, null));
  }

  /**
   * The onUpdate method throws an nsupportedOperationException
   * because the MenubarController does not support updates.
   *
   * @param event The ontrollerEvent to be processed.
   * @throws UnsupportedOperationException because the MenubarController does not support updates.
   */
  @Override
  public void onUpdate(ControllerEvent event) {
    throw new UnsupportedOperationException("Can't call onUpdate on MenubarController");
  }

  /**
   * Returns the view managed by this controller.
   *
   * @return The view managed by this controller.
   */
  public Region getView() {
    return view;
  }
}
