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

public class ReturnActionTest {

  private BoardGame game;
  private ReturnAction returnAction;
  private Tile actionTile;
  private Tile  destinationTile;
  private Player player;

  @BeforeEach
  void setUp() {
    game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    game.createBoard(9, 10);
    actionTile = game.getBoard().getTile(15);
    destinationTile = game.getBoard().getTile(actionTile.getTileId() - 1);

    assertNotNull(actionTile, "Action tile is null");

    game.getBoard().addReturn(actionTile.getTileId());

    player = new Player("TestPlayer", game, "dog");

    game.addPlayer(player);

    player.placeOnTile(actionTile);
  }

  @Test
  void testLadderAction() {
    actionTile.landPlayer(player);

    assertEquals(game.getBoard().getTile(destinationTile.getTileId()), player.getCurrentTile(),
        "Player should move to tile 14 but is on "
            + player.getCurrentTile());

    assertTrue(destinationTile.getPlayersOnTile().contains(player),
        "Player should be in the list of players on the end tile");

    assertFalse(actionTile.getPlayersOnTile().contains(player),
        "Player should no longer be in the list of players on the start tile");
  }
}
