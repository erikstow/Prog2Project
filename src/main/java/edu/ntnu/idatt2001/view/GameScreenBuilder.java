package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.state.GameState;
import edu.ntnu.idatt2001.util.Widgets;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import javafx.util.Duration;

import java.util.function.Consumer;

public class GameScreenBuilder implements Builder<Region> {
  private final GameState model;
  private final Consumer<Link> linkClickAction;

  public GameScreenBuilder(GameState model, Consumer<Link> linkClickAction) {
    this.model = model;
    this.linkClickAction = linkClickAction;
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();
    model.storyTitleProperty().addListener((observable, oldValue, newValue) -> {
      results.setTop(Widgets.createLabelWithBinding(model.storyTitleProperty(), "story-title-label"));
    });
    
    VBox mainContentBox = new VBox();
    mainContentBox.getChildren().addAll(createPlayerSprite(), createPassageBox(results));
    
    results.setCenter(mainContentBox);
    results.setRight(createInventoryLinksColoumn());
    results.getStylesheets().add("game.css");
    return results;
  }

  private Node createPassageBox(BorderPane view) {
    ScrollPane results = new ScrollPane();
    results.setFitToWidth(true);
    results.setMinHeight(200);
    results.getStyleClass().add("passage-scroll-pane");
    VBox vbox  = new VBox();
    vbox.setMinHeight(200);
    vbox.getStyleClass().add("passage-box");
    Label title = Widgets.createLabelWithBinding(model.titleProperty(), "passage-title-label");
    Label content = createContentLabel(view);
    vbox.getChildren().addAll(title, content);
    results.setContent(vbox);
    return results;
  }

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
      results.getChildren().addAll(createHealthScoreBox(),playerSprite, name);
    });
    return results;
  }


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

  private Node createInventoryLinksColoumn() {
    VBox results = new VBox();
    results.setAlignment(Pos.BOTTOM_RIGHT);
    results.setSpacing(20);
    results.getChildren().addAll(createInventoryGoldBox(), createLinksBox());
    return results;
  }

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
    results.setAlignment(Pos.CENTER_LEFT);
    results.setMinHeight(200);
    results.getStyleClass().add("links-box");
    model.linksProperty().addListener((observable, oldValue, newValue) -> {
      results.getChildren().clear();
      for (Link link : newValue) {
        results.getChildren().add(createHyperLink(link));
      }
    });
    return results;
  }

  private Node createLabel(StringProperty property) {
    Label results = new Label();
    results.textProperty().bind(property);
    return results;
  }

  private Node createHyperLink(Link link) {
    Hyperlink results = new Hyperlink(link.getText());
    results.getStyleClass().add("link");
    results.setOnAction(event -> linkClickAction.accept(link));
    return results;
  }

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