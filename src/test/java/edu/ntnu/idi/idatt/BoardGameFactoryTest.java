package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Board;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
      BoardGame game = BoardGame.getInstance();
      BoardGameFactory.createLadderGame(90);
      assertEquals(90, game.getBoard().getTiles().size());
    }

    @Test
    @DisplayName("Should create a board with 45 tiles")
    void shouldCreateBoardWith45Tiles() throws Exception {
      BoardGame game = BoardGame.getInstance();
      BoardGameFactory.createLadderGame(45);
      assertEquals(45, game.getBoard().getTiles().size());
    }
  }
}
