package edu.ntnu.idi.idatt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Board;
import edu.ntnu.idi.idatt.Model.Player;
import edu.ntnu.idi.idatt.Model.Tile;
import edu.ntnu.idi.idatt.Model.TileAction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    Board gameBoard = BoardGame.getInstance().getBoard();
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


  void boardFileReader(File file) throws IOException {
    String json = null;

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
        System.out.println(id + " " + tileAction);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void getTileAction(String tileAction) {
    Player player = BoardGame.getInstance().getCurrentPlayer();
    switch (tileAction) {
      case "null":
        break;
      case "tileaction":
        TileAction.perform(player);
        break;
    }
  }
}
