package edu.ntnu.idi.idatt.model.actions;


import edu.ntnu.idi.idatt.model.Player;

/**
 * The TileAction interface specifies a standard interface for various types of actions
 * that can be performed on a player landing on a tile. Concrete actions must implement
 * this interface.
 */
public interface TileAction {

  /**
   * Performs the action associated with the tile on the specified player.
   *
   * @param player the player who the tile action is performed on
   */
  void perform(Player player);
}

