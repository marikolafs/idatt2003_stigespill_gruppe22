package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameBoardView extends StackPane {
  private static final double TILE_SIZE= 50;
  private final Canvas canvas;


  /**
   * Creates a canvas with the specified number of rows and columns.The canvas size is determined
   * by multiplying the number of rows and columns by the tile size.
   * @param rows the rows of the game board
   * @param cols the columns of the game board
   */
  public GameBoardView(int rows, int cols) {
    this.canvas = new Canvas(cols * TILE_SIZE, rows * TILE_SIZE);
    this.getChildren().add(canvas);

  }

    /**
     * Draws the game board on the canvas with the specified tile ids.
     *
     * @param board the game board to be drawn
     */
  public void drawBoard(Board board) {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    Map<Integer, Tile> tiles = board.getTiles();
    int rows = board.getRows();
    int cols = board.getColumns();

    for (Tile tile : tiles.values()) {
      int tileId = tile.getTileId();
      int r = tile.getY();
      int c = tile.getX();

      // converts the tile coordinates to canvas coordinates
      double x = ((double) c / (double) cols) * canvas.getWidth();
      double y = canvas.getHeight() - (((double) r / (double) rows) * canvas.getHeight());

      // draws the tile
      gc.setFill(tileId % 2 == 0 ? Color.BEIGE : Color.OLIVE);
      gc.fillRect(x, y - TILE_SIZE, TILE_SIZE, TILE_SIZE);

      // draws the border of the tile
      gc.setStroke(Color.BLACK);
      gc.strokeRect(x, y - TILE_SIZE, TILE_SIZE, TILE_SIZE);

      // adds tileId text to the tile
      gc.setFill(Color.BLACK);
      gc.fillText(String.valueOf(tileId), x + TILE_SIZE / 4, y - TILE_SIZE / 2);
    }
  }
}