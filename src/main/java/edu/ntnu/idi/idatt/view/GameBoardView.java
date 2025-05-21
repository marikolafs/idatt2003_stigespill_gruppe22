package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The GameBoardView class is responsible for rendering the game board and the players on it.
 * It creates a grid pane to represent the board and adds tiles and player pieces.
 */
public class GameBoardView extends StackPane implements BoardGameObserver {

  private static final double TILE_SIZE = 50;
  private final GridPane gridPane;
  private final Board board;
  private final List<Player> players;
  private Text winnerDeclaration;

  public GameBoardView(Board board, List<Player> players) throws FileNotFoundException {
    this.board = board;
    this.players = players;
    this.gridPane = new GridPane();
    this.gridPane.setAlignment(Pos.CENTER);
    this.winnerDeclaration = new Text();

    this.winnerDeclaration.setStyle("-fx-font-size: 90;");

    addTilesToGrid();
    addPlayersToGrid();

    this.getChildren().addAll(gridPane, winnerDeclaration);
  }

  /**
   * Adds tiles to the grid pane.
   * Each tile is represented by a rectangle with a label showing its ID.
   */
  private void addTilesToGrid() {
    Map<Integer, Tile> tiles = board.getTiles();

    for (Tile tile : tiles.values()) {
      StackPane tilePane = new StackPane();
      Label label = new Label("Tile: " + tile.getTileId());
      Rectangle rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);

      // Set tile color
      if (tile.getTileId() % 2 == 0) {
        rectangle.setFill(Color.BEIGE);
      } else {
        rectangle.setFill(Color.OLIVE);
      }

      tilePane.getChildren().addAll(rectangle, label);
      gridPane.add(tilePane, tile.getX(), tile.getY());
    }
  }

  /**
   * Adds players to the grid pane.
   * @throws FileNotFoundException if the image file cannot be found.
   */
  private void addPlayersToGrid() throws FileNotFoundException {
    for (Player player : players) {
      StackPane playerPane = new StackPane();

      // Load player image
      String piecePath = "/images/pieces/" + player.getPiece() + ".png";;
      InputStream inputStream = getClass().getResourceAsStream(piecePath);
      if (inputStream == null) {
        throw new IllegalArgumentException("Image not found at path: " + piecePath);
      }

      Image image = new Image(inputStream);
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(40);
      imageView.setFitWidth(40);

      Label label = new Label(player.getPiece());
      label.setStyle("-fx-font-size: 5;");

      playerPane.getChildren().addAll(imageView, label);
      gridPane.add(playerPane, player.getCurrentTile().getX(), player.getCurrentTile().getY());

    }
  }

  public void updateBoard() throws FileNotFoundException {
    gridPane.getChildren().clear();
    addTilesToGrid();
    addPlayersToGrid();
  }

  @Override
  public void stateChanged(GameEvent event) {
    switch (event.getEventType()){
      case PLAYER_MOVED -> {
        if (event.getPlayer() instanceof Player player) {
          Platform.runLater(() -> {
            try {
              updateBoard();
            } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
            }
          });
        }
      }
      case PLAYER_PIECE_CHANGED -> {
        Platform.runLater(() -> {
          try {
            updateBoard();
          } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
          }
        });
      }
      case PLAYER_WIN -> {
            if (event.getPlayer() instanceof Player player) {
            Platform.runLater(() ->
                winnerDeclaration.setText(player.getName() + " has won the game!"));
            }
        }

      default -> {
        // Handle other events if needed
      }
    }
  }
}