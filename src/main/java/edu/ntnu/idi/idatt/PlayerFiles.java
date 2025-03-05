package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PlayerFiles class is responsible for reading and writing a list of players in csv files.
 */
public class PlayerFiles {

  private List<Player> players;
  static BoardGame game = new BoardGame();

  /**
   * Constructor initializing the player list.
   */
  public PlayerFiles() {
    players = new ArrayList<>();
  }

  /**
   * Method that writes players to a csv file.
   *
   * @param players the list of players to be saved to a file.
   */
  public static void writePlayersToFile(List<Player> players) {
    BufferedWriter writer = null;
    try {
      FileWriter fileWriter = new FileWriter("players.csv");
      writer = new BufferedWriter(fileWriter);
      for (Player player : players) {
        writer.write(player.getName() + "," + player.getPiece());
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
   * Method that parses a file and returns players.
   *
   * @param file the file the list of players is to be read from.
   */
  public static void addPlayersThroughFile(File file){
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String playerName  = line.split(",")[0];
        String playerPiece = line.split(",")[1];
        game.addPlayer(new Player(playerName, game, playerPiece));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
