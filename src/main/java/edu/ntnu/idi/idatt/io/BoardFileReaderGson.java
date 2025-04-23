package edu.ntnu.idi.idatt.io;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.exceptions.JsonParsingException;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.HoldAction;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.model.actions.ReturnAction;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of BoardFileReader that uses Gson to read board data from JSON files. This class
 * handles parsing of board configurations from JSON format and constructs a playable Board with
 * tiles, tile connections, and tile actions.
 */
public class BoardFileReaderGson implements BoardFileReader {

  private Gson gson;
  private static final Logger logger = LoggerFactory.getLogger(BoardFileReaderGson.class);

  /**
   * Constructs a new BoardFileReaderGson instance. Initializes the Gson parser to be used for JSON
   * processing.
   */
  public BoardFileReaderGson() {
    this.gson = new Gson();
  }

  /**
   * Reads a board configuration from a JSON file at the specified path. Parses the JSON structure
   * to create a Board object with properly configured tiles, connections, and actions.
   *
   * @param path the path to the JSON file containing board configuration
   * @return a Board object constructed from the JSON file data
   * @throws IOException if an error occurs while reading or parsing the file
   */
  @Override
  public Board readBoard(Path path) throws IOException {
    try (FileReader reader = new FileReader(path.toFile())) {
      // Parse the Json file
      JsonObject boardJson = JsonParser.parseReader(reader).getAsJsonObject();

      // Extract board properties
      String name = boardJson.has("name")
          ? boardJson.get("name").getAsString() : "default name";

      String description = boardJson.has("description")
          ? boardJson.get("description").getAsString() : "default description";

      logger.info("Creating a new BoardGame: {} {}", name, description);

      // Create a new BoardGame instance with the given name and description
      BoardGame game = BoardGame.getInstance(name, description);
      game.setName(name);
      game.setDescription(description);

      Board board = BoardGame.getBoard();
      board.setRows(boardJson.get("rows").getAsInt());
      board.setColumns(boardJson.get("columns").getAsInt());

      //Create a map to store tiles by id for linking
      Map<Integer, Tile> tilesMap = new HashMap<>();

      // Read and create all tiles
      JsonArray tilesJsonArray = boardJson.getAsJsonArray("tiles");
      for (JsonElement tileElement : tilesJsonArray) {
        JsonObject tileJson = tileElement.getAsJsonObject();
        int tileId = tileJson.get("id").getAsInt();

        // Create the individual tile
        Tile tile = new Tile(tileId, null, 1, 1);
        tilesMap.put(tileId, tile);
        board.addTile(tile);

        // Set starting tile
        if (tileId == 1) {
          board.setStartingTile(tile);
        }

        // Set goal tile as the tile with the highest id
        if (board.getGoalTile() == null || tileId > board.getGoalTile().getTileId()) {
          board.setGoalTile(tile);
        }
      }

      // Add actions and custom next tile links from JSON
      for (JsonElement tileElement : tilesJsonArray) {
        JsonObject tileJson = tileElement.getAsJsonObject();
        int tileId = tileJson.get("id").getAsInt();
        Tile currentTile = tilesMap.get(tileId);
        int x = tileJson.get("x").getAsInt();
        currentTile.setX(x);
        int y = tileJson.get("y").getAsInt();
        currentTile.setY(y);

        // Link to the next tile if the tile has a nextTile
        if (tileJson.has("nextTileId")) {
          int nextTileId = tileJson.get("nextTileId").getAsInt();
          Tile nextTile = tilesMap.get(nextTileId);
          currentTile.setNextTile(nextTile);
        }

        // Add action if the tile has one
        if (tileJson.has("action")) {
          JsonObject actionJson = tileJson.getAsJsonObject("action");
          String actionType = actionJson.get("type").getAsString();

          if ("LadderAction".equals(actionType)) {
            int destinationTileId = actionJson.get("destinationTileId").getAsInt();
            String actionDescription = actionJson.get("description").getAsString();

            LadderAction ladderAction = new LadderAction(destinationTileId, actionDescription);
            currentTile.setLandAction(ladderAction);
          }

          if ("ReturnAction".equals(actionType)) {
            String actionDescription = actionJson.get("description").getAsString();

            ReturnAction returnAction = new ReturnAction(actionDescription);
            currentTile.setLandAction(returnAction);
          }

          if("HoldAction".equals(actionType)) {
            String actionDescription = actionJson.get("description").getAsString();

            HoldAction holdAction = new HoldAction(actionDescription);
            currentTile.setLandAction(holdAction);
          }
        }
      }

      return board;
    } catch (Exception e) {
      throw new JsonParsingException("Error while parsing JSON-fil: " + e.getMessage(), e);
    }
  }
}