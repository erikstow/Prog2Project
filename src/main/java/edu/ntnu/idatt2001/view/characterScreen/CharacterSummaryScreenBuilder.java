package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.gui.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterSummaryScreenBuilder implements Builder<Region> {

  private final CharacterScreenModel model;

  public CharacterSummaryScreenBuilder(CharacterScreenModel model) {
    this.model = model;
  }

  @Override
  public Region build() {
    TabPane results = new TabPane();
    Tab infoTab = new Tab("Player Info", createPlayerInfoBox());
    Tab difficultyTab = new Tab("Difficulty", createDifficultyBox());
    Tab goalsTab = new Tab("Goals", createGoalsBox());
    results.getTabs().addAll(infoTab, difficultyTab, goalsTab);
    return results;
  }
  
  private Node createPlayerInfoBox() {
    VBox results = new VBox();
    Label name = Widgets.createLabel("Name: " + model.getName(), "");
    Label appearence = Widgets.createLabel("Appearence: " + model.getAppearence(), "");
    results.getChildren().addAll(name, appearence);
    return results;
  }

  private Node createDifficultyBox() {
    VBox results = new VBox();
    Label difficulty = Widgets.createLabel("Difficulty: " + model.getDifficulty(), "");
    results.getChildren().addAll(difficulty);
    return results;
  }
  
  private Node createGoalsBox() {
    VBox results = new VBox();
    Label goals = Widgets.createLabel("Goals: " + model.getGoals(), "");
    results.getChildren().addAll(goals);
    return results;
  }

 

}
