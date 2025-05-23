package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.model.actions.EntryAction;
import edu.ntnu.idi.idatt.model.actions.HoldAction;
import edu.ntnu.idi.idatt.model.actions.LadderAction;
import edu.ntnu.idi.idatt.model.actions.ReturnAction;
import edu.ntnu.idi.idatt.model.actions.TileAction;
import java.util.HashMap;
import java.util.Map;
import edu.ntnu.idi.idatt.model.actions.TileAction;

/**
 * Represents the board consisting of tiles.
 * The board has a name and a description of the game it displays.
 * The board has the purpose of adding and storing tiles.
 * Each tile has an unique id which the board can use to keep track of the tiles in the game
 *
 * @version 1.3
 */
public class Board {

  private final Map<Integer, Tile> tiles;
  private final Map<Player, Tile> entryTiles = new HashMap<>();
  private Tile startingTile;
  private Tile goalTile;
  private int rows;
  private int columns;
  private Map<String, Tile> startingTiles = new HashMap<>();


  /**
   * Constructs a new Board.
   * Initializes an empty HashMap to store the tiles.
   * The starting tile and goal tile are initially null and must be set separately
   * using setStartingTile() and setGoalTile() methods.
   *
   */
  public Board() {
    this.tiles = new HashMap<>();
    this.startingTile = null;
    this.goalTile = null;
    this.rows = rows;
    this.columns = columns;
  }

  /**
   * Accessor method for startingTile.
   *
   * @return the starting tile
   */

  public Tile getStartingTile() {
    return startingTile;
  }

  /**
   * Mutator method for startingTile.
   */
  public void setStartingTile(Tile startingTile) {
    this.startingTile = startingTile;
  }

  /**
   * Accessor method for goalTile.
   *
   * @return the goal tile
   */
  public Tile getGoalTile() {
    return goalTile;
  }

  /**
   * Mutator method for goalTile.
   *
   */
  public void setGoalTile(Tile goalTile) {
    this.goalTile = goalTile;
  }

  /**
   * Accessor method for rows
   *
   * @return the number of rows on the board
   */
  public int getRows() {
    return rows;
  }

  /**
   * Mutator method for rows
   *
   * @param rows the new amount of rows
   */
  public void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * Accessor method for columns
   *
   * @return the number of columns on the board
   */
  public int getColumns() {
    return columns;
  }

  /**
   * Mutator method for columns
   *
   * @param columns the new amount of columns
   */
  public void setColumns(int columns) {
    this.columns = columns;
  }

  /**
   * Accessor method for tiles.
   *
   * @return the tiles
   */
  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

  /**
   * Accessor method for a pieces starting tile. Sees which tiles contain the EntryTile action and returns the tile belonging to a specified piece.
   * @param piece the piece the starting tile belongs to
   */
  public Tile getStartingTileForPiece(String piece) {
    for (Tile tile : tiles.values()) {
      TileAction action = tile.getLandAction();
      if (action instanceof EntryAction entryAction && entryAction.getPiece().equalsIgnoreCase(piece)) {
        return tile;
      }
    }
    return null;
  }

  /**
   * Has the purpose of adding a new tile and linking it to a previous tile if it exists.
   *
   * @param tile the tile to be added
   */
  public void addTile(Tile tile) {

    if (tiles.containsKey(tile.getTileId())) {
      return;
    }
    tiles.put(tile.getTileId(), tile);

    if (tiles.containsKey(tile.getTileId() - 1)) {
      Tile previousTile = tiles.get(tile.getTileId() - 1);

      if (previousTile != null) {
        previousTile.setNextTile(tile);
      }
    }
  }

  /**
   * Adds a ladder to the board.
   * The ladder is represented by a LadderAction object.
   *
   * @param ladderStart the starting tile id of the ladder
   * @param destinationTileId the destination tile id of the ladder
   */
  public void addLadder(int ladderStart, int destinationTileId) {
    LadderAction ladderAction = new LadderAction(destinationTileId, "Ladder");
    Tile ladderTile = getTile(ladderStart);
    ladderTile.setLandAction(ladderAction);
  }

  /**
   * Adds a return tile to the board.
   *
   * @param returnTileId the tileId where the return action will be placed.
   */
  public void addReturn(int returnTileId) {
    ReturnAction returnAction = new ReturnAction("Return action");
    Tile returnTile = getTile(returnTileId);
    returnTile.setLandAction(returnAction);
  }

  public void addHold(int holdTileId) {
    HoldAction holdAction = new HoldAction("Hold action");
    Tile holdTile = getTile(holdTileId);
    holdTile.setLandAction(holdAction);
  }

  /**
   * The method has the purpose of retrieving a tile based on the tileId.
   *
   * @param tileId the id of the tile to be retrieved
   * @return the tile with the specified id
   */
  public Tile getTile(int tileId) {
    if (tiles.containsKey(tileId)) {
      return tiles.get(tileId);
    } else {
      throw new IllegalArgumentException("Tile does not exist");
    }
  }
}