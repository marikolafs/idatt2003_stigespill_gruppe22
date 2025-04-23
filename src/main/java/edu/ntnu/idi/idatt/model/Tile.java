package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.model.actions.TileAction;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.observer.events.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tile on the game board. The tiles are linked to each other, for that reason a tile
 * always has a referance to the "next tile". If there is no "next tile" the tile is the last tile
 * in the game (goal). A tile can be referenced to an action, that will be executed when the player
 * lands on that tile.
 *
 * @version 1.0
 */
public class Tile extends Observable {

  private Tile nextTile;
  private int tileId;
  private TileAction landAction;
  private List<Player> playersOnTile;
  private int x;
  private int y;

  /**
   * Constructs a Tile with a specified tile ID and land action.
   *
   * @param tileId     the unique identifier for the tile
   * @param landAction the action to be executed when a player lands on this tile
   * @param x          the x coordinate of the tile
   * @param y          the y coordinate of the tile
   */
  public Tile(int tileId, TileAction landAction, int x, int y) {
    this.tileId = tileId;
    this.landAction = landAction;
    this.playersOnTile = new ArrayList<>();
    this.x = x;
    this.y = y;
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
   * Mutator method for landAction.
   *
   * @param landAction the landaction to be set
   */
  public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
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
   * Accessor method for x.
   *
   * @return the tiles x coordinate
   */
  public int getX() {
    return x;

  }

  /**
   * Accessor method for y.
   *
   * @return the tiles y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * mutator method for x.
   *
   * @param x the new x coordinate
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * mutator method for y.
   *
   * @param y the new y coordinate
   */
  public void setY(int y) {
    this.y = y;
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

    notifyObservers(new GameEvent(Event.PLAYER_MOVED,
        player.getName() + " landed on tile " + tileId, player));


    if (landAction != null) {
      landAction.perform(player);

      notifyObservers(new GameEvent(Event.TILE_ACTION,
          player.getName() + " triggered an action on tile " + tileId, player));
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

    notifyObservers(new GameEvent(Event.PLAYER_LEFT_TILE,
        player.getName() + " left tile " + tileId, player));
  }
}