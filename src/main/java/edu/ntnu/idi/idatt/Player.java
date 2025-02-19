package edu.ntnu.idi.idatt;

/**
 * Represents a player in the boardgame. A player has a name and is always located on a tile.
 * A player can be placed on a tile and move a number of steps on the board.
 * When the player reaches or passes the last tile, the player has reached
 * the end of the game (goal).
 */
public class Player {
  final String name;
  private Tile currentTile;
  BoardGame boardGame;

  /**
   * Constructs a new Player with the specified name and places the player on the
   * starting tile of the game board.
   *
   * @param name the name of the player
   * @param game the BoardGame instance from which the starting tile is retrieved
   */
  public Player(String name, Tile currentTile) {
    this.name = name;
    this.currentTile = boardGame.getBoard().getTile(); // getting the tile position
  }

  /**
   * Accessor method for currentTile.
   *
   * @return the current tile
   */
  public Tile getCurrentTile() {
    return currentTile;
  }

  /**
   * Accessor method for name.
   *
   * @return the players name
   */
  public  String getName() {
    return name;
  }

  /**
   * Places the player on the given tile and sets it to the current tile.
   *
   * @param tile the tile which the player will be placed on
   */
  public void placeOnTile(Tile tile) {
    this.currentTile = tile;
  }

}