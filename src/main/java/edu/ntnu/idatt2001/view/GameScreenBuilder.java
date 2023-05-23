package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.state.GameState;
import edu.ntnu.idatt2001.util.Widgets;
import java.io.File;
import java.util.function.Consumer;
import java.util.function.Consumer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Builder;
import javafx.util.Duration;


/**
 * The GameScreenBuilder class is responsible for constructing
 * the game screen UI based on the provided GameState model.
 * It uses JavaFX components and styles to create various *
 * elements such as labels, scroll panes, hyperlinks, and more.
 * The resulting UI is built within a BorderPane container.
 */
public class GameScreenBuilder implements Builder<Region> {
  private final GameState model;
  private final Consumer<Link> linkClickAction;

  /**
   * Constructs a new GameScreenBuilder with the given GameState model and linkClickAction consumer.
   *
   * @param model           The GameState model.
   * @param linkClickAction The consumer to handle link click actions.
   */
  public GameScreenBuilder(GameState model, Consumer<Link> linkClickAction) {
    this.model = model;
    this.linkClickAction = linkClickAction;
  }

  /**
   * Builds the main application screen as a Region.
   * Sets up the layout and binds the current screen to the model's current screen.
   *
   * @return a Region containing the application screen
   */
  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    model.storyTitleProperty().addListener((observable, oldValue, newValue) -> {
      results.setTop(Widgets.createLabel(newValue, "story-title-label"));
    });

    VBox mainContentBox = new VBox();
    mainContentBox.getChildren().addAll(createPlayerSprite(), createPassageBox(results));
    mainContentBox.setAlignment(Pos.BOTTOM_CENTER);
    results.setCenter(mainContentBox);
    results.setRight(createInventoryLinksColoumn());
    results.getStylesheets().add("/css/game.css");
    return results;
  }

  /**
   * Creates the passage box node within the game screen UI.
   *
   * @param view The BorderPane view.
   * @return The created passage box node.
   */
  private Node createPassageBox(BorderPane view) {
    ScrollPane results = new ScrollPane();
    results.setFitToWidth(true);
    results.setMinHeight(200);
    results.getStyleClass().add("passage-scroll-pane");
    VBox vbox = new VBox();
    vbox.setMinHeight(200);
    vbox.getStyleClass().add("passage-box");
    Label title = Widgets.createLabelWithBinding(model.titleProperty(), "passage-title-label");
    Label content = createContentLabel(view);
    vbox.getChildren().addAll(title, content);
    results.setContent(vbox);
    return results;
  }

  /**
   * Creates the player sprite node within the game screen UI.
   *
   * @return The created player sprite node.
   */
  private Node createPlayerSprite() {
    VBox results = new VBox();
    results.setAlignment(Pos.CENTER);
    results.getStyleClass().add("player-sprite-box");
    Label name = Widgets.createLabelWithBinding(model.nameProperty(), "player-name-label");
    name.setPadding(new Insets(5, 0, 5, 0));
    model.playerSpriteProperty().addListener((observable, oldValue, newValue) -> {
      results.getChildren().clear();
      ImageView playerSprite = new ImageView(model.getPlayerSprite());
      playerSprite.setPreserveRatio(true);
      playerSprite.setFitHeight(400);
      playerSprite.getStyleClass().add("player-sprite");
      results.getChildren().addAll(createHealthScoreBox(), playerSprite, name);
    });
    return results;
  }

  /**
   * Creates the health score box node within the player sprite node.
   *
   * @return The created health score box node.
   */
  private Node createHealthScoreBox() {
    HBox results = new HBox();
    results.setAlignment(Pos.CENTER);
    results.setSpacing(35);
    results.setPadding(new Insets(0, 10, 10, 10));
    Label hpPrompt = Widgets.createLabel("HP: ", "hp-prompt");
    Label hp = Widgets.createLabelWithBinding(model.healthProperty(), "hp-label");
    Label scorePrompt = Widgets.createLabel("Score: ", "score-prompt");
    Label score = Widgets.createLabelWithBinding(model.scoreProperty(), "score-label");
    results.getChildren().addAll(hpPrompt, hp, scorePrompt, score);
    return results;
  }

  /**
   * Creates the inventory links column node within the game screen UI.
   *
   * @return The created inventory links column node.
   */
  private Node createInventoryLinksColoumn() {
    VBox results = new VBox();
    results.setSpacing(20);
    results.getChildren().addAll(createInventoryGoldBox(), createLinksBox());
    return results;
  }

  /**
   * Creates the inventory gold box node within the inventory links column node.
   *
   * @return The created inventory gold box node.
   */
  private Node createInventoryGoldBox() {
    VBox results = new VBox();
    results.getStyleClass().add("inventory-gold-box");
    HBox goldBox = new HBox();
    Label goldPrompt = Widgets.createLabel("Gold: ", "gold-prompt");
    Label gold = Widgets.createLabelWithBinding(model.goldProperty(), "gold-label");
    Label inventoryLabel = Widgets.createLabel("Inventory: ", "inventory-prompt");
    ListView<String> inventory = new ListView<>(model.inventoryProperty());
    goldBox.getChildren().addAll(goldPrompt, gold);
    results.getChildren().addAll(inventoryLabel, inventory, goldBox);
    return results;
  }

  private Node createLinksBox() {
    VBox results = new VBox();
    results.setMinHeight(200);
    results.getStyleClass().add("links-box");
    model.linksProperty().addListener((observable, oldValue, newValue) -> {
      results.getChildren().clear();
      for (Link link : newValue) {
        results.getChildren().add(createHyperLink(link));
      }
    });
    results.setAlignment(Pos.CENTER_LEFT);
    return results;
  }

  /**
   * Creates a hyperlink node for the given link.
   *
   * @param link The Link object for the hyperlink.
   * @return The created hyperlink node.
   */
  private Node createHyperLink(Link link) {
    Hyperlink results = new Hyperlink(link.getText());
    results.getStyleClass().add("link");
    results.setOnAction(event -> {
      String clickFile = new File("src/main/resources/sound/cork-85200.mp3").toURI().toString();
      Media clickSound = new Media(clickFile);
      MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
      mediaPlayer.seek(Duration.ZERO);
      mediaPlayer.play();
      linkClickAction.accept(link);
    });
    return results;
  }


  /**
   * Creates the content label node within the passage box node.
   *
   * @param view The BorderPane view.
   * @return The created content label node.
   */
  private Label createContentLabel(BorderPane view) {
    Label results = new Label();
    results.getStyleClass().add("content");
    results.setWrapText(true);
    final Timeline[] timeline = new Timeline[1];

    model.contentProperty().addListener((observable, oldValue, newValue) -> {
      if (timeline[0] != null && timeline[0].getStatus() == Animation.Status.RUNNING) {
        timeline[0].stop();
      }
      results.setText("");
      timeline[0] = createContentTimeline(results, newValue);
      timeline[0].play();
    });

    view.setOnMouseClicked(event -> {
      if (timeline[0] != null && timeline[0].getStatus() == Animation.Status.RUNNING) {
        timeline[0].stop();
        results.setText(model.getContent());
      }
    });
    return results;
  }

  /**
   * Creates a timeline for animating the content label with the provided content.
   *
   * @param label   The content label node.
   * @param content The content string to animate.
   * @return The created timeline.
   */
  private Timeline createContentTimeline(Label label, String content) {
    final int[] index = new int[1];
    Timeline timeline = new Timeline();

    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), event -> {
      label.setText(label.getText() + model.getContent().charAt(index[0]));
      index[0]++;
    }));
    timeline.setCycleCount(content.length());

    return timeline;
  }
}