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
  private Player currentPlayer;
  private Dice dice;
  private List<Player> players;


  public  BoardGame() {
    this.board = new Board();
    this.players = new ArrayList<>();
  }

  /**
   * Accessor method for board.
   *
   * @return the board object
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Accessor method for currentPlayer
   *
   * @return currentPlayer
   */
  public Player getCurrentPlayer () {
    return currentPlayer;
  }

  // TODO add addPlayer method
  public void addPlayer(String name) {

  }

  // TODO add createBoard method
  public void createBoard() {

  }

  // TODO add createDicemethod
  public void createDice() {

  }

  // TODO add play method
  public void play() {

  }

  // TODO add getWinner method
  public Player getWinner(){

  }

  public static void main(String[] args) {
    System.out.println("hello world!");
  }
}
