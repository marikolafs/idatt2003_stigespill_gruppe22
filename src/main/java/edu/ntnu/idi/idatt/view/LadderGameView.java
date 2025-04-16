package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LadderGameView {

  BoardGameFactory boardGameFactory = new BoardGameFactory();
  private static BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());
  private Board board = game.getBoard();
  private Map<Integer, Tile> tiles = board.getTiles();
  private static List<Player> players = game.getPlayers();

  BorderPane layout = new BorderPane();
  Scene scene = new Scene(layout, 300, 300, Color.WHITE);

  public LadderGameView(Stage stage) throws Exception {
    gameBoardView();
    playerView();
    scene.setRoot(layout);
    stage.setTitle("Ladder Game");
    stage.setScene(scene);
  }

  /**
   * View for the board and tiles, as well as the players on the board.
   *
   * @throws FileNotFoundException if an image file cannot be found.
   */
  //TODO edit to be updated by the observer
  public void gameBoardView() throws FileNotFoundException {
    GridPane gridPane = new GridPane();

    // adding tiles and labelling them
    for (Tile tile : tiles.values()) {
      StackPane tilePane = new StackPane();
      Label label = new Label("tile: " + tile.getTileId());
      Rectangle r = new Rectangle();
      r.setX(tile.getX());
      r.setY(tile.getY());
      r.setWidth(50);
      r.setHeight(50);
      if (tile.getTileId() % 2 == 0) {
        r.setFill(Color.BEIGE);
      } else {
        r.setFill(Color.OLIVE);
      }

      tilePane.getChildren().addAll(r, label);
      gridPane.add(tilePane, tile.getX(), tile.getY());
    }

    // Adding players to the tile they´re on
    // Should be updated to replace the image based on the chosen player piece
    for (Player player : players) {
      StackPane playerPane = new StackPane();

      FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
      Image image = new Image(inputstream);
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(40);
      imageView.setFitWidth(40);

      Label label = new Label(player.getPiece());
      label.setStyle("-fx-font-size: 5;");

      playerPane.getChildren().addAll(imageView, label);
      gridPane.add(playerPane, player.getCurrentTile().getX(), player.getCurrentTile().getY());
    }

    gridPane.setAlignment(Pos.CENTER);
    layout.setCenter(gridPane);
  }

  /**
   * View for currentPlayers menu
   *
   * @throws FileNotFoundException if the image file cannot be found
   */
  //TODO edit to update the board through the observer, change depending on currentPlayer
  public void playerView() throws FileNotFoundException {

    HBox hBox = new HBox();

    FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setFitWidth(50);

    Button rollButton = new Button("Roll");
    rollButton.setOnAction(e -> {
      BoardGame.play();
    });
    Text playerName = new Text("Player Name");
    Text tileName = new Text("Current Tile");

    hBox.getChildren().addAll(imageView,  rollButton,  playerName, tileName);

    layout.setBottom(hBox);
  }
}
