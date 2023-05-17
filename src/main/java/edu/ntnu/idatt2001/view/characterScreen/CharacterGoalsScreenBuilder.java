package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterGoalsScreenBuilder implements Builder<Region> {
  
  private final CharacterScreenModel model;
  private final Runnable addGoal;
  private final Runnable undoGoal;

  public CharacterGoalsScreenBuilder(CharacterScreenModel model, Runnable addGoal, Runnable undoGoal) {
    this.model = model;
    this.addGoal = addGoal;
    this.undoGoal = undoGoal;
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    Label goals = Widgets.createLabel("Define your goals", "");
    results.setTop(goals);
    results.setRight(createGoalsView());
    results.setLeft(createGoalAdderBox());
    return results;
  }

  private Node createGoalsView() {
    VBox results = new VBox();
    Label goals = Widgets.createLabel("Your Goals", "");
    ListView<Goal> goalList = new ListView<>();
    goalList.setItems(model.goals());
    Button undoGoalButton = Widgets.createButton("Undo last Goal", undoGoal, "undo-goal-button");
    results.getChildren().addAll(goals, goalList, undoGoalButton);
    return results;
  }

  private Node createGoalAdderBox() {
    VBox results = new VBox();
    Label goalAddPrompt = Widgets.createLabel("Choose goals to add", "");
    TextField goalValue = new TextField();
    goalValue.textProperty().bindBidirectional(model.goalValue());
    ComboBox<String> goalType = new ComboBox<>();
    goalType.getItems().addAll("Health", "Gold", "Score", "Inventory");
    goalType.valueProperty().bindBidirectional(model.goalType());
    goalType.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.equals("Health")) {
        goalValue.setPromptText("An integer...");
      } else if (newValue.equals("Gold")) {
        goalValue.setPromptText("An integer...");
      } else if (newValue.equals("Score")) {
        goalValue.setPromptText("An integer...");
      } else if (newValue.equals("Inventory")) {
        goalValue.setPromptText("Comma-separated list of items...");
      }
    });
    Button addGoalButton = Widgets.createButton("Add Goal", addGoal, "add-goal-button");
    results.getChildren().addAll(goalAddPrompt, goalType, goalValue, addGoalButton);
    return results;
  }
}

