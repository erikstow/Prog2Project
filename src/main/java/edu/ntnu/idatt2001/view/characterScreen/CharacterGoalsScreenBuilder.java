package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.gui.characterScreenModel.CharacterScreenModel;
import edu.ntnu.idatt2001.util.widgets.Widgets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
    ComboBox<String> goalType = createGoalTypeComboBox();
    TextField goalValue = createGoalValueField(goalType);

    Button addGoalButton = Widgets.createButton("Add Goal", addGoal, "add-goal-button");
    addGoalButton.disableProperty().bind(goalValue.textProperty().isEmpty().or(goalType.valueProperty().isNull()));
    results.getChildren().addAll(goalAddPrompt, goalType, goalValue, addGoalButton);
    return results;
  }

  private TextField createGoalValueField(ComboBox<String> goalType) {
    TextField results = new TextField();
    results.textProperty().bindBidirectional(model.goalValue());
    results.disableProperty().bind(goalType.valueProperty().isNull());

    goalType.valueProperty().addListener((observable, oldValue, newValue) -> {
      results.setText("");
      if (newValue.equals("Inventory")) {
        results.setPromptText("Comma-separated list of items...");
        results.setTextFormatter(null);
      } else {
        results.setPromptText("An integer...");
        results.setTextFormatter(getPositiveIntegerFormatter());
      }
    });

    return results;
  }

  private ComboBox<String> createGoalTypeComboBox() {
    ComboBox<String> results = new ComboBox<>();

    results.setPromptText("Choose goal type");
    results.getItems().addAll("Health", "Gold", "Score", "Inventory");
    results.valueProperty().bindBidirectional(model.goalType());

    return results;
  }

  private TextFormatter<String> getPositiveIntegerFormatter() {
    UnaryOperator<TextFormatter.Change> filter = change -> {
      String newText = change.getControlNewText();
      if (Pattern.matches("\\d*", newText)) {
        return change;
      }
      return null;
    };
    return new TextFormatter<>(filter);
  }

}
