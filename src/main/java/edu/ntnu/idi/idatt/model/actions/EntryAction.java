package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tile representing a pieces entry onto the board. Determines which tile a piece should enter the
 * board on, based on which tile they start on.
 */
public class EntryAction implements TileAction {

  private static Logger logger = LoggerFactory.getLogger(EntryAction.class);
  private final BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());

  private final String description;
  private final String piece;
  private final int destinationTileId;

  public EntryAction(String description, String piece, int destinationTileId) {
    this.description = description;
    this.piece = piece;
    this.destinationTileId = destinationTileId;
  }

  /**
   * Accessor method for the actions description.
   *
   * @return the actions description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Accessor method for the piece belonging to the entry tile.
   *
   * @return the piece belonging to the entry tile
   */
  public String getPiece() {
    return piece;
  }

  /**
   * Accessor method for the tile id of the tile a piece will move to upon first entering the
   * board.
   *
   * @return the id of the tile the piece should move to
   */
  public int getDestinationTileId() {
    return destinationTileId;
  }

  @Override
  public void perform(Player player) {
    Piece piece = player.getCurrentPiece();
    if (piece == null || !piece.isInStart()) {
      return;
    }
    Tile destinationTile = game.getBoard().getTile(destinationTileId);
    if (destinationTile == null) {
      logger.warn("No destiantion tile found for piece {}", player.getPiece());
      return;
    }
    piece.setCurrentTile(destinationTile);
    piece.setInStart(false);
    destinationTile.addPiece(piece);
  }
}
