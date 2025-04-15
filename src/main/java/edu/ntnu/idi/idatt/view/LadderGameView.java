package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LadderGameView {

  BoardGameFactory boardGameFactory = new BoardGameFactory();
  BoardGame game = BoardGame.getInstance(BoardGame.getName(),BoardGame.getDescription());
  Board board =  game.getBoard();
  Map<Integer, Tile> tiles = board.getTiles();

  public LadderGameView(Stage stage) throws Exception {
    BorderPane layout = new BorderPane();
    Scene scene = new Scene(layout, 300, 300, Color.WHITE);

    GridPane gridPane = new GridPane();

    for (Tile  tile : tiles.values()) {
      StackPane tilePane = new StackPane();
      Label label = new Label("tile: " + tile.getTileId());
      Rectangle r = new Rectangle();
      r.setX(tile.getX());
      r.setY(tile.getY());
      r.setWidth(50);
      r.setHeight(50);
      if(tile.getTileId() % 2 == 0){
        r.setFill(Color.BEIGE);
      } else {
        r.setFill(Color.OLIVE);
      }
      tilePane.getChildren().addAll(r, label);
      gridPane.add(tilePane, tile.getX(), tile.getY());
      System.out.println(tile.getTileId() + " " + tile.getX() + " " + tile.getY());
    }

    gridPane.setAlignment(Pos.CENTER);

    stage.setTitle("Ladder Game");
    scene.setRoot(gridPane);
    stage.setScene(scene);
  }
}
