package edu.ntnu.idi.idatt;

/**
 * Represents a tile on the game board. The tiles are linked to each other, for that reason a
 * tile always has a referance to the "next tile". If there is no "next tile" the tile is the
 * last tile in the game (goal).
 * A tile can be referenced to an action, that will be executed when the player lands on that tile.
 *
 * @version 1.0
 */
public class Tile {

  private Tile nextTile;
  private int tileId;
  private TileAction landAction;

  /**
   * Constructs a Tile with a specified tile ID and land action.
   *
   * @param tileId the unique identifier for the tile
   * @param landAction the action to be executed when a player lands on this tile
   */
  public Tile(int tileId, TileAction landAction) {
    this.tileId = tileId;
    this.landAction = landAction;
  }

  /**
   * Accessor method for tileId.
   *
   * @return tileId
   */
  public int getTileId() {
    return tileId;
  }

  /**
   * Accessor method for nextTile
   *
   * @return the nextTile
   */
  public Tile getNextTile() {
    return nextTile;
  }

  //TODO add landPlayer method
  public void landPlayer(Player player) {

  }
  // TODO add leavePlayer method
  public void leavePlayer(Player player) {

  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }
}