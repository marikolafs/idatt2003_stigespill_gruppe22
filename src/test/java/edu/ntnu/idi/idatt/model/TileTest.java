package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TileTest {
  private BoardGame game;
  private BoardGameObserver observer;

  @BeforeEach
  void setUp() {
    observer = new BoardGameObserver() {
      @Override
      public void stateChanged(GameEvent event) {

      }
    };
    game = new BoardGame("Test Game", "Test Description");
    game.getBoard().addTile(new Tile(0, null,  1, 1));
    game.getBoard().addTile(new Tile(1, null,   1, 1));
  }

  @Nested
  @DisplayName("Positive tests for the Tile class")
  class PositiveTests {

    @Test
    @DisplayName("Should notify the observers when a player lands on the tile and return the event")
    void notifyObservers_WhenPlayerLandsOnTile() {
      Tile tile = game.getBoard().getTile(1);
      Player player1 = new Player("Player 1", game, "Tophat");
      GameEvent event = new GameEvent("player_landed", "Player 1 landed on tile 1", player1);

      tile.addObserver(observer);
      tile.landPlayer(player1);

      assertEquals(1, tile.getObservers().size());
      assertEquals(player1, tile.getPlayersOnTile().get(0));
      assertEquals("player_landed",event.getEventType());
    }


    @Test
    @DisplayName("Should return the tile id and land action")
    void tile_Constructor_ValidInput() {
      Tile tile = new Tile(0, null,  1, 1);

      assertEquals(0, tile.getTileId());
      assertEquals(null, tile.getLandAction());
    }

    @Test
    @DisplayName("Should return x and y coordinates")
    void tile_Constructor_ValidInput_Coordinates() {
      Tile tile = new Tile(0, null,  1, 1);
      assertEquals(1, tile.getX());
      assertEquals(1, tile.getY());
    }

    @Test
    @DisplayName("Should return the next tile")
    void setNextTile_ValidInput() {
      Tile tile1 = new Tile(0, null,   1, 1);
      Tile tile2 = new Tile(1, null, 1, 2);
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