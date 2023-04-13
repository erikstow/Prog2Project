package edu.ntnu.idatt2001.views;

import edu.ntnu.idatt2001.models.GameModel;
import edu.ntnu.idatt2001.models.TitleScreenModel;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class TitleScreenView extends VBox {
  private final TitleScreenModel model;
  private final Runnable actionHandler;
  private final List<String> storyTitles;

  public TitleScreenView(TitleScreenModel model, List storyTitles, Runnable action) {
    this.model = model;
    this.actionHandler = action;
    this.storyTitles = storyTitles;
  }


}
