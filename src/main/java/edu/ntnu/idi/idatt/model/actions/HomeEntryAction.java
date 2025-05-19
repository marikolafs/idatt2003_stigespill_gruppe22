package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the starting tile of a players final stretch in Ludo. When a player with the
 * corresponding piece to the final stretch passes this tile, they should move onto the final
 * stretch, rather than moving past it.
 */
public class HomeEntryAction implements TileAction {

  private static final Logger log = LoggerFactory.getLogger(HomeEntryAction.class);
  private final String description;
  BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  String piece;
  private int destinationTileId;


  public HomeEntryAction(String description, String piece, int destinationTileId) {
    this.description = description;
    this.piece = piece;
    this.destinationTileId = destinationTileId;
  }

  /**
   * Accessor method for the actions description.
   *
   * @return the description of the action.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Accessor method for destinationTileId.
   *
   * @return the tile id of the destination for the action
   */
  public int getDestinationTileId() {
    return destinationTileId;
  }

  /**
   * Mutator method for destinationTileId.
   *
   * @param destinationTileId the destination for the action
   */
  public void setDestinationTileId(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  public String getPiece() {
    return piece;
  }

  @Override
  public void perform(Player player) {
    if (player == null || player.getCurrentPiece() == null) {
      return;
    }

    Piece piece = player.getCurrentPiece();
    Tile destinationTile = game.getBoard().getTile(destinationTileId);
    piece.setCurrentTile(destinationTile);

    log.info("{} {} -> Has entered home stretch", player.getName(), description);
  }
}
