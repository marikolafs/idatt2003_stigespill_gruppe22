package edu.ntnu.idi.idatt.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the board consisting of tiles.
 * The board has a name and a description of the game it displays.
 * The board has the purpose of adding and storing tiles.
 * Each tile has an unique id which the board can use to keep track of the tiles in the game
 *
 * @version 1.3
 */
public class Board {

  private Map<Integer, Tile> tiles;
  private String name;
  private String description;
  private Tile startingTile;
  private Tile goalTile;

  /**
   * Constructs a new Board with the specified name and description.
   * Initializes an empty HashMap to store the tiles.
   * The starting tile and goal tile are initially null and must be set separately
   * using setStartingTile() and setGoalTile() methods.
   *
   * @param name the name of the board game
   * @param description a brief description of the board game
   * @throws IllegalArgumentException if name or description is null or empty
   */
  public Board(String name, String description) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Board name cannot be null or empty");
    }
    if (description == null || description.trim().isEmpty()) {
      throw new IllegalArgumentException("Board description cannot be null or empty");
    }

    this.tiles = new HashMap<>();
    this.startingTile = null;
    this.goalTile = null;
    this.name = name;
    this.description = description;
  }

  /**
   * Accessor method for name.
   *
   * @return the name of the board
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor method for description.
   *
   * @return the description of the board.
   */
  public String getDescription() {
    return description;
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
   * Accessor method for tiles.
   *
   * @return the tiles
   */
  public Map<Integer, Tile> getTiles() {
    return tiles;
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