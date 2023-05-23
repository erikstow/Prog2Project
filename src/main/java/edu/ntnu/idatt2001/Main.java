package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.controller.ApplicationController;
import edu.ntnu.idatt2001.controller.CharacterScreenController;
import edu.ntnu.idatt2001.controller.GameController;
import edu.ntnu.idatt2001.controller.MenubarController;
import edu.ntnu.idatt2001.controller.SettingsController;
import edu.ntnu.idatt2001.controller.TitleScreenController;
import edu.ntnu.idatt2001.util.MusicManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * The main class that serves as the entry point for the application.
 * It initializes and configures the application's controllers and views,
 * sets up the scene, and handles the primary stage.
 */
public class Main extends Application {

  /**
   * The main method that launches the JavaFX application.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }


  /**
   * The start method of the JavaFX application.
   * It sets up the primary stage, initializes controllers and views,
   * and configures event handling.
   *
   * @param primaryStage the primary stage of the application
   */
  @Override
  public void start(Stage primaryStage) {

    TitleScreenController titleScreenController = new TitleScreenController();
    GameController gameController = new GameController();
    CharacterScreenController characterScreenController = new CharacterScreenController();
    SettingsController settingsController = new SettingsController();
    MenubarController menubarController = new MenubarController();
    ApplicationController applicationController = new ApplicationController(
        titleScreenController.getView(),
        gameController.getView(),
        characterScreenController.getView(),
        settingsController.getView(),
        menubarController.getView(),
        new MusicManager()
    );

    applicationController.addObserver(gameController);
    applicationController.addObserver(characterScreenController);
    applicationController.addObserver(settingsController);
    applicationController.addObserver(titleScreenController);

    titleScreenController.addObserver(applicationController);
    gameController.addObserver(applicationController);
    settingsController.addObserver(applicationController);
    characterScreenController.addObserver(applicationController);
    menubarController.addObserver(applicationController);

    Scene scene = new Scene(applicationController.getView(), 1024, 971);
    primaryStage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.F11) {
        primaryStage.setFullScreen(!primaryStage.isFullScreen());
      }
    });
    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
    primaryStage.show();
  }
}
