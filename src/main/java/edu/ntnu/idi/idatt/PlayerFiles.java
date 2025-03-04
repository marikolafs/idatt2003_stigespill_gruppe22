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


public class PlayerFiles {

  private List<Player> players;
  static BoardGame game = new BoardGame();

  public PlayerFiles() {
    players = new ArrayList<>();
  }

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
