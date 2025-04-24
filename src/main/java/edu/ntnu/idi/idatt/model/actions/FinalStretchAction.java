package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.Player;

public class FinalStretchAction implements TileAction {

  private final String color;

  public FinalStretchAction(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public String setColor(String color) {
    return color;
  }

  @Override
  public void perform(Player player) {

  }
}
