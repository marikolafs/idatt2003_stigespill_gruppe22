
package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.tileActions.TileAction;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a ladder action in the board game, which moves a player from their current tile
 * to a specific destination tile when triggered.
 * This class implements the TileAction interface and can be set as a landing action on game tiles.
 * When a player lands on a tile with a LadderAction, they are immediately moved to the specified
 * destination tile.
 *
 * @version 2.0
 */
public class LadderAction implements TileAction {
  private static final Logger logger = LoggerFactory.getLogger(LadderAction.class);
  private final int destinationTileId;
  private final String description;




  public LadderAction( int destinationTileId, String description ) {
    if (description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or empty");
    }
    this.destinationTileId = destinationTileId;
    this.description = description;

  }

  public String getDescription() {
    return description;
  }

  @Override
  public void perform(Player player) {
    BoardGame game= BoardGame.getInstance();
    Tile destinationTile = game.getBoard().getTile(destinationTileId);

    if (destinationTile == null) {
      logger.error("Destination tile {} not found", destinationTileId);
      return;
    }

    logger.info("{} {} -> Moves to {}", player.getName(), description, destinationTileId);

    player.getCurrentTile().leavePlayer(player);
    player.placeOnTile(destinationTile);
    destinationTile.landPlayer(player);

  }
}
