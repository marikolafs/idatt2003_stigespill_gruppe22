package edu.ntnu.idi.idatt.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
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
      Tile tile = new Tile(1, null);
      BoardGame game = new BoardGame();
      game.getBoard().setStartingTile(tile);

      Player player1 = new Player("Chris", game, "Tophat");
      Player player2 = new Player("Maria", game, "Dog");

      game.addPlayer(player2);
      game.addPlayer(player1);

      assertEquals(2, game.getPlayers().size());
    }

    @Test
    @DisplayName("Should return tiles on board")
    void createBoard_ReturnTiles() {
      BoardGame game = new BoardGame();

      game.createBoard(5);

      assertEquals(5,game.getBoard().getTiles().size());
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

    @Test
    @DisplayName("Should play the game and determine the winner")
    void playGame_DetermineWinner() {
      BoardGame game = new BoardGame();
      game.createBoard(10);
      game.createDice(2);

      Player player1 = new Player("Chris", game, "Tophat");
      Player player2 = new Player("Maria", game, "Dog");

      game.addPlayer(player1);
      game.addPlayer(player2);

      game.play();

      Player winner = game.getWinner();

      assertEquals(game.getBoard().getGoalTile().getTileId(), winner.getCurrentTile().getTileId(), "Winner should be at the goal tile");
    }

  }
  @Nested
  @DisplayName("Negative test")
    class NegativeTests {

    @Test
    @DisplayName("Should throw IllegalArgumentException when adding null player")
    void addPlayer_ThrowException_NullPlayer() {
      BoardGame game = new BoardGame();

      assertThrows(IllegalArgumentException.class, () -> game.addPlayer(null));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating board with less than 1 tile")
    void createBoard_ThrowException_LessThanOneTile() {
      BoardGame game = new BoardGame();

      assertThrows(IllegalArgumentException.class, () -> game.createBoard(0));
      assertThrows(IllegalArgumentException.class, () -> game.createBoard(-1));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating dice with less than 1 die")
    void createDice_ThrowException_LessThanOneDie() {
      BoardGame game = new BoardGame();

      assertThrows(IllegalArgumentException.class, () -> game.createDice(0));
      assertThrows(IllegalArgumentException.class, () -> game.createDice(-1));
    }

  }


}
