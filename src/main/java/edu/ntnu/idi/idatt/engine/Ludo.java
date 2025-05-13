package edu.ntnu.idi.idatt.engine;

import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The Ludo class extends the BoardGame class and is responsible for managing the gameplay for Ludo.
 * It iterates over the players, allowing each player to roll the dice * and move their pieces on
 * the board. The game concludes when the first player gets all their pieces to the last tile, at *
 * which point a winner is decided.
 *
 * @version 1.0
 */
public class Ludo extends BoardGame {

  private List<Player> players;
  private int currentPlayerIndex = 0;
  private boolean gameWon = false;
  private Dice dice;
  private Board board;

  private final List<BoardGameObserver> observers;

  /**
   * The constructor initializes the board and players list. The number of dice is set here.
   */
  public Ludo(String name, String description) {
    super(name, description);
    this.players = new ArrayList<>();
    this.dice = new Dice(1);
    this.currentPlayerIndex = 0;
    this.board = new Board();
    this.observers = new ArrayList<>();
  }


  /**
   * Adds a player to the game. The player is added to the list of players if it is not already
   * registered.
   *
   * @param player the player to be added
   */
  //TODO: update to create 4 pieces for each player added and add them to their starting area.
  public void addPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (!players.contains(player)) {
      players.add(player);
      notifyObservers(
          new GameEvent("player_joined", player.getName() + " joined the game", player));
    }
  }

  /**
   * The play method is responsible for managing the game play. It iterates over the players, *
   * allowing each player to roll the dice and move their pieces on the board. The game concludes
   * when the first * player gets all their pieces to the last tile, at which point a winner is
   * decided.
   */
  public void play() {

    if (gameWon) {
      return;
    }

    Player player = players.get(currentPlayerIndex);
    handleTurn(player);
    notifyObservers(
        new GameEvent("player_change", "Player changed to " + player.getName(), player));
  }

  /**
   * The handleTurn method is responsible for overseeing a players turn. It rolls the dice, updates
   * the list of a players active pieces, checks which piece should be moved, and moves over to the
   * next turn if the player did not roll a 6.
   *
   * @param player the player whose turn it is
   */
  public void handleTurn(Player player) {
    int diceValue = dice.roll();
    List<Piece> activePieces = player.getPieces().stream().filter(p -> canMove(p, diceValue))
        .toList();

    if (activePieces.isEmpty()) {
      nextTurn();
      return;
    } else if (activePieces.size() == 1) {
      movePiece(activePieces.get(0), diceValue);
      return;
    }

    // Player can go again if they rolled a 6
    if (diceValue != 6) {
      nextTurn();
    }
  }

  /**
   * The nextTurn method is responsible for moving to the next player's turn. It updates the
   * currentPlayerIndex and calls the play method to start the next player's turn.
   */
  private void nextTurn() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    play();
  }

  /**
   * Checks whether a players piece should be able to move, depending on whether or not the player
   * has rolled a 6.
   *
   * @param piece     the piece being checked for whether it can move
   * @param diceValue the value of the players roll
   * @return whether the piece can move
   */
  public boolean canMove(Piece piece, int diceValue) {
    if (piece.isHome()) {
      return false;
    }
    if (piece.isInStart()) {
      return diceValue == 6;
    }
    return getDestinationTile(piece, diceValue) != null;
  }

  /**
   * The movePiece method is responsible for moving pieces on the board. If a piece is in the
   * starting area it moves them onto the board, otherwise it moves them to their destination,
   * provided that the destination tile is not set to null.
   *
   * @param piece     the piece being moved
   * @param diceValue the amount of steps the piece will take
   */
  public void movePiece(Piece piece, int diceValue) {

    // Move piece to its starting tile when activated
    if (piece.isInStart()) {
      Tile entryTile = getEntryTileFor(piece);
      piece.setCurrentTile(entryTile);
      entryTile.addPiece(piece);
      piece.setInStart(false);
      return;
    }

    Tile destination = getDestinationTile(piece, diceValue);
    if (destination == null) {
      throw new IllegalStateException("Destination tile is null");
    }

    Tile current = piece.getCurrentTile();
    if (current != null) {
      current.removePiece(piece);
    }

    destination.addPiece(piece);
    piece.setCurrentTile(destination);
  }

  /**
   * the getDestinationTile method sets the destination of a piece, depending on what value was
   * rolled on the dice.
   *
   * @param piece     the piece being moved
   * @param diceValue the value rolled
   * @return the destination for the piece
   */
  //TODO: sjekk om det går ann å knytte denne opp mot move i Player
  public Tile getDestinationTile(Piece piece, int diceValue) {
    Tile current = piece.getCurrentTile();
    for (int i = 0; i < diceValue; i++) {
      if (current.getNextTile() == null) {
        return null;
      }
      current = current.getNextTile();
    }
    return current;
  }

  /**
   * Finds the first tile for a piece.
   *
   * @param piece the piece the tile belongs to
   * @return the first tile for the piece
   */
  private Tile getEntryTileFor(Piece piece) {
    return board.getEntryTileFor(piece.getPlayer());
  }
}
