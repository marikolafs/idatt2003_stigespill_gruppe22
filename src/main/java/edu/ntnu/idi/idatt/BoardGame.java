package edu.ntnu.idi.idatt;

/**
 * The BoardGame class is responsible for setting up the game board and dice, registering players,
 * and managing the gameplay. It iterates over the players, allowing each player to roll the dice
 * and move on the board. The game concludes when the first player reaches the last tile (goal),
 * at which point a winner is decided.
 */
public class BoardGame {
  private Board board;


  public  BoardGame() {
    this.board = new Board();
  }

  /**
   * Accessor method for board.
   *
   * @return the board object
   */
  public Board getBoard() {
    return board;
  }
}
