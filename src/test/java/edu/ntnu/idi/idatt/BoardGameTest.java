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

  }
}
