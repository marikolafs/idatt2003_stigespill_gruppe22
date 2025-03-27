package edu.ntnu.idi.idatt.model.actions;


import edu.ntnu.idi.idatt.model.Player;

/**
 * The TileAction interface specifies a standard interface for various types of actions
 * that can be performed on a player landing on a tile. Concrete actions must implement
 * this interface.
 * For example, a LadderAction object might be associated with tile 23, with a destination
 * tile 10. When a player lands on tile 23, the action associated with the tile will be
 * performed on the player, which in this case is to move the player to tile 10 (i.e., the
 * player falls down the ladder from tile 23 to tile 10).
 */
public interface TileAction {

  void perform(Player player);
}

