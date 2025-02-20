package edu.ntnu.idi.idatt;

public class Tile {

  private Tile nextTile;
  private int tileId;
  private TileAction landAction;

  public Tile(int tileId) {
    this.tileId = tileId;
  }

  public void landPlayer(Player player) {

  }

  public void leavePlayer(Player player) {

  }

  public void setNextTile(Tile nextTile) {
    if(tileId == 90){
      this.nextTile = null;
    } else {
      this.nextTile = nextTile;
    }
    // next tile = Tile + 1, within the existing tiles, tile 90 has no next tile
  }
}
/*Representerer ett felt på spillbrettet. I denne mappen har vi sagt at felt skal henge etter hverandre.
Derfor har et felt alltid en referanse til «neste felt». Dersom det ikke finnes et «neste felt» er feltet det
siste feltet i spillet (altså mål).
Et felt kan ha tilknyttet en «aksjon/handling» (engelsk: Action) som blir utført på den spilleren
som lander på feltet.*/