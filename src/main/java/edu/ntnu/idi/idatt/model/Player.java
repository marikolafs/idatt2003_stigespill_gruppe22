package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.engine.BoardGame;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the boardgame. A player has a name and is always located on a tile. A
 * player can be placed on a tile and move a number of steps on the board. When the player reaches
 * or passes the last tile, the player has reached the end of the game (goal).
 *
 * @version 1.0
 */
public class Player {

  final String name;
  private Tile currentTile;
  private String piece;
  private boolean holdAction = false;
  private List<Piece> pieces = new ArrayList<>();
  private Piece currentPiece;
  private boolean isWinner = false;

  /**
   * Constructs a new Player with the specified name and places the player on the starting tile of
   * the game board.
   *
   * @param name  the name of the player
   * @param game  the BoardGame instance from which the starting tile is retrieved
   * @param piece the piece assigned to the player
   */
  public Player(String name, BoardGame game, String piece) {
    this.name = name;
    this.currentTile = currentTile;
    this.piece = piece;
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
  public String getName() {
    return name;
  }

  /**
   * Accessor method for player piece.
   *
   * @return the player piece
   */
  public String getPiece() {
    return piece;
  }

  /**
   * Accessor method for the players pieces
   *
   * @return a list containing the players pieces
   */
  public List<Piece> getPieces() {
    return pieces;
  }

  public void setPiece(String piece) {
    this.piece = piece;
  }

  public Piece getCurrentPiece() {
    return currentPiece;
  }

  public void setCurrentPiece(Piece currentPiece) {
    this.currentPiece = currentPiece;
  }

  /**
   * Accessor method stating whether a player is set to hold their next turn.
   *
   * @return whether or not a player should hold their next turn.
   */
  public boolean shouldHold() {
    return holdAction;
  }

  /**
   * Mutator method for holdAction, sets whether a player should hold their next turn or not.
   *
   * @param holdAction whether the player should hold their next turn.
   */
  public void setHoldAction(boolean holdAction) {
    this.holdAction = holdAction;
  }

  /**
   * Places the player on the given tile and sets it to the current tile.
   *
   * @param tile the tile which the player will be placed on
   * @throws IllegalArgumentException if tile is null
   */
  public void placeOnTile(Tile tile) {
    if (tile == null) {
      throw new IllegalArgumentException("Tile cannot be null");
    }
    this.currentTile = tile;
  }

  public void isWinner(boolean value) {
    isWinner = value;
  }

  public boolean getIsWinner() {
    return isWinner;
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