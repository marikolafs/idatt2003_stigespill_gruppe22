package edu.ntnu.idi.idatt.model;

/**
 * The Piece class represents a players piece. A piece belongs to a player and is always located on
 * a tile. A piece is initially set in the start area, and ends in the home area, which is its last
 * tile.
 *
 * @version 1.0
 */
public class Piece {

  private Tile currentTile;
  private boolean isHome;
  private boolean isInStart;
  private static Player player;


  /**
   * Constructs a new Piece belonging to the specified player and sees its current tile as well as
   * whether it is positioned in the start or home area.
   *
   * @param player      the owner of the piece
   * @param currentTile the tile the piece is located on
   * @param isHome      whether the piece has reached its final tile
   * @param isInStart   whether a piece is positioned in its start area
   */
  public Piece(Player player, Tile currentTile, boolean isHome, boolean isInStart) {
    this.currentTile = currentTile;
    this.isHome = isHome;
    this.isInStart = isInStart;
    this.player = player;
  }

  /**
   * Accessor method for a pieces current tile.
   *
   * @return the current tile
   */
  public Tile getCurrentTile() {
    return currentTile;
  }

  /**
   * Mutator method for a pieces current tile.
   *
   * @param currentTile the tile the piece should be updated to be on
   */
  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  /**
   * Accessor method for the value declaring whether a piece has reached its final tile.
   *
   * @return whether a piece is home
   */
  public boolean isHome() {
    return isHome;
  }

  /**
   * Mutator method for the isHome value describing whether a piece is in its home.
   *
   * @param isHome the updated value for whether a piece is home
   */
  public void setHome(boolean isHome) {
    this.isHome = isHome;
  }

  /**
   * Accessor method for the value declaring whether a piece is still in its starting area.
   *
   * @return whether a piece is in start
   */
  public boolean isInStart() {
    return isInStart;
  }

  /**
   * Mutator method for the isInStart value describing whether a piece is in its start area.
   *
   * @param isInStart the updated value for whether a piece is in start
   */
  public void setInStart(boolean isInStart) {
    this.isInStart = isInStart;
  }

  /**
   * Accessor method for a pieces owner.
   *
   * @return the player who owns a piece
   */
  public static Player getPlayer() {
    return player;
  }
}
