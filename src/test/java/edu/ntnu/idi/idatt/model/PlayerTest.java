package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.engine.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  private BoardGame game;
  private Player player;

  @BeforeEach
  void setUp() {
    game = new BoardGame("Test Game", "Test Description");
    Tile startingTile = new Tile(1, null, 1, 1);
    game.getBoard().setStartingTile(startingTile);
    game.getBoard().addTile(startingTile);
    player = new Player("chris", game, "TopHat");
    player.placeOnTile(startingTile);
  }

  @Nested
  @DisplayName("Positive tests for the Player class")
  class PositiveTests {
    @Test
    @DisplayName("Should return the players name and current tile position")
    void player_Constructor_ValidInput () {
      assertEquals("chris", player.getName());
      assertEquals(1, player.getCurrentTile().getTileId());
      assertEquals("TopHat", player.getPiece());
    }

    @Test
    @DisplayName("Should return the player on the given tile and update its current position")
    void placeOnTile_UpdateCurrentPosition_WhenValidInput () {
      Tile tile1 = new Tile(1,null, 1, 1);
      player.placeOnTile(tile1);

      assertEquals(1, player.getCurrentTile().getTileId());
    }

    @Test
    @DisplayName("Should move the player a certain amount of steps and update the current tile")
    void move_UpdateCurrentPositionBySteps_WhenGivenValidInput () {
      Tile tile1 = new Tile(1,null, 1, 1);
      Tile tile2 = new Tile(2,null, 1, 2);
      Tile tile3 = new Tile(3,null, 1, 3);
      Board board = new Board();
      game.getBoard().addTile(tile1);
      game.getBoard().addTile(tile2);
      game.getBoard().addTile(tile3);

      player.move(3);

      assertEquals(3,player.getCurrentTile().getTileId() );
    }

    @Nested
    @DisplayName("Negative tests for Player class")
    class NegativeTests {
      @Test
      @DisplayName("Should throw an IllegalArgumentException if tiles is null")
      void placeOnTile_ThrowException_WhenTilesIsNull () {

        assertThrows(IllegalArgumentException.class, () -> player.placeOnTile(null));
      }

      // TODO make negative test for move() after implemented exception handling
    }
  }
}