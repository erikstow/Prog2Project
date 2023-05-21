package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.ApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

    @Override
    public void start(Stage primaryStage) {
      Scene scene = new Scene(new ApplicationController().getView(), 1024, 971);
      primaryStage.setScene(scene);
      scene.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.F11) {
          primaryStage.setFullScreen(!primaryStage.isFullScreen());
        }
      });
      primaryStage.setFullScreen(true);
      primaryStage.show();
    }
}
