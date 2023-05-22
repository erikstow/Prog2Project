package edu.ntnu.idatt2001.view.characterScreen;

import edu.ntnu.idatt2001.model.goals.Goal;
import edu.ntnu.idatt2001.model.state.CharacterScreenState;
import edu.ntnu.idatt2001.util.Widgets;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CharacterGoalsScreenBuilder implements Builder<Region> {

  private final CharacterScreenState model;
  private final Runnable addGoal;
  private final Runnable undoGoal;

  public CharacterGoalsScreenBuilder(CharacterScreenState model,
                                     Runnable addGoal,
                                     Runnable undoGoal) {
    this.model = model;
    this.addGoal = addGoal;
    this.undoGoal = undoGoal;
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    Label goals = Widgets.createLabel("Define your goals", "define-goals-label");
    results.setTop(goals);
    results.setCenter(createGoalsView());
    results.setLeft(createGoalAdderBox());
    results.getStylesheets().add("goalscreen.css");
    results.setAlignment(goals, Pos.CENTER);
    return results;
  }

  private Node createGoalsView() {
    VBox results = new VBox();
    ListView<Goal> goalList = new ListView<>();
    goalList.setItems(model.goals());
    goalList.getStyleClass().add("goals-list-view");
    results.getStyleClass().add("goals-view-box");
    Button undoGoalButton = Widgets.createButton("Undo last Goal", undoGoal, "undo-goal-button");
    undoGoalButton.disableProperty().setValue(true);
    goalList.getItems().addListener((InvalidationListener) change -> {
      undoGoalButton.disableProperty().setValue(goalList.getItems().isEmpty());
    });
    results.getChildren().addAll(goalList, undoGoalButton);
    return results;
  }

  private Node createGoalAdderBox() {
    VBox results = new VBox(
        Widgets.createLabel("Choose goals to add", "goal-add-prompt")
    );

    ComboBox<String> goalType = createGoalTypeComboBox();
    goalType.getStyleClass().add("goal-type-combobox");
    results.getChildren().add(goalType);

    TextField goalValue = createGoalValueField(goalType);
    results.getChildren().add(goalValue);

    Button addGoalButton = Widgets.createButton("Add Goal", () -> {
      addGoal.run();
      goalValue.setText("");
    }, "add-goal-button");

    addGoalButton
        .disableProperty()
        .bind(goalValue.textProperty().isEmpty().or(goalType.valueProperty().isNull()));
    addGoalButton.setDefaultButton(true);
    results.getChildren().add(addGoalButton);

    results.getStyleClass().add("goal-adder-box");
    return results;
  }

  private TextField createGoalValueField(ComboBox<String> goalType) {
    TextField results = new TextField();
    results.textProperty().bindBidirectional(model.goalValue());
    results.disableProperty().bind(goalType.valueProperty().isNull());
    results.getStyleClass().add("goal-value-text-field");
    goalType.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        results.setText("");
        if (newValue.equals("Inventory")) {
          results.setPromptText("Comma-separated list of items...");
          results.setTextFormatter(null);
        } else {
          results.setPromptText("An integer...");
          results.setTextFormatter(getPositiveIntegerFormatter());
        }
      } else {
        results.setText("");
      }
    });

    results.textProperty().addListener((observable, oldValue, newValue) -> {
      boolean newValueValid = !newValue.isEmpty() && checkIntegerOverflow(newValue);
      boolean notInventory = !goalType.getValue().equals("Inventory");
      if (newValueValid && notInventory) {
        results.setText(oldValue);
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

  private boolean checkIntegerOverflow(String value) throws NumberFormatException {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return true;
    }
    return false;
  }
}
