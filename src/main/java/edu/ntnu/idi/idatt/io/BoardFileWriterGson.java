package edu.ntnu.idi.idatt.io;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardFileWriterGson implements BoardFileWriter {

  private Gson gson;
  private static final Logger logger = LoggerFactory.getLogger(BoardFileWriterGson.class);

  /**
   * Constructs a new BoardFileWriterGson instance.
   * Initializes the Gson parser to be used for JSON processing.
   */
  public BoardFileWriterGson() {
    this.gson = new Gson();
  }

  JsonArray jsonArray = new JsonArray();
  JsonObject object = new JsonObject();

  @Override
  public void writeBoard(Board board) throws IOException {
    Board gameBoard = BoardGame.getInstance("default name", "default description").getBoard();
    object.add("board", jsonArray);
    JsonWriter writer = new JsonWriter(new FileWriter("src/main/resources/board.json"));
    try {
      writer.beginObject();
      writer.name(BoardGame.getName());
      writer.beginArray();
      for (Tile tile : gameBoard.getTiles().values()) {
        writer.beginObject();
        writer.name("id").value(tile.getTileId());
        /*
        if (tile.getLandAction() != null) {
          writer.name("action").value(String.valueOf(tile.getLandAction()));
        }
         */
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
}
