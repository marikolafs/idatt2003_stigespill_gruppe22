package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardTest {
  @BeforeEach
      void setUp() {
    Board board = new Board();
    Tile tile1 = new Tile(1,null);
    Tile tile2 = new Tile(2,null);
    Tile tile3 = new Tile(3,null);

    board.addTile(tile1);
    board.addTile(tile2);
    board.addTile(tile3);
  }

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {
    @Test
    @DisplayName("Should return tiles in the list")
    void addTiles_ReturnTilesInList() {
      Board board = new Board();
      assertEquals(3, board.getTiles().size());
    }

    @Test
    @DisplayName("Should return the next tile")
    void addTiles_ReturnPreviousTile () {
      Board board = new Board();
      Tile tile1 = new Tile(1,null);
      Tile tile2 = new Tile(2,null);
      Tile tile3 = new Tile(3,null);

      board.addTile(tile1);
      board.addTile(tile2);
      board.addTile(tile3);

      Tile nextTile = board.getTile(1).getNextTile();

      assertEquals(2, nextTile.getTileId());


    }

    @Test
    @DisplayName("Should return tile based on tileId")
    void getTile_ShouldReturnTileId() {
      Board board = new Board();
      Tile tile1 = new Tile(1,null);
      Tile tile2 = new Tile(2,null);
      Tile tile3 = new Tile(3,null);

      board.addTile(tile1);
      board.addTile(tile2);
      board.addTile(tile3);

      assertEquals(tile2,board.getTile(2));
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {
    @Test
    @DisplayName("Should throw IllegalArgumentException if tile does not exist")
    void getTile_ThrowException_WhenTileDoesNotExist () {
      Board board = new Board();
      Tile tile1 = new Tile(1,null);
      Tile tile2 = new Tile(2,null);
      Tile tile3 = new Tile(3,null);

      board.addTile(tile1);
      board.addTile(tile2);
      board.addTile(tile3);

      assertEquals(tile2,board.getTile(2));

      assertThrows(IllegalArgumentException.class, () -> board.getTile(4));

    }
  }
}
