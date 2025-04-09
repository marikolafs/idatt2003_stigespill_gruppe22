package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BoardFileWriterGsonTest {
  private BoardFileWriterGson boardFileWriterGson;
  private BoardFileReaderGson boardFileReaderGson;
  private Path testBoardPath;
  private Board board;
  private BoardGame game;

  @BeforeEach
  void setUp() throws IOException {
    boardFileWriterGson = new BoardFileWriterGson();
    BoardGame game = BoardGame.getInstance("default name", "default description");
    Board board = game.getBoard();
    board.setRows(2);
    board.setColumns(1);
    Tile tile1 = new Tile(1, null, 1, 1);
    Tile tile2 = new Tile(2, null, 1, 2);
    board.addTile(tile1);
    board.addTile(tile2);
    boardFileWriterGson.writeBoard(board);
  }

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Should write a board with 2 tiles")
    void writeBoard() throws IOException {
      boardFileReaderGson = new BoardFileReaderGson();
      testBoardPath = Paths.get("src/main/resources/board.json");
      board = boardFileReaderGson.readBoard(testBoardPath);
      game = BoardGame.getInstance("default name", "default description");
      assertEquals(2, board.getTiles().size());
    }

    @Test
    @DisplayName("Should find next tile")
    void findNextTile() throws IOException {
      boardFileReaderGson = new BoardFileReaderGson();
      testBoardPath = Paths.get("src/main/resources/board.json");
      board = boardFileReaderGson.readBoard(testBoardPath);
      game = BoardGame.getInstance("default name", "default description");
      assertEquals(2, board.getTile(1).getNextTile().getTileId());
    }
  }
}