package edu.ntnu.idi.idatt;

/**
 * Represents a player in the boardgame. A player has a name and is always located on a tile.
 * A player can be placed on a tile and move a number of steps on the board.
 * When the player reaches or passes the last tile, the player has reached
 * the end of the game (goal).
 *
 * @version 1.0
 */
public class Player {
  final String name;
  private Tile currentTile;

  /**
   * Constructs a new Player with the specified name and places the player on the
   * starting tile of the game board.
   *
   * @param name the name of the player
   * @param game the BoardGame instance from which the starting tile is retrieved
   */
  public Player(String name, BoardGame game) {
    this.name = name;
    this.currentTile = game.getBoard().getTile(1); // getting the tile position
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

// TODO add exception for last tile
  /**
   * Moves the player a number of steps on the board. The player is moved to the next tile if it is
   * not null.
   *
   * @param steps the steps the player will be moved
   */
  public void move(int steps) {
    for (int i = 0; i < steps; i++) {
      if (currentTile.getNextTile() != null) {
        currentTile = currentTile.getNextTile();
      }
    }
  }





}