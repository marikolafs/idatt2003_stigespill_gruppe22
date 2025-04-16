package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.PlayerFiles;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.view.LadderGameView;
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BoardGameApp extends Application {

  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  Board board = new Board();
  BoardGameFactory boardGameFactory = new BoardGameFactory();
  private static List<Player> players;

  /*
  Mål for påsken
  - fullføre designmønstre
  - sette opp basic gui
  - ferdig gjøre hold action
  - fikse example board filen
  - evt annet?
   */

  @Override
  public void start(Stage stage) throws Exception {
    BoardGameFactory boardGameFactory = new BoardGameFactory();
    boardGameFactory.createLadderGame90();

    game.addPlayer(new Player("player1", game, "piece1"));
    game.addPlayer(new Player("player2", game, "piece2"));

    game.getPlayers().getFirst().move(2);

    LadderGameView ladderGameView = new LadderGameView(stage);
    stage.setFullScreen(true);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
