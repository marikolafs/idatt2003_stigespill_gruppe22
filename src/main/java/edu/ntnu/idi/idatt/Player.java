package edu.ntnu.idi.idatt;

/**
 * Represents a player in the boardgame. A player has a name and is always located on a tile.
 * A player can be placed on a tile and move a number of steps on the board.
 * When the player reaches or passes the last tile, the player has reached
 * the end of the game (goal).
 */
public class Player {
  final private String name;
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
    this.currentTile = game.getBoard().getTile(); // getting the tile position
  }

  /**
   * Accessor method for currentTile
   * @return the current tile
   */
  public Tile getCurrentTile () {
    return currentTile;
  }

  /**
   * Accessor method for name
   * @return the players name
   */
  public  String getName() {
    return name;
  }




}
/*Representerer en spiller i spillet. En spiller har et navn, og «bor» til enhver tid på et felt. En spiller
kan bli plassert på et felt og kan også bevege seg et antall steg på spillbrettet. Når spilleren når
siste felt eller passerer siste felt, har spilleren nådd slutten av spillet (mål).*/