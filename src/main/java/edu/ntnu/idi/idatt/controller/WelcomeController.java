package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.SceneManager;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.view.WelcomeView;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

  private String gameType;
  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  private SceneManager sceneManager;
  private WelcomeView welcomeView;
  private Scene welcomeScene;

  public WelcomeController(Stage stage, SceneManager sceneManager) throws FileNotFoundException {
    this.sceneManager = sceneManager;
    this.welcomeView = new WelcomeView();
    this.welcomeScene = new Scene(welcomeView.getView(), 400, 300);

    welcomeView.getLadder90Button().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "LadderGame90");
      game.setGameType("LadderGame90");
    });

    welcomeView.getLadder45Button().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "LadderGame45");
      game.setGameType("LadderGame45");
    });

    welcomeView.getLadder90PlusButton().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "LadderGame90Plus");
      game.setGameType("LadderGame90Plus");
    });
      showWelcomeScene();
      setupEventHandlers(stage);
      sceneManager.addScene("Welcome", welcomeScene);
  }

  private void setupEventHandlers(Stage stage) {
    welcomeView.getLadder90Button().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "LadderGame90");
    });

    welcomeView.getLudoButton().setOnAction(event -> {
      new PlayerSetupController(stage, sceneManager, "Ludo");
      game.setGameType("Ludo");
    });
  }

  public void showWelcomeScene() {
    sceneManager.switchScene(welcomeScene, "Welcome");
  }
}