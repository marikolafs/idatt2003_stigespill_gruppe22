package edu.ntnu.idi.idatt;

/**
 * Represents a tile on the board. A tile has a unique ID and a reference to the next tile.
 *
 *@version 1.0
 *
 */
public class Tile {

  private Tile nextTile;
  private int tileId;
  private TileAction landAction;

  /**
   * Constructs a new Tile with the specified tile ID.
   *
   * @param tileId the unique ID of the tile
   */
  public Tile(int tileId) {
    this.tileId = tileId;
    this.nextTile = null;
  }

  /**
   * Accessor method for  the tile.
   *
   * @return the tile object
   */
  public int getTileId() {
    return tileId;
  }

  /**
   * Accessor method for nextTile.
   *
   * @return the next tile
   */
  public Tile getNextTile() {
    return nextTile;
  }

  /**
   * Mutator method for nextTile.
   *
   * @param nextTile the next tile
   */
  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  public void landPlayer(Player player) {

  }

  public void leavePlayer(Player player) {

  }

}
/*Representerer ett felt på spillbrettet. I denne mappen har vi sagt at felt skal henge etter hverandre.
Derfor har et felt alltid en referanse til «neste felt». Dersom det ikke finnes et «neste felt» er feltet det
siste feltet i spillet (altså mål).
Et felt kan ha tilknyttet en «aksjon/handling» (engelsk: Action) som blir utført på den spilleren
som lander på feltet.*/