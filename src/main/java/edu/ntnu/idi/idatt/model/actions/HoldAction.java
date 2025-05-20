package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.Player;

/**
 * Represents a hold action in the board game, which makes the player hold their turn for one round
 * when triggered. This class implements the TileAction interface and can be set as a landing action
 * on game tiles. When a player lands on a tile with a HoldAction, their turn will be skipped in the
 * following round.
 */
public class HoldAction implements TileAction {

  private final String descrption;

  public HoldAction(String descrption) {
    this.descrption = descrption;
  }

  /**
   * Accessor method for the hold actions description.
   *
   * @return the description of the hold action.
   */
  public String getDescrption() {
    return descrption;
  }

  @Override
  public void perform(Player player) {
    player.setHoldAction(true);
    System.out.println(player.getName() + " holds ");
  }
}
