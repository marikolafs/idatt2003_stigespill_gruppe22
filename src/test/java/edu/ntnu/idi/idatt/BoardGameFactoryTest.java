package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.factory.BoardGameFactory;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.HoldAction;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.model.actions.ReturnAction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardGameFactoryTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Should create a board with 90 tiles")
    void shouldCreateBoardWith90Tiles() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame90();
      assertEquals(90, game.getBoard().getTiles().size());
    }

    @Test
    @DisplayName("Should create a board with 45 tiles")
    void shouldCreateBoardWith45Tiles() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame45();
      assertEquals(45, game.getBoard().getTiles().size());
    }

    @Test
    @DisplayName("Should return board name")
    void shouldReturnBoardName() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame45();
      assertEquals("Ladder game 45", game.getName());
    }

    @Test
    @DisplayName("Should return board description")
    void shouldReturnBoardDescription() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame45();
      assertEquals("Ladder game with 45 tiles", game.getDescription());
    }

    @Test
    @DisplayName("Should show return ladderAction")
    void shouldShowLadderAction() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame45();
      Tile tileWithAction = game.getBoard().getTile(4);
      LadderAction action = (LadderAction) tileWithAction.getLandAction();

      assertTrue(tileWithAction.getLandAction() instanceof LadderAction,
          "Action should be of type LadderAction");
    }

    @Test
    @DisplayName("Should return holdAction")
    void shouldReturnHoldAction() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame90Plus();
      Tile tileWithAction = game.getBoard().getTile(21);
      HoldAction action = (HoldAction) tileWithAction.getLandAction();

      assertTrue(tileWithAction.getLandAction() instanceof HoldAction, "Action should be of type HoldAction");
    }

    @Test
    @DisplayName("Should return returnAction")
    void shouldReturnReturnAction() throws Exception {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      BoardGameFactory.createLadderGame90Plus();
      Tile tileWithAction = game.getBoard().getTile(5);
      ReturnAction action = (ReturnAction) tileWithAction.getLandAction();

      assertTrue(tileWithAction.getLandAction() instanceof ReturnAction, "Action should be of type ReturnAction");
    }
  }
}
