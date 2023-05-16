package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.model.game.Link;
import edu.ntnu.idatt2001.model.gui.GameModel;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
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
      createLabel(model.contentProperty())
    ));

    results.setRight(createPlayerBox());

    VBox linkBox = new VBox();
    model.linksProperty().addListener((observable, oldValue, newValue) -> {
      linkBox.getChildren().clear();
      for (Link link : newValue) {
        linkBox.getChildren().add(presentLink(link));
      }
    });

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
}

