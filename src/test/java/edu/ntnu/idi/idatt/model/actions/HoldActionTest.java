package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HoldActionTest {

  private BoardGame game;
  private HoldAction holdAction;
  private Tile actionTile;
  private Player player;

  @BeforeEach
  void setUp() {
    // Reset any state from previous tests
    game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    game.createBoard(9, 10);
    actionTile = game.getBoard().getTile(15);

    // Ensure that the action tile is initialized
    assertNotNull(actionTile, "Action tile is null");

    // Add the hold action to the board.
    game.getBoard().addHold(actionTile.getTileId());

    player = new Player("TestPlayer", game, "testPiece");

    game.addPlayer(player);

    player.placeOnTile(actionTile);
  }

  @Test
  void testHoldAction() {
    // checking that shouldHold is only true once player has landed on the action tile.
    assertFalse(player.shouldHold());

    actionTile.landPlayer(player);

    assertTrue(player.shouldHold());
  }

  @Test
  void testEndHold() {
    // emulating how shouldHold would be set to false during the play loop.
    player.setHoldAction(true);

    if (player.shouldHold()) {
      player.setHoldAction(false);
    }

    assertFalse(player.shouldHold());
  }
}
