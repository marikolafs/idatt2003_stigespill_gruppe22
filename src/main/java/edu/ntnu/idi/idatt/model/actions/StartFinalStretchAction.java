package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.HashMap;
import java.util.Map;

public class StartFinalStretchAction implements TileAction {

  //tenker at alle disse brettene kan være fil-basert så vi unngår å måtte deale med å endre createBoard

  private final String descrption;
  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  String color;
  private final Map<Integer, Tile> tiles;

  FinalStretchAction finalStretchAction;


  public StartFinalStretchAction(String descrption, String color) {
    this.descrption = descrption;
    this.color = color;
    tiles = game.getBoard().getTiles();
  }

  public void setNextTiles(Player player) {

    Map<Integer, Tile> finalTiles = new HashMap<>();

    for (Tile tile : tiles.values()) {

      if ("FinalStretchAction".equals(tile.getLandAction())) {
        if (finalStretchAction.getColor().equals(player.getPiece())) {
          finalTiles.put(tile.getTileId(), tile);
        }
      }
    }
    //add logic to set next tile as the tiles for the last stretch of the correct color, in the correct order
  }

  public String getDescrption() {
    return descrption;
  }

  @Override
  public void perform(Player player) {
    player.setFinalStretch(true);
    System.out.println(player.getName() + " holds ");
  }

}
