package edu.ntnu.idi.idatt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.Model.Tile;
import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Player;
import edu.ntnu.idi.idatt.PlayerFiles;
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
      BoardGame game;
      game = new BoardGame();
      Tile startingTile = new Tile(1, null);
      game.getBoard().setStartingTile(startingTile);
      game.getBoard().addTile(startingTile);

      List<Player> players =  new ArrayList<>();
      PlayerFiles playerFiles = new PlayerFiles();
      File file = new File("src/main/resources/ExamplePlayers.csv");
      playerFiles.addPlayersThroughFile(file);
      assertEquals(4, players.size());
    }
  }
}
