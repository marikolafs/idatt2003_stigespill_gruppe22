
package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class LadderAction implements TileAction {

  private Tile ladderStart;
  private Tile ladderEnd;

  public LadderAction(Tile ladderStart, Tile ladderEnd) {
    this.ladderStart = ladderStart;
    this.ladderEnd = ladderEnd;
  }

  public Tile getLadderStart() {
    return ladderStart;
  }

  public Tile getLadderEnd() {
    return ladderEnd;
  }

  public void createLadders(){
    ArrayList<LadderAction> ladder = new ArrayList<>();
    ladder.add(new ladder(4, 12));
    ladder.add(new ladder(84, 55));

  }

  @Override
  public void perform(Player player) {
    if(player.getCurrentTile().equals(ladderStart)) {
      player.placeOnTile(ladderEnd);
    }
  }
}
