package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

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
  private List<Player> playersOnTile;

  /**
   * Constructs a Tile with a specified tile ID and land action.
   *
   * @param tileId the unique identifier for the tile
   * @param landAction the action to be executed when a player lands on this tile
   */
  public Tile(int tileId, TileAction landAction) {
    this.tileId = tileId;
    this.landAction = landAction;
    this.playersOnTile = new ArrayList<>();
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
   * Accessor method for nextTile.
   *
   * @return the nextTile
   */

  public Tile getNextTile() {
    return nextTile;
  }

    /**
     * Accessor method for landAction.
     *
     * @return the landAction
     */
  public TileAction getLandAction() {
    return landAction;
  }

  /**
   * Accessor method for playersOnTile.
   *
   * @return the players on the tile
   */
  public List<Player> getPlayersOnTile() {
    return playersOnTile;
  }

  /**
   * Mutator method for nextTile.
   *
   * @param nextTile the next tile
   */
  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  /**
   * This method is called when a player lands on the tile. The player is added to the list of
   * players on the tile and the land action is executed if it is not null.
   *
   * @param player the player that lands on the tile
   * @throws IllegalArgumentException if player is null
   */
  public void landPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (!playersOnTile.contains(player)) {
      playersOnTile.add(player);
    }

    if (landAction != null) {
      landAction.perform(player);
    }

  }

  /**
   * Remove the player from the tile when its supposed to leave the tile.
   *
   * @param player the player to leave the tile
   * @throws IllegalArgumentException if player is null
   */
  public void leavePlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    playersOnTile.remove(player);
  }


}