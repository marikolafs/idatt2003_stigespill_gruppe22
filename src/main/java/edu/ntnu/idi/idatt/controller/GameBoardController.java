package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.view.GameBoardView;
import edu.ntnu.idi.idatt.view.LadderGameView;
import edu.ntnu.idi.idatt.view.PlayerView;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoardController {

  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());

  /**
   *Initializer method for GameBoardController
   *
   * @param stage the stage the controller is working on
   * @param sceneManager sceneManager responsible for switching between scenes
   * @param gameType the type of game being played
   * @throws FileNotFoundException if a file can not be found
   */
  public GameBoardController(Stage stage, SceneManager sceneManager, String gameType) throws FileNotFoundException {
    try{
      GameBoardView gameBoardView = new GameBoardView(game.getBoard(), game.getPlayers(), gameType);
      PlayerView playerView = new PlayerView();

      LadderGameView layout = new LadderGameView(gameBoardView, playerView);
      Scene gameScene = new Scene(layout, 1000, 800);
      sceneManager.switchScene(gameScene, "LadderGame");

      PlayerController playerController = new PlayerController(game.getDice(), playerView, gameType, stage);

      game.addObserver(gameBoardView);
      game.addObserver(playerView);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
