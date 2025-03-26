package edu.ntnu.idi.idatt;


import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerFilesTest {
  
  @Nested
  @DisplayName("Positive test")
  class PositiveTest {
    @Test
    @DisplayName("Should read players from the given file and add them to the list")
    void readPlayersFromFileAndAddToList() {
      BoardGame game = BoardGame.getInstance("Test Game", "Test Description");
      game.createBoard(5);

      List<Player> players =  BoardGame.getInstance("Test Game", "Test Description").getPlayers();
      File file = new File("src/main/resources/ExamplePlayers.csv");
      PlayerFiles.addPlayersThroughFile(file);
      assertEquals(4, players.size());
    }

    @Test
    @DisplayName("Should write players from list to the given file")
    void writePlayersFromListToFile() {
      game = BoardGame.getInstance();
      board = new Board();
      game.createBoard(5);
      List<Player> players =  BoardGame.getInstance().getPlayers();
      Player player1 = new Player("chris", game, "TopHat");
      Player player2 = new Player("maria", game, "Cat");
      game.addPlayer(player1);
      game.addPlayer(player2);

      PlayerFiles.writePlayersToFile(players);

      players.remove(player1);
      players.remove(player2);

      File playersFile = new File("src/main/resources/players.csv");
      PlayerFiles.addPlayersThroughFile(playersFile);
      assertEquals(2, players.size());
    }
  }
}
