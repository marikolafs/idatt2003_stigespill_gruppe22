package edu.ntnu.idi.idatt;

import java.util.List;
import java.util.*;

/**
 * The BoardGame class is responsible for setting up the game board and dice, registering players,
 * and managing the gameplay. It iterates over the players, allowing each player to roll the dice
 * and move on the board. The game concludes when the first player reaches the last tile (goal),
 * at which point a winner is decided.
 */
public class BoardGame {
  private Board board;

  private List<Player> players = new ArrayList<>();
  Tile startingTile = board.getTile(0);

  public  BoardGame() {
    this.board = new Board();
  }

  public void addPlayer(String name) {
    players.add(new Player(name, startingTile));
  }

  public void play() {

  }

  /**
   * Accessor method for board.
   *
   * @return the board object
   */
  public Board getBoard() {
    return board;
  }

  public static void main(String[] args) {
    System.out.println("hello world!");
  }
}
