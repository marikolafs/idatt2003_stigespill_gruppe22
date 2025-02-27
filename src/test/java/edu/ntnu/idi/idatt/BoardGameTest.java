package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardGameTest {
  @Nested
  @DisplayName("Postive test")
  class PositiveTests {
    @Test
    @DisplayName("Should add and return players in the list")
    void addPlayer_ReturnPlayersInList_ValidInput() {

      BoardGame game = new BoardGame();
      Player player1 = new Player("Chris",game);
      Player player2 = new Player("Maria",game);

      game.addPlayer(player2);
      game.addPlayer(player1);

      assertEquals(2, game.getPlayers().size());

    }

    @Test
    @DisplayName("Should return tiles on board")
    void createBoard_ReturnTiles() {
      BoardGame game = new BoardGame();

      game.createBoard(5);

      assertEquals(5,game.getBoard().tiles.size());
    }

    @Test
    @DisplayName("Should return starting tile and goal tile")
    void createBoard_ReturnStartingAndGoalTile () {
      BoardGame game = new BoardGame();

      game.createBoard(5);

      assertEquals(1, game.getBoard().getStartingTile().getTileId());
      assertEquals(5, game.getBoard().getGoalTile().getTileId());
    }

    @Test
    @DisplayName("Should return the number of dice")
    void createDice_ReturnNumberOfDice() {
      BoardGame game = new BoardGame();

      game.createDice(2);

      assertEquals(2,game.getDice().getDiceList().size());
    }


  }
}
