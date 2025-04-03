package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.model.Board;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.PlayerFiles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardFilesTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Should write tiles to file")
    void shouldWriteTilesToFile() throws IOException {
      BoardGame game = BoardGame.getInstance("default name", "default description");
      Board board = game.getBoard();
      Tile tile1 = new Tile(1, null, 1, 1);
      Tile tile2 = new Tile(2, null, 1, 2);
      board.addTile(tile1);
      board.addTile(tile2);
      BoardFiles boardFiles = new BoardFiles();
      boardFiles.boardFileWriter();
    }

    /*
    @Test
    @DisplayName("Should read board from files")
    void shouldReadBoardFromFile() throws IOException {
      BoardGame game = BoardGame.getInstance("default name","default description");
      Board board = game.getBoard();
      BoardFiles boardFiles = new BoardFiles();
      boardFiles.boardFileReader(new File("src/main/resources/board.json"));
      assertEquals(2, board.getTiles().size());
    }
     */
  }
}
