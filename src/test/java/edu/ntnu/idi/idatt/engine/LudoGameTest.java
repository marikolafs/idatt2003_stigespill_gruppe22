package edu.ntnu.idi.idatt.engine;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LudoGameTest {
  /*

  private Ludo game;
  private Player player;
  private Tile startingTile;
  Piece piece = new Piece(player, startingTile, false, false);
  private List<Player> players = new ArrayList<>();
  private Board board = game.getInstance(game.getName(), game.getDescription()).getBoard();

  @BeforeEach
  public void setUp() {
    game = new Ludo("Ludo", "Classic game of Ludo.");

    startingTile = new Tile(1, null, 1, 1);
    Tile tile1 = new Tile(2, null, 2, 1);
    Tile tile2 = new Tile(3, null, 3, 1);
    board.addTile(startingTile);
    board.addTile(tile1);
    board.addTile(tile2);
    startingTile.setNextTile(tile1);
    tile1.setNextTile(tile2);
    tile2.setHomeTile(true, null);

    player = new Player("Test Player", game, "Cat");
    Piece piece = new Piece(player, startingTile, false, true);
    player.getPieces().add(piece);

    players.add(player);
  }

  @Test
  public void testCanMoveFromStartWith6() {
    Piece piece = player.getPieces().get(0);
    assertTrue(game.canMove(piece, 6));
  }

  @Test
  public void testCannotMoveFromStartWithout6() {
    Piece piece = player.getPieces().get(0);
    assertFalse(game.canMove(piece, 1));
  }

   */

}
