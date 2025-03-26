package edu.ntnu.idi.idatt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.TileAction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class BoardFiles {

  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  JsonArray jsonArray = new JsonArray();
  JsonObject object = new JsonObject();

  /**
   * Method that converts the tiles in the active board to Json object and writes them to a Json
   * file.
   *
   * @throws IOException if filewriter can´t continue.
   */
  void boardFileWriter() throws IOException {
    Board gameBoard = BoardGame.getInstance("default name", "default description").getBoard();
    object.add("board", jsonArray);
    JsonWriter writer = new JsonWriter(new FileWriter("src/main/resources/board.json"));
    try {
      writer.beginObject();
      writer.name("board");
      writer.beginArray();
      for (Tile tile : gameBoard.getTiles().values()) {
        writer.beginObject();
        writer.name("id").value(tile.getTileId());
        writer.name("tileAction").value(String.valueOf(tile.getLandAction()));
        writer.endObject();
      }
      writer.endArray();
      writer.endObject();
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
      }
    }
  }

  /**
   * Reads a board configuration from the specified file.
   *
   * @param file the file containing the board configuration.
   * @throws IOException if an error occurs while reading the file.
   */
  /*
  void boardFileReader(File file) throws IOException {
    String json = null;
    Board board = BoardGame.getInstance("default name", "default description").getBoard();

    try {
      InputStream is = new FileInputStream(file);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
      JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
      JsonArray jsonArray = jsonObject.get("board").getAsJsonArray();
      for (int i = 0; i < jsonArray.size(); i++) {
        JsonObject jsonBoard = jsonArray.get(i).getAsJsonObject();
        int id = jsonBoard.get("id").getAsInt();
        String tileAction = jsonBoard.get("tileAction").getAsString();
        board.addTile(new Tile(id, getTileAction(tileAction)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
*/
  /**
   * Reads a string and returns the corresponding TileAction.
   *
   * @param tileAction String representation of the desired tileAction.
   * @return the TileAction corresponding to the String.
   */
  /*
  private TileAction getTileAction(String tileAction) {
    Player player = BoardGame
        .getInstance("default name", "default description").getCurrentPlayer();
    switch (tileAction) {
      case "null":
        return null;
      case "ladderAction":
        TileAction ladderAction = null;
        return ladderAction;
    }
    return null;
  }
   */
}
