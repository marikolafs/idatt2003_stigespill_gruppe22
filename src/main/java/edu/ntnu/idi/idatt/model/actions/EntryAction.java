package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.Player;

/**
 * Tile representing a pieces entry onto the board.
 */
public class EntryAction implements TileAction {

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
  }
}
