package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.SceneManager;
import edu.ntnu.idi.idatt.view.WelcomeView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomeController {

  private SceneManager sceneManager;

  public WelcomeController(Stage stage, SceneManager sceneManager) {
    WelcomeView welcomeView = new WelcomeView();
    Scene welcomeScene = new Scene(welcomeView.getView(), 400, 300);

    welcomeView.getLadderButton().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "LadderGame");
    });

    welcomeView.getLudoButton().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "Ludo");
    });

    sceneManager.switchScene(welcomeScene, "Welcome");
  }

}
