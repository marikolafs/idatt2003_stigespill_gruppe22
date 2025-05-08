package edu.ntnu.idi.idatt.io;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.exceptions.JsonParsingException;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.model.actions.TileAction;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardFileWriterGson implements BoardFileWriter {

  private static final Logger logger = LoggerFactory.getLogger(BoardFileWriterGson.class);
  private final Gson gson;

  public BoardFileWriterGson() {
    this.gson = new Gson();
  }

  @Override
  public void writeBoard(Board board) throws IOException {
    try (JsonWriter writer = new JsonWriter(new FileWriter("src/main/resources/board.json"))) {
      writer.beginObject();
      writer.name("name").value(BoardGame.getName());
      writer.name("description").value(BoardGame.getDescription());
      writer.name("rows").value(board.getRows());
      writer.name("columns").value(board.getColumns());

      writer.name("tiles");
      writer.beginArray();
      for (Tile tile : board.getTiles().values()) {
        writer.beginObject();
        writer.name("id").value(tile.getTileId());
        writer.name("x").value(tile.getX());
        writer.name("y").value(tile.getY());

        if (tile.getNextTile() != null) {
          writer.name("nextTileId").value(tile.getNextTile().getTileId());
        }

        if (tile.getLandAction() != null) {
          writer.name("action");
          writer.beginObject();
          TileAction tileAction = tile.getLandAction();
          if (tileAction instanceof LadderAction) {
            LadderAction ladderAction = (LadderAction) tileAction;
            writer.name("type").value("Ladder");
            writer.name("destinationTileId").value(ladderAction.getDestinationTileId());
            writer.name("description").value(ladderAction.getDescription());
          }
          writer.endObject();
        }
        writer.endObject();
      }
      writer.endArray();
      writer.endObject();
    } catch (Exception e) {
      logger.error("Error writing board to file: {}", e.getMessage(), e);
      throw new JsonParsingException("Could not write board to JSON file: " + e.getMessage(), e);
    }
  }
}