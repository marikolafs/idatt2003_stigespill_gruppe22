package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.HashMap;
import java.util.Map;

/*
Ludo regler:
- 1 terning
- 4 brikker per spiller -> mulighet for tilpassning?
- hvis terningen viser 6, kan du ta ut en ny brikke i startområdet
  eller flytte en allerede aktiv brikke 6 steg fremover.
- du kan hoppe over motstanderens brikker, men du kan bli slått tilbake til
  startområdet hvis en motstander lander på samme plass som din.
- når en brikke når målområdet, må du få eksakt antall øyne på terningen for å hjemføre den
- du vinner om du er først til å få alle dine brikker trygt i mål


- legge til brikker for hver spiller, plassere de på riktig punkt på brettet.
- gjøre det mulig å velge hvilken brikke som skal flyttes hvis den er ute av startområdet, men ikke kommet i mål enda.
- ha en måte å endre stien for brikker basert på farge/brikke
 */

/*
Maybe change to "homeTile" + entry tile from home (startFinalStretch)
 */

/**
 * Represents the starting tile of a players final stretch in Ludo. When a player with the
 * corresponding piece to the final stretch passes this tile, they should move onto the final
 * stretch, rather than moving past it.
 */
public class HomeEntryAction implements TileAction {

  //tenker at disse brettene kan være fil-baserte så vi unngår å måtte deale med å endre createBoard

  private final String description;
  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  String piece;
  private final Map<Integer, Tile> tiles;

  HomeStretchAction homeStretchAction;


  public HomeEntryAction(String description, String piece) {
    this.description = description;
    this.piece = piece;
    tiles = game.getBoard().getTiles();
  }

  /**
   * Determines a players route based on which piece they are using.
   *
   * @param player the player whose path is being determined.
   */
  public void setNextTiles(Player player) {

    // set startFinalStretchAction on all tiles 6 tiles before the final stretch
    // then consistently check whether they are at the final stretch tiles. ??

    Map<Integer, Tile> finalTiles = new HashMap<>();

    int checkingTile = player.getCurrentTile().getTileId();
    if (player.getPiece().equals(piece)) {
      //player.setFinalStretch(true);
    }

    for (Tile tile : tiles.values()) {
      if (tile.getLandAction().equals("HomeEntryAction") && tile.getTileId() >  checkingTile) {
        finalTiles.put(tile.getTileId(), tile);
      }
    }

    /*

    for (Tile tile : tiles.values()) {

      if ("FinalStretchAction".equals(tile.getLandAction())) {
        if (finalStretchAction.getPiece().equals(player.getPiece())) {
          finalTiles.put(tile.getTileId(), tile);
        }
      }
    }

     */
    //add logic to set next tile as the tiles for the last stretch of the correct color, in the correct order
  }

  /**
   * Accessor method for the actions description.
   *
   * @return the description of the action.
   */
  public String getDescrption() {
    return description;
  }

  @Override
  public void perform(Player player) {
  }
}
