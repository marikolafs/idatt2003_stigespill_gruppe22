package edu.ntnu.idi.idatt.model.actions;

import edu.ntnu.idi.idatt.model.Player;

public class FinalStretchAction implements TileAction {

  private final String description;
  private final String piece;

  public FinalStretchAction(String description, String color) {
    this.description = description;
    this.piece = color;
  }

  public String getDescription() {
    return description;
  }

  public String getPiece() {
    return piece;
  }

  public String setPiece(String piece) {
    return piece;
  }

  @Override
  public void perform(Player player) {

  }
}
