package edu.ntnu.idi.idatt;

public class Tile {

  private Tile nextTile;
  private int tileId;
  private TileAction landAction;

  public Tile(int tileId) {
    this.tileId = tileId;
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
/*Representerer ett felt på spillbrettet. I denne mappen har vi sagt at felt skal henge etter hverandre.
Derfor har et felt alltid en referanse til «neste felt». Dersom det ikke finnes et «neste felt» er feltet det
siste feltet i spillet (altså mål).
Et felt kan ha tilknyttet en «aksjon/handling» (engelsk: Action) som blir utført på den spilleren
som lander på feltet.*/