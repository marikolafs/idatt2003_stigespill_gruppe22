package edu.ntnu.idi.idatt.engine;


import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.observer.events.Event;
import edu.ntnu.idi.idatt.view.PlayerView;
import java.util.ArrayList;
import java.util.List;


/**
 * The BoardGame class is responsible for setting up the game board and dice, registering players,
 * and managing the gameplay. It iterates over the players, allowing each player to roll the dice
 * and move on the board. The game concludes when the first player reaches the last tile (goal), at
 * which point a winner is decided.
 *
 * @version 2.0
 */
public class BoardGame extends Observable {

  private static BoardGame instance;
  private static String name;
  private static String description;
  private static boolean rollButtonPressed;

  /**
   * The getInstance method is a singleton pattern implementation. It ensures that only one
   * instance of the BoardGame class is created.
   *
   * @param name the name of the game
   * @param description the description of the game
   * @return the single instance of BoardGame
   */
  public static BoardGame getInstance(String name, String description) {
    if (instance == null) {
      instance = new BoardGame("default name", "default description");
    }
    return instance;
  }

  private static Board board;
  private static Player currentPlayer;
  private static Dice dice;
  private static List<Player> players;
  private final List<BoardGameObserver> observers;

  /**
   * The constructor initializes the board and players list.
   */
  public BoardGame(String name, String description) {
    BoardGame.name = name;
    BoardGame.description = description;
    board = new Board();
    players = new ArrayList<>();
    this.observers = new ArrayList<>();
  }

  /**
   * Mutator method for name.
   *
   * @param name the name
   */
  public void setName(String name) {
    BoardGame.name = name;
  }

  /**
   * Mutator method for description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    BoardGame.description = description;
  }

  public static void setCurrentPlayer(Player player) {
    currentPlayer = player;
  }

  public static void setPlayerView(PlayerView playerView) {
    playerView = playerView;
  }

  /**
   * Accessor method for name.
   *
   * @return the name
   */
  public static String getName() {
    return name;
  }

  /**
   * Accessor method for description.
   *
   * @return the description
   */
  public static String getDescription() {
    return description;
  }

  /**
   * Accessor method for board.
   *
   * @return the board object
   */
  public static Board getBoard() {
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
   * Adds a player to the game. The player is added to the list of players if it is not already
   * registered.
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
      notifyObservers(new GameEvent(Event.PLAYER_ADDED,
          player.getName() + " joined the game", player));
    }
  }

  /**
   * Creates a new board with the specified number of rows and columns.
   *
   * @param rows and columns the number of rows and columns the board should contain.
   * @return null
   * @throws IllegalArgumentException if the number of rows or columns is less than or equal to 0.
   */
  public Board createBoard(int columns, int rows) {

    if (rows <= 0 || columns <= 0) {
      throw new IllegalArgumentException("Number of rows and columns must be greater than 0");
    }
    board = new Board(); // Resets the board
    board.setRows(rows);
    board.setColumns(columns);

    int tileId = 1;

    for (int r = rows - 1; r >= 0; r--) {
      boolean leftToRight = (rows - 1 - r) % 2 == 0;

      if (leftToRight) {
        for (int c = 0; c < columns; c++) {
          Tile tile = new Tile(tileId++, null, c, r);
          board.addTile(tile);
        }
      } else {
        for (int c = columns - 1; c >= 0; c--) {
          Tile tile = new Tile(tileId++, null, c, r);
          board.addTile(tile);
        }
      }
    }

    Tile startingTile = board.getTile(1);
    board.setStartingTile(startingTile);
    Tile goalTile = board.getTile(rows * columns);
    board.setGoalTile(goalTile);

    notifyObservers(new GameEvent(Event.BOARD_CREATED, "The game board has been created.", null));
    return null;
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
    dice = new Dice(numberOfDice);
    notifyObservers(new GameEvent(Event.DICE_CREATED,
        "The game dice have been created.", null));
  }

  private static int currentPlayerIndex = 0;
  private static boolean gameWon = false;

  /**
   * Accessor method for gameWon.
   *
   * @return gameWon
   */
  public static boolean setGameWon(boolean value) {
    return gameWon;
  }

  /**
   * The play method is responsible for managing the game play. It iterates over the players,
   * allowing each player to roll the dice and move on the board. The game concludes when the first
   * player reaches the last tile (goal), at which point a winner is decided.
   */
  public static void play() {

    if (gameWon) {
      return;
    }

    Player player = players.get(currentPlayerIndex);
    setCurrentPlayer(player);


    notifyObservers(
        new GameEvent(Event.PLAYER_CHANGE, "Player changed to " + player.getName(), player));
  }

  /**
   * The rollDice method is responsible for rolling the dice and moving the player on the board.
   * It updates the players position, triggers any actions associated with the new tile
   * and checks for a winner.
   *
   * @param player the player who is rolling the dice
   */
  public static void rollDice(Player player) {

    int diceValue = dice.roll();

    Tile currentTile = player.getCurrentTile();
    currentTile.leavePlayer(player);

    player.move(diceValue);
    Tile newTile = player.getCurrentTile();
    newTile.landPlayer(player);

    player.placeOnTile(newTile);

    notifyObservers(new GameEvent(Event.PLAYER_MOVED,
        player.getName() + " moved to tile " + newTile.getTileId(), player));

    if (newTile.getLandAction() != null) {
      newTile.getLandAction().perform(player);
      notifyObservers(new GameEvent(Event.TILE_ACTION,
          player.getName() + " triggered an action on tile "
              + newTile.getTileId(), player));
    }

    if (newTile.getTileId() >= getBoard().getGoalTile().getTileId()) {
      notifyObservers(new GameEvent(Event.PLAYER_WIN,
          player.getName() + " wins the game!", player));
      notifyObservers(new GameEvent(Event.GAME_END,
          "The game has ended.", null));
      gameWon = true;
      System.out.println(getWinner().getName() + " won the game!");
      return;
    }

    nextTurn();
  }

  /**
   * The nextTurn method is responsible for moving to the next player's turn. It updates the
   * currentPlayerIndex and calls the play method to start the next player's turn.
   */
  public static void nextTurn() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    play();
  }

  /**
   * The getWinner method is responsible for determining the winner of the game. It iterates over
   * the players and checks if the player has reached the goal tile.
   *
   * @return the player who has reached the goal tile first
   */
  public static Player getWinner() {
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
}
