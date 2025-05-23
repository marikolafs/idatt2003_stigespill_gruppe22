package edu.ntnu.idi.idatt.io;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The PlayerFiles class is responsible for reading and writing a list of players in csv files.
 */
public class PlayerFiles {

  private List<Player> players;

  /**
   * Constructor initializing the player list.
   */
  public PlayerFiles() {
    players = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription()).getPlayers();
  }

  /**
   * Method that writes players to a csv file.
   *
   * @param players the list of players to be saved to a file.
   */
  public static void writePlayersToFile(List<Player> players) {
    BufferedWriter writer = null;
    try {
      FileWriter fileWriter = new FileWriter("src/main/resources/players.csv");
      writer = new BufferedWriter(fileWriter);
      for (Player player : players) {
        writer.write(player.getName() + "," + player.getPiece() + "\n");
      }
    } catch (IOException e) {
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
      }
    }
  }

  /**
   * Method that parses a file and adds players to list.
   *
   * @param file the file the list of players is to be read from.
   */
  public static void addPlayersThroughFile(File file) {
    BoardGame game = BoardGame.getInstance("default name", "default description");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String playerName = line.split(",")[0];
        String playerPiece = line.split(",")[1];
        Player player = new Player(playerName, game, playerPiece);
        game.addPlayer(player);
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
