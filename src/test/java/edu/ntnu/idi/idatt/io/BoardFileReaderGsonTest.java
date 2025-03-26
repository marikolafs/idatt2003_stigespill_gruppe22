package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardFileReaderGsonTest {

  private BoardFileReaderGson boardFileReaderGson;
  private Path testBoardPath;
  private Board board;
  private BoardGame game;

  @BeforeEach
  void setUp() throws IOException {
    boardFileReaderGson = new BoardFileReaderGson();
    testBoardPath = Paths.get("src/main/resources/ExampleBoard.json");
    board = boardFileReaderGson.readBoard(testBoardPath);
    game = BoardGame.getInstance("default name", "default description");
  }

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Should assure that 90 tiles are read and created")
    void readBoard_ReadAllTiles() {

      assertEquals(90, board.getTiles().size());
    }

    @Test
    @DisplayName("Should get the name and description of the board game created")
    void readBoard_ShouldGetTheNameAndDescription() {

      assertEquals("Stigespill90", game.getName(), "Board name should match");
      assertEquals("Et klassisk stigespill med 90 felt og flere stiger.", game.getDescription(),
          "Board description should match");
    }

    @Test
    @DisplayName("Should get the starting and goal tile id ")
    void readBoard_ShouldGetStartingAndGoalTile() {
      assertEquals(1, game.getBoard().getStartingTile().getTileId());
      assertEquals(90, game.getBoard().getGoalTile().getTileId());
    }

    @Test()
    @DisplayName("Should assert the next tile")
    void readBoard_ShouldGetTheNextTile() {
      assertEquals(2, game.getBoard().getTile(1).getNextTile().getTileId());
    }

    @Test
    @DisplayName("Should get the action, type, destination tile and description of a tile that has one")
    void readBoard_ShouldGetAction() {
      Tile tileWithAction = game.getBoard().getTile(4);
      LadderAction action = (LadderAction) tileWithAction.getLandAction();

      assertTrue(tileWithAction.getLandAction() instanceof LadderAction,
          "Action should be of type LadderAction");
      assertEquals(14, action.getDestinationTileId(), "Action destination tile id should match");
      assertEquals("Ladder from 4 to 14", action.getDescription(),
          "Action description should match");
    }
  }


  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Should verify tiles without actions have null landAction")
    void readBoard_TilesWithoutActions_ShouldHaveNullAction() {
      // Check tiles that shouldn't have actions
      Tile tileTwo = game.getBoard().getTile(2);
      assertNull(tileTwo.getLandAction(), "Tile 2 should not have an action");

      Tile tileFifty = game.getBoard().getTile(50);
      assertNull(tileFifty.getLandAction(), "Tile 50 should not have an action");
    }

    @Test
    @DisplayName("Should throw IOException when file does not exist")
    void readBoard_NonExistentFile_ShouldThrowIOException() {
      Path nonExistentPath = Paths.get("non_existent_file.json");

      assertThrows(IOException.class, () -> {
        boardFileReaderGson.readBoard(nonExistentPath);
      }, "Should throw IOException for non-existent file");
    }


  }


}