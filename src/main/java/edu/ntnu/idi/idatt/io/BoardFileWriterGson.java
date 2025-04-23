package edu.ntnu.idi.idatt.io;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.exceptions.JsonParsingException;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.EntryAction;
import edu.ntnu.idi.idatt.model.actions.HoldAction;
import edu.ntnu.idi.idatt.model.actions.HomeEntryAction;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.model.actions.ReturnAction;
import edu.ntnu.idi.idatt.model.actions.TileAction;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardFileWriterGson implements BoardFileWriter {

  private Gson gson;
  private static final Logger logger = LoggerFactory.getLogger(BoardFileWriterGson.class);

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
        if (tile.getLandAction() != null) {
          writer.beginObject();
          writer.name("action");
          writer.beginObject();
          TileAction tileAction = tile.getLandAction();
          if (tileAction instanceof LadderAction) {
            LadderAction ladderAction = (LadderAction) tileAction;
            writer.name("type").value("ladder");
            writer.name("destinationTileId").value(ladderAction.getDestinationTileId());
            writer.name("description").value(ladderAction.getDescription());
          } else if (tileAction instanceof HoldAction) {
            HoldAction holdAction = (HoldAction) tileAction;
            writer.name("type").value("hold");
            writer.name("description").value(holdAction.getDescription());
          } else if (tileAction instanceof ReturnAction) {
            ReturnAction returnAction = (ReturnAction) tileAction;
            writer.name("type").value("return");
            writer.name("description").value(returnAction.getDescription());
          } else if (tileAction instanceof EntryAction) {
            EntryAction entryAction = (EntryAction) tileAction;
            writer.name("type").value("entry");
            writer.name("destinationTileId").value(entryAction.getDestinationTileId());
            writer.name("piece").value(entryAction.getPiece());
            writer.name("description").value(entryAction.getDescription());
          } else if (tileAction instanceof HomeEntryAction) {
            HomeEntryAction homeAction = (HomeEntryAction) tileAction;
            writer.name("type").value("home");
            writer.name("destinationTileId").value(homeAction.getDestinationTileId());
            writer.name("piece").value(homeAction.getPiece());
            writer.name("description").value(homeAction.getDescription());
          }
          writer.endObject();
        }
        writer.endObject();
      }
      writer.endArray();
      writer.endObject();
    } catch (Exception e) {
      logger.error("Feil ved skriving av board til fil: {}", e.getMessage(), e);
      throw new JsonParsingException("Kunne ikke skrive board til JSON-fil: " + e.getMessage(), e);
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        logger.error("Kunne ikke lukke filen: {}", e.getMessage(), e);
      }
    }
  }
}
