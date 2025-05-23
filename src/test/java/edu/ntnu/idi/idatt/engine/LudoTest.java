package edu.ntnu.idi.idatt.engine;

import edu.ntnu.idi.idatt.io.BoardFileReaderGson;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LudoTest {

  private BoardFileReaderGson reader;
  private Path file;
  private Board board;
  private List<Player> players;
  private Player testPlayer;
  private BoardGame game;

  @BeforeEach
  public void setUp() throws IOException {
    BoardGame.reset();
    game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    reader = new BoardFileReaderGson();
    file = Paths.get("src/main/resources/LudoBoard.json");
    board = reader.readBoard(file);
    board.setStartingTile(board.getTile(1));
    players = new ArrayList<>();
    testPlayer = new Player("test player 1", game, "Pig");
    game.giveLudoPieces(testPlayer);
    game.addPlayer(testPlayer);
  }

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Should read board with 76 tiles")
    public void readBoardWith80Tiles() {
      assertEquals(80, board.getTiles().size());
    }

    @Test
    @DisplayName("Should give a player 4 pieces")
    public void givePlayer4Pieces() {
      assertEquals(4, testPlayer.getPieces().size());
    }

    @Test
    @DisplayName("should allow player to move only when 6 is rolled")
    public void allowPlayerToMoveWhen6IsRolled() {
      assertTrue(game.canMove(testPlayer.getPieces().get(0), 6));
      assertFalse(game.canMove(testPlayer.getPieces().get(0), 1));
    }

    // Test that a piece moves to the appropriate entry tile
    @Test
    @DisplayName("Should enter the board on the correct tile")
    public void enterTheBoardOnTheCorrectTile() {
      Piece piece = testPlayer.getPieces().get(0);
      piece.setInStart(true);
      testPlayer.setCurrentPiece(piece);

      Tile entryTile = board.getTile(80);
      piece.setCurrentTile(entryTile);
      game.canMove(piece, 6);
      entryTile.getLandAction().perform(testPlayer);

      assertFalse(piece.isInStart());
      assertEquals(29, piece.getCurrentTile().getTileId());
    }


    @Test
    @DisplayName("Should move a piece the appropriate distance")
    public void movePieceTheAppropriateDistance() {
      Piece testPiece = testPlayer.getPieces().get(0);
      testPiece.setInStart(false);
      testPiece.setCurrentTile(board.getTile(1));
      game.movePiece(testPiece, 1);
      assertEquals(2, testPiece.getCurrentTile().getTileId());
    }

    @Test
    @DisplayName("Should move into home stretch")
    public void moveIntoHomeStretch() {
      Piece testPiece = testPlayer.getPieces().get(0);
      testPiece.setInStart(false);
      testPiece.setCurrentTile(board.getTile(27));
      game.movePiece(testPiece, 1);
      assertEquals(65, testPiece.getCurrentTile().getTileId());
    }

    @Test
    @DisplayName("Should set a winner")
    public void setWinner() {
      for (Piece piece : testPlayer.getPieces()) {
        piece.setHome(true);
      }
      game.setLudoWinner(testPlayer);
      assertTrue(testPlayer.getIsWinner());
      assertTrue(game.getGameWon());
    }
  }
}
