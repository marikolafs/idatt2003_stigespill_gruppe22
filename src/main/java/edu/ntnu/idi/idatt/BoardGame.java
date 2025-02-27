package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;



/**
 * The BoardGame class is responsible for setting up the game board and dice, registering players,
 * and managing the gameplay. It iterates over the players, allowing each player to roll the dice
 * and move on the board. The game concludes when the first player reaches the last tile (goal),
 * at which point a winner is decided.
 *
 * @version 1.2
 */
public class BoardGame {

  private Board board;
  private Player currentPlayer;
  private Dice dice;
  private List<Player> players;

  /**
   * The constructor initializes the board and players list.
   */
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
   * Accessor method for currentPlayer.
   *
   * @return currentPlayer
   */
  public Player getCurrentPlayer() {
    return currentPlayer;

  }

  /**
   * Accessor method for players.
   *
   * @return players
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Accessor method for dice.
   *
   * @return dice
   */
  public Dice getDice() {
    return dice;
  }

  /**
   * Adds a player to the game.
   * The player is added to the list of players if it is not already registered.
   *
   * @param player the player to be added
   * @throws IllegalArgumentException if player is null
   */
  public void addPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (!players.contains(player)) {
      players.add(player);
      Tile startingTile = board.getStartingTile();
      player.placeOnTile(startingTile);
      startingTile.landPlayer(player);
    }

  }

  /**
   * Creates a new board with the specified number of tiles.
   *
   * @param tiles the number of tiles to be added to the board
   * @throws IllegalArgumentException if the number of tiles is less than or equal to 0
   */
  public void createBoard(int tiles) {
    if (tiles <= 0) {
      throw new IllegalArgumentException("Number of tiles must be greater than 0");
    }
    Tile startingTile = new Tile(1,null);
    board.setStartingTile(startingTile);
    board.addTile(startingTile);

    for (int i = 2; i < tiles; i++) {
      Tile tile = new Tile(i,null);
      board.addTile(tile);
    }
    Tile goalTile = new Tile(tiles,null);
    board.setGoalTile(goalTile);
    board.addTile(goalTile);

  }

  /**
   * Creates new dice with the specified number of die objects.
   *
   * @param numberOfDice the number of die objects to be created
   * @throws IllegalArgumentException if the number of dice is less than or equal to 0
   */
  public void createDice(int numberOfDice) {
    if (numberOfDice <= 0) {
      throw new IllegalArgumentException("Number of dice must be greater than 0");
    }
    this.dice = new Dice(numberOfDice);
  }

  /**
   * The play method is responsible for managing the game play. It iterates over the players,
   * allowing each player to roll the dice and move on the board. The game concludes when the
   * first player reaches the last tile (goal), at which point a winner is decided.
   */
  public void play() {
    boolean gameWon = false;
    while (!gameWon) {
      for (Player player : players) {
        currentPlayer = player;
        Tile currentTile = player.getCurrentTile();

        int steps = dice.roll();
        player.move(steps);

        currentTile.leavePlayer(player);

        Tile newTile = player.getCurrentTile();
        newTile.landPlayer(player);
        currentPlayer.placeOnTile(newTile);

        if (newTile.getTileId() >= board.getGoalTile().getTileId()) {
          gameWon = true;
          break;
        }
      }

    }

  }



  /**
   * The getWinner method is responsible for determining the winner of the game.
   * It iterates over the players and checks if the player has reached the goal tile.
   *
   * @return the player who has reached the goal tile first
   */
  public Player getWinner() {
    Player winner = null;
    for (Player player : players) {
      currentPlayer = player;
      if (currentPlayer.getCurrentTile().getTileId() >= board.getGoalTile().getTileId()) {
        winner = currentPlayer;
        break;
      }
    }
    return winner;

  }

  public static void main(String[] args) {
    System.out.println("hello world!");
  }
}
