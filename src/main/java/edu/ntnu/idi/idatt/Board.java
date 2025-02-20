package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the board consisting of tiles.
 * The board has the purpose of adding and storing tiles.
 * Each tile has an unique id which the board can use to keep track of the tiles in the game
 *
 * @version 1.0
 */
public class Board {
  Map<Integer, Tile> tiles = new HashMap<>();
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