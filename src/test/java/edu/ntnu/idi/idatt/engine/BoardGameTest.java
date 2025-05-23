package edu.ntnu.idi.idatt.engine;

import edu.ntnu.idi.idatt.observer.events.Event;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardGameTest {

  private BoardGameObserver observer = new BoardGameObserver() {
    @Override
    public void stateChanged(GameEvent event) {
      // Observer implementation for testing
    }
  };

  @Nested
  @DisplayName("Positive test")
  class PositiveTests {

    @Test
    @DisplayName("Should notify observers when a player lands on the tile")
    void notifyObservers_WhenPlayerLandsOnTile() {
      Tile tile = new Tile(1, null, 1, 1);
      Player player = new Player("Chris", null, "Tophat");
      GameEvent event = new GameEvent(Event.PLAYER_MOVED, "Player Chris landed on tile 1", player);

      tile.addObserver(observer);
      tile.landPlayer(player);

      assertEquals(1, tile.getObservers().size());
      assertEquals(player, tile.getPlayersOnTile().get(0));
      assertEquals(Event.PLAYER_MOVED, event.getEventType());
    }

    @Test
    @DisplayName("Should add and return players in the list")
    void addPlayer_ReturnPlayersInList_ValidInput() {
      Tile tile = new Tile(1, null, 1, 1);
      BoardGame game = new BoardGame("Test Game", "Test Description");
      game.getBoard().setStartingTile(tile);

      Player player1 = new Player("Chris", game, "Tophat");
      Player player2 = new Player("Maria", game, "Dog");

      game.addPlayer(player2);
      game.addPlayer(player1);

      assertEquals(2, game.getPlayers().size());
    }

    @Test
    @DisplayName("Should return number of rows and columns")
    void createBoard_ReturnRowsAndColumns() {
      BoardGame game = new BoardGame("Test Game", "Test Description");
      game.createBoard(9, 10);
      assertEquals(10, game.getBoard().getRows());
      assertEquals(9, game.getBoard().getColumns());
    }

    @Test
    @DisplayName("Should return tiles on board")
    void createBoard_ReturnTiles() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      game.createBoard(1, 5);

      assertEquals(5, game.getBoard().getTiles().size());
    }

    @Test
    @DisplayName("Should return starting tile and goal tile")
    void createBoard_ReturnStartingAndGoalTile() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      game.createBoard(1, 5);

      assertEquals(1, game.getBoard().getStartingTile().getTileId());
      assertEquals(5, game.getBoard().getGoalTile().getTileId());
    }

    @Test
    @DisplayName("Should return the number of dice")
    void createDice_ReturnNumberOfDice() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      game.createDice(2);

      assertEquals(2, game.getDice().getDiceList().size());
    }

    @Test
    @DisplayName("Should play the game and determine the winner")
    void playGame_DetermineWinner() {
      BoardGame game = new BoardGame("Test Game", "Test Description");
      game.createBoard(2, 5);
      game.createDice(2);

      Player player1 = new Player("Chris", game, "Tophat");
      Player player2 = new Player("Maria", game, "Dog");

      game.addPlayer(player1);
      game.addPlayer(player2);


      Tile goalTile = game.getBoard().getGoalTile();
      player1.placeOnTile(goalTile);


      Player winner = game.getWinner();
      assertNotNull(winner, "Winner should not be null");
      assertEquals(player1, winner, "Player1 should be the winner");
    }

    @Test
    @DisplayName("Should get name and description")
    void getNameAndDescription() {
      String testName = "Test Game";
      String testDescription = "Test Description";
      BoardGame game = new BoardGame(testName, testDescription);

      assertEquals(testName, game.getName(), "Should return the correct name");
      assertEquals(testDescription, game.getDescription(), "Should return the correct description");
    }

    @Test
    @DisplayName("Should get singleton instance with name and description")
    void getSingletonInstance() {
      String testName = "Singleton Test";
      String testDescription = "Singleton Description";

      BoardGame game1 = BoardGame.getInstance(testName, testDescription);
      BoardGame game2 = BoardGame.getInstance("Different Name", "Different Description");

      // Verify it's the same instance (singleton pattern)
      assertEquals(game1, game2, "Should return the same instance");
      // First created instance values should be maintained
      assertEquals("Test Game", game1.getName(),
          "Should keep the default name from first instantiation");
    }
  }

  @Nested
  @DisplayName("Negative test")
  class NegativeTests {

    @Test
    @DisplayName("Should throw IllegalArgumentException when adding null player")
    void addPlayer_ThrowException_NullPlayer() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      assertThrows(IllegalArgumentException.class, () -> game.addPlayer(null));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating board with less than 1 tile")
    void createBoard_ThrowException_LessThanOneTile() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      assertThrows(IllegalArgumentException.class, () -> game.createBoard(0, 0));
      assertThrows(IllegalArgumentException.class, () -> game.createBoard(-1, -1));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when creating dice with less than 1 die")
    void createDice_ThrowException_LessThanOneDie() {
      BoardGame game = new BoardGame("Test Game", "Test Description");

      assertThrows(IllegalArgumentException.class, () -> game.createDice(0));
      assertThrows(IllegalArgumentException.class, () -> game.createDice(-1));
    }
  }
}