package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
  private String gameType;
  private ImageView boardImage;


  public GameBoardView(Board board, List<Player> players, String gameType) throws FileNotFoundException {
    this.board = board;
    this.players = players;
    this.gridPane = new GridPane();
    this.gridPane.setAlignment(Pos.CENTER);
    this.winnerDeclaration = new Text();
    this.gameType = gameType;

    this.winnerDeclaration.setStyle("-fx-font-size: 90;");

    this.boardImage = addPicturesToGrid();
    addTilesToGrid();
    addPlayersToGrid();

    StackPane stackPane = new StackPane();
    stackPane.getChildren().addAll(boardImage, gridPane);

    this.getChildren().addAll(stackPane, winnerDeclaration);
  }

  /**
   * Adds tiles to the grid pane.
   * Each tile is represented by a rectangle with a label showing its ID.
   */
  private void addTilesToGrid() {
    Map<Integer, Tile> tiles = board.getTiles();

    for (Tile tile : tiles.values()) {
      StackPane tilePane = new StackPane();
      //Label label = new Label("Tile: " + tile.getTileId());
      Rectangle rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);

      // Set tile color
      if (tile.getTileId() % 2 == 0) {
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.TRANSPARENT);
      } else {
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.TRANSPARENT);
      }

      tilePane.getChildren().addAll(rectangle);
      gridPane.add(tilePane, tile.getX(), tile.getY());
    }
  }

  /**
   * Adds players to the grid pane.
   * @throws FileNotFoundException if the image file cannot be found.
   */
  private void addPlayersToGrid() throws FileNotFoundException {
    if (gameType.equals("Ludo")) {
      for (Player player : players) {
        for (Piece piece : player.getPieces()) {
          Tile tile = piece.getCurrentTile();
          if (tile == null) {
            continue;
          }

          StackPane piecePane = new StackPane();

          String piecePath = "/images/pieces/" + player.getPiece() + ".png";
          InputStream pieceStream = getClass().getResourceAsStream(piecePath);
          if (pieceStream == null) {
            throw new IllegalArgumentException("Image not found: " + piecePath);
          }

          Image pieceImage = new Image(pieceStream);
          ImageView pieceImageView = new ImageView(pieceImage);
          pieceImageView.setFitHeight(40);
          pieceImageView.setFitWidth(40);

          piecePane.getChildren().add(pieceImageView);
          gridPane.add(piecePane, piece.getCurrentTile().getX(), piece.getCurrentTile().getY());
        }
      }
    } else {
      for (Player player : players) {
        Tile tile = player.getCurrentTile();
        if (tile == null) {
          continue;
        }

        StackPane playerPane = new StackPane();

        // Load player image
        String piecePath = "/images/pieces/" + player.getPiece() + ".png";
        InputStream inputStream = getClass().getResourceAsStream(piecePath);
        if (inputStream == null) {
          throw new IllegalArgumentException("Image not found at path: " + piecePath);
        }

        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        playerPane.getChildren().add(imageView);

        gridPane.add(playerPane, player.getCurrentTile().getX(), player.getCurrentTile().getY());
      }
    }
  }

  public ImageView addPicturesToGrid() throws FileNotFoundException {

    String boardPath = "src/main/resources/images/game_images/" + gameType + ".png";
    FileInputStream boardStream = new FileInputStream(boardPath);
    if (boardStream == null) {
      throw new IllegalArgumentException("Image not found: " + boardPath);
    }
    Image boardImage = new Image(boardStream);
    ImageView boardImageView = new ImageView(boardImage);
    boardImageView.setFitHeight(board.getRows() * TILE_SIZE);
    boardImageView.setFitWidth(board.getColumns() * TILE_SIZE);
    return boardImageView;
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