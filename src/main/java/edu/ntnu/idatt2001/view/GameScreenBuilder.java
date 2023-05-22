package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.gui.GameModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import javafx.util.Duration;

import java.util.function.Consumer;

public class GameScreenBuilder implements Builder<Region> {
  private final GameModel model;
  private final Consumer<Link> linkClickAction;

  public GameScreenBuilder(GameModel model, Consumer<Link> linkClickAction) {
    this.model = model;
    this.linkClickAction = linkClickAction;
  }

  @Override
  public Region build() {
    BorderPane results = new BorderPane();

    results.setCenter(new VBox(
      createLabel(model.titleProperty()),
      createContentLabel(results)
    ));

    results.setRight(createPlayerBox());

    VBox linkBox = new VBox();
    model.linksProperty().addListener((observable, oldValue, newValue) -> {
      linkBox.getChildren().clear();
      for (Link link : newValue) {
        linkBox.getChildren().add(presentLink(link));
      }
    });
    results.getStylesheets().add("game.css");
    results.setBottom(linkBox);

    return results;
  }

  private Node createPlayerBox() {
    return new VBox(
      createLabel(model.healthProperty()),
      createLabel(model.goldProperty()),
      createLabel(model.scoreProperty())
      // createLabel(model.inventoryProperty())
    );
  }

  private Node createLabel(StringProperty property) {
    Label results = new Label();
    results.textProperty().bind(property);
    return results;
  }

  private Node presentLink(Link link) {
    Hyperlink hyperlink = new Hyperlink(link.getText());
    hyperlink.setOnAction(event -> linkClickAction.accept(link));

    return hyperlink;
  }

  private Label createContentLabel(BorderPane view) {
    Label results = new Label();
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