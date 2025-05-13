package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a return action in the board game, this makes it so that if a player lands on a tile
 * with a return action, they will be forced to move one tile back. This class implements the
 * TileAction interface and can be set as a landing action on game tiles. When a player lands on a
 * tile with a ReturnAction, they are immediately moved to the specified destination tile.
 */
public class ReturnAction implements TileAction {

  private static final Logger log = LoggerFactory.getLogger(ReturnAction.class);
  private static String description;

  public ReturnAction(String description) {
    if (description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or empty");
    }
    this.description = description;
  }

  /**
   * Accessor method for description.
   *
   * @return the description
   */
  public static String getDescription() {
    return description;
  }

  @Override
  public void perform(Player player) {
    BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    Tile destinationTile = game.getBoard().getTile(player.getCurrentTile().getTileId() - 1);
    int destinationTileId = destinationTile.getTileId();

    if (destinationTile == null) {
      log.error("Destination tile {} not found", destinationTileId);
      return;
    }

    log.info("{} {} -> Moves to {}", player.getName(), description, destinationTileId);

    player.getCurrentTile().leavePlayer(player);
    player.placeOnTile(destinationTile);
    destinationTile.landPlayer(player);
  }
}

