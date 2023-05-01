package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

    @Override
    public void start(Stage primaryStage) {
      Scene scene = new Scene(new GameController().getView(), 500, 200);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
}
