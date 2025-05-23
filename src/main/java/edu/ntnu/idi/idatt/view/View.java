package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.engine.BoardGame;
import java.io.FileNotFoundException;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

/**
 * The View class is responsible for setting up the main layout of the application. It combines the
 * GameBoardView and PlayerView into a single scene.
 */
public class View extends BorderPane{


  /**
   * The constructor sets up the main layout of the application.
   *
   * @param stage The primary stage for this application.
   * @throws FileNotFoundException if an image file cannot be found.
   */
  public View(Stage stage) throws FileNotFoundException {
    BoardGame boardGame = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    Scene scene = new Scene(this, 1200, 800);

    GameBoardView gameBoardView = new GameBoardView(BoardGame.getBoard(), boardGame.getPlayers(),
        boardGame.getGameType());
    PlayerView playerView = new PlayerView();

    this.setCenter(gameBoardView);
    this.setBottom(playerView);

    scene.setRoot(this);
    stage.setTitle(BoardGame.getName());
    stage.setScene(scene);

  }
}

