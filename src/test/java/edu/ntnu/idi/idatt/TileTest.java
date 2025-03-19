package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TileTest {
  private BoardGame game;

  @BeforeEach
  void setUp() {
    game = new BoardGame();
    game.getBoard().addTile(new Tile(0, null));
    game.getBoard().addTile(new Tile(1, null));
  }

  @Nested
  @DisplayName("Positive tests for the Tile class")
  class PositiveTests {
    @Test
    @DisplayName("Should return the tile id and land action")
    void tile_Constructor_ValidInput() {
      Tile tile = new Tile(0, null);

      assertEquals(0, tile.getTileId());
      assertEquals(null, tile.getLandAction());
    }

    @Test
    @DisplayName("Should return the next tile")
    void setNextTile_ValidInput() {
      Tile tile1 = new Tile(0, null);
      Tile tile2 = new Tile(1, null);
      tile1.setNextTile(tile2);

      assertEquals(tile2, tile1.getNextTile());
    }

    @Test
    @DisplayName("Should return the players on the tile")
    void landPlayer_ValidInput() {
      Tile tile = game.getBoard().getTile(1);
      Player player1 = new Player("Player 1", game, "Tophat");

      tile.landPlayer(player1);

      assertEquals(1, tile.getPlayersOnTile().size());
      assertEquals(player1, tile.getPlayersOnTile().get(0));
    }

    @Test
    @DisplayName("Should remove the player from the tile")
    void removePlayer_ValidInput() {
      Tile tile = game.getBoard().getTile(1);
      Player player1 = new Player("Player 1", game, "Tophat");
      tile.landPlayer(player1);

      tile.leavePlayer(player1);

      assertEquals(0, tile.getPlayersOnTile().size());
    }

    @Nested
    @DisplayName("Negative tests for the Tile class")
    class NegativeTests {

      @Test
      @DisplayName("Should throw IllegalArgumentException for player null")
      void landPlayer_throwException_WhenPlayerNull() {
        Tile tile = game.getBoard().getTile(1);

        assertThrows(IllegalArgumentException.class, () -> tile.landPlayer(null));
      }

      @Test
      @DisplayName("Should throw IllegalArgumentException for player null")
      void leavePlayer_throwException_WhenPlayerNull() {
        Tile tile = game.getBoard().getTile(1);

        assertThrows(IllegalArgumentException.class, () -> tile.leavePlayer(null));
      }
    }
  }
}