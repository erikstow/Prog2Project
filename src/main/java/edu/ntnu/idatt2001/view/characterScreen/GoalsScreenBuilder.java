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

/**
 * This class provides a builder for the character goals screen in the game.
 * The screen displays the character's goals and *
 * provides an interface for adding new goals and undoing the last goal.
 */
public class GoalsScreenBuilder implements Builder<Region> {

  private final CharacterScreenState state;
  private final Runnable addGoal;
  private final Runnable undoGoal;

  /**
   * Constructor for CharacterGoalsScreenBuilder.
   *
   * @param state the CharacterScreenState model containing the current character state
   * @param addGoal a Runnable to be executed when a goal is added
   * @param undoGoal a Runnable to be executed to undo the last goal
   */
  public GoalsScreenBuilder(CharacterScreenState state,
                            Runnable addGoal,
                            Runnable undoGoal) {
    this.state = state;
    this.addGoal = addGoal;
    this.undoGoal = undoGoal;
  }

  /**
   * Builds the character goals screen as a Region.
   * Sets up the layout and bindings for all UI elements on the screen.
   *
   * @return a Region containing the character goals screen
   */
  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    Label goals = Widgets.createLabel("Achievable Gaols Only", "define-goals-label");
    results.setTop(goals);
    results.setCenter(createGoalsView());
    results.setLeft(createGoalAdderBox());
    results.getStylesheets().add("/css/goalscreen.css");
    results.setAlignment(goals, Pos.CENTER);
    return results;
  }

  /**
   * Creates a view with a list of the character's goals and an undo button.
   *
   * @return a Node representing the view
   */
  private Node createGoalsView() {
    VBox results = new VBox();
    ListView<Goal> goalList = new ListView<>();
    goalList.setItems(state.goals());
    goalList.getStyleClass().add("goals-list-view");
    results.getStyleClass().add("goals-view-box");
    Button undoGoalButton = Widgets.createButton("Undo", undoGoal, "undo-goal-button");
    undoGoalButton.disableProperty().setValue(true);
    goalList.getItems().addListener((InvalidationListener) change -> {
      undoGoalButton.disableProperty().setValue(goalList.getItems().isEmpty());
    });
    results.getChildren().addAll(goalList, undoGoalButton);
    return results;
  }

  /**
   * Creates a box for adding new goals.
   * Includes a dropdown for goal type, a text field for goal value, and an add button.
   *
   * @return a Node representing the box
   */
  private Node createGoalAdderBox() {
    VBox results = new VBox(
        Widgets.createLabel("Add goals", "goal-add-prompt")
    );

    ComboBox<String> goalType = createGoalTypeComboBox();
    goalType.getStyleClass().add("goal-type-combobox");
    results.getChildren().add(goalType);

    TextField goalValue = createGoalValueField(goalType);
    results.getChildren().add(goalValue);

    Button addGoalButton = Widgets.createButton("Add", () -> {
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

  /**
   * Creates a text field for inputting the value of the goal to be added.
   * The field is bound to the model's goalValue *
   * property and is disabled if no goal type is selected.
   *
   * @param goalType the ComboBox to choose the goal type
   * @return a TextField for inputting the goal value
   */
  private TextField createGoalValueField(ComboBox<String> goalType) {
    TextField results = new TextField();
    results.textProperty().bindBidirectional(state.goalValue());
    results.disableProperty().bind(goalType.valueProperty().isNull());
    results.getStyleClass().add("goal-value-text-field");
    goalType.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        results.setText("");
        if (newValue.equals("Inventory")) {
          results.setPromptText("An item...");
          results.setTextFormatter(null);
        } else {
          results.setPromptText("A number...");
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

  /**
   * Creates a combo box for selecting the type of the goal to be added.
   * The selected goal type is bound bidirectionally to the model's goalType property.
   *
   * @return a ComboBox for selecting the goal type
   */
  private ComboBox<String> createGoalTypeComboBox() {
    ComboBox<String> results = new ComboBox<>();

    results.setPromptText("Choose goal type");
    results.getItems().addAll("Health", "Gold", "Score", "Inventory");
    results.valueProperty().bindBidirectional(state.goalType());

    return results;
  }

  /**
   * Creates a text formatter for ensuring that only *
   * positive integers are allowed in the text field.
   *
   * @return a TextFormatter that filters out non-integer inputs
   */
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

  /**
   * Checks whether the input string can be converted to an integer without causing an overflow.
   *
   * @param value the string to check
   * @return true if the string would cause an integer overflow, false otherwise
   * @throws NumberFormatException if the string cannot be parsed to an integer
   */
  private boolean checkIntegerOverflow(String value) throws NumberFormatException {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return true;
    }
    return false;
  }
}
