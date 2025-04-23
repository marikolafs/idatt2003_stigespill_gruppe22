package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.Player;

public class HoldAction implements TileAction {

  private final String descrption;

  public HoldAction(String descrption) {
    this.descrption = descrption;
  }

  public String getDescrption() {
    return descrption;
  }

  @Override
  public void perform(Player player){
    player.setHoldAction(true);
    System.out.println(player.getName() + " holds ");
  }
}
