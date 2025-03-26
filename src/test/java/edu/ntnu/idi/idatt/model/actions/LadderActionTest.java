package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadderActionTest {

  private BoardGame game;
  private LadderAction ladderAction;
  private Tile startTile;
  private Tile endTile;
  private Player player;

  @BeforeEach
  void setup() {
    // Reset any state from previous tests
    game = BoardGame.getInstance("Test Game", "Test Description");
    game.createBoard(90);


    startTile = game.getBoard().getTile(20);
    endTile = game.getBoard().getTile(30);


    // Make sure both tiles are initialized
    assertNotNull(startTile, "Start tile is null");
    assertNotNull(endTile, "End tile is null");

    // Create the ladder action with the correct destination id
    ladderAction = new LadderAction(30, "Move up to tile 30");
    startTile.setLandAction(ladderAction);

    player = new Player("TestPlayer", game, "dog");

    game.addPlayer(player);

    player.placeOnTile(startTile);
  }

  @Test
  void testLadderAction() {
    startTile.landPlayer(player);

    assertEquals(endTile, player.getCurrentTile(),
        "Player should move to end tile " + endTile + " but is on "
            + player.getCurrentTile());

    assertTrue(endTile.getPlayersOnTile().contains(player),
        "Player should be in the list of players on the end tile");

    assertFalse(startTile.getPlayersOnTile().contains(player),
        "Player should no longer be in the list of players on the start tile");
  }
}