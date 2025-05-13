package edu.ntnu.idi.idatt.io;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import java.io.FileWriter;
import java.io.IOException;

public class BoardFileWriterGson implements BoardFileWriter {

  private Gson gson;

  /**
   * Constructs a new BoardFileWriterGson instance. Initializes the Gson parser to be used for JSON
   * processing.
   */
  public BoardFileWriterGson() {
    this.gson = new Gson();
  }

  JsonArray jsonArray = new JsonArray();
  JsonObject object = new JsonObject();

  /**
   * Writes a board configuration to a JSON file.
   *
   * @param board the board that will be saved in a JSON file
   * @throws IOException if an error occurs while writing to the file
   */
  @Override
  public void writeBoard(Board board) throws IOException {
    Board gameBoard = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription())
        .getBoard();
    object.add("board", jsonArray);
    JsonWriter writer = new JsonWriter(new FileWriter("src/main/resources/board.json"));
    try {
      writer.beginObject();
      writer.name("name").value(BoardGame.getName());
      writer.name("description").value(BoardGame.getDescription());

      writer.name("rows").value(gameBoard.getRows());
      writer.name("columns").value(gameBoard.getColumns());

      writer.name("tiles");
      writer.beginArray();
      for (Tile tile : gameBoard.getTiles().values()) {
        writer.beginObject();
        writer.name("id").value(tile.getTileId());
        writer.name("x").value(tile.getX());
        writer.name("y").value(tile.getY());

        // Write next tile if it exists
        if (tile.getNextTile() != null) {
          writer.name("nextTileId").value(tile.getNextTile().getTileId());
        }

        // Write tile action if it exists
        //TODO: edit to account for different tile actions
        if (tile.getLandAction() != null) {
          writer.beginObject();
          writer.name("action");
          writer.name("type").value(String.valueOf(tile.getLandAction()));
          writer.name("destinationTileId").value(LadderAction.getDestinationTileId());
          writer.name("description").value(LadderAction.getDescription());
          writer.endObject();
        }
        writer.endObject();
      }
      writer.endArray();
      writer.endObject();
    } finally {
      try {
        writer.close();
      } catch (Exception e) {
        handleFileError(new IOException("Failed to handle file: " + e.getMessage(), e));
        throw new IOException("Failed to write board file", e);
      }
    }
  }
}
