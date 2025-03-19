package edu.ntnu.idi.idatt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Board;
import edu.ntnu.idi.idatt.Model.Tile;
import java.io.FileWriter;
import java.io.IOException;

public class BoardFiles {

  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  JsonArray jsonArray = new JsonArray();
  JsonObject object = new JsonObject();

  /**
   * Method that converts the tiles in the active board to
   * Json object and writes them to a Json file.
   *
   * @throws IOException
   */
  private void boardFileWriter() throws IOException {
    Board gameBoard = BoardGame.getInstance().getBoard();
    try{
      object.add("board", jsonArray);
      JsonWriter writer = new JsonWriter(new FileWriter("src/main/resources/board.json"));
      writer.beginObject();
      writer.name("board");
      writer.beginArray();
      for(Tile tile : gameBoard.getTiles().values()){
        writer.beginObject();
        writer.name("id").value(tile.getTileId());
        writer.name("tileAction").value(String.valueOf(tile.getLandAction()));
        writer.endObject();
      }
      writer.endArray();
      writer.endObject();
      writer.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
