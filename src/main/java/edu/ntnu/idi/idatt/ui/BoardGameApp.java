package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.factory.BoardGameFactory;
import edu.ntnu.idi.idatt.controller.SceneManager;
import edu.ntnu.idi.idatt.controller.WelcomeController;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.view.WelcomeView;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardGameApp extends Application {

  private Stage primaryStage;
  private Scene scene;

  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  Board board = new Board();
  BoardGameFactory boardGameFactory = new BoardGameFactory();
  private static List<Player> players;
  private Observable observable = game;
  private WelcomeView welcomeView;

  @Override
  public void start(Stage stage) throws Exception {
    this.welcomeView = new WelcomeView();
    this.primaryStage = stage;
    SceneManager sceneManager = SceneManager.getInstance(primaryStage);
    Scene welcomeScene = new Scene(welcomeView.getView(), 400, 300);
    sceneManager.addScene("Welcome", welcomeScene);

    new WelcomeController(primaryStage, sceneManager);

    primaryStage.show();
  }



  public static void main(String[] args) {
    launch(args);
  }
}
