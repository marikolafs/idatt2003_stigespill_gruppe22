package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.SceneManager;
import edu.ntnu.idi.idatt.controller.WelcomeController;
import edu.ntnu.idi.idatt.io.PlayerFiles;
import edu.ntnu.idi.idatt.controller.PlayerController;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.view.GameBoardView;
import edu.ntnu.idi.idatt.view.LadderGameView;
import edu.ntnu.idi.idatt.view.PlayerSetupView;
import edu.ntnu.idi.idatt.view.PlayerView;
import edu.ntnu.idi.idatt.view.View;
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

    /*
    boardGameFactory.createLadderGame90();

    game.addPlayer(new Player("player1", game, "piece1"));
    game.addPlayer(new Player("player2", game, "piece2"));

    game.getPlayers().getFirst().move(2);

    game.play();

     */

    //View view = new View(stage);
    //GameBoardView gameBoardView = view.getGameBoardView();
    //PlayerView playerView = view.getPlayerView();
    //WelcomeView welcomeView = new WelcomeView();

    //LadderGameView ladderGameView = new  LadderGameView(gameBoardView, playerView);
    //Scene scene = new Scene(welcomeView, 1000, 800);
    //stage.setScene(scene);
    //stage.setFullScreen(true);
    //stage.show();

    //PlayerController playerController = new PlayerController(game.getDice(), playerView);
    //observable.addObserver(playerView);
    //observable.addObserver(gameBoardView);
  }



  public static void main(String[] args) {
    launch(args);
  }
}
