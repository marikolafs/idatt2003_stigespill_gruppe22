package edu.ntnu.idi.idatt.engine;


import edu.ntnu.idi.idatt.io.BoardFileReaderGson;
import edu.ntnu.idi.idatt.io.PlayerFiles;
import edu.ntnu.idi.idatt.io.BoardFileWriterGson;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.HomeEntryAction;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.observer.events.Event;
import edu.ntnu.idi.idatt.view.PlayerView;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;


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
  private static String gameType;

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
  private List<BoardGameObserver> observers;
  private HomeEntryAction homeEntryAction;
  private static boolean gameWon;
  private static int currentPlayerIndex = 0;

  /**
   * The constructor initializes the board and players list.
   */
  public BoardGame(String name, String description) {
    this.name = name;
    this.description = description;
    this.board = new Board();
    this.players = new ArrayList<>();
    this.observers = new ArrayList<>();
  }

  /**
   * The reset method resets the BoardGame instance
   */
  public static void reset() {
    instance = null;
  }

  /**
   * Accessor method for gameType
   *
   * @return the type of game being played
   */
  public String getGameType() {
    return gameType;
  }

  /**
   * Mutator method for gameType
   *
   * @param gameType the type of game being played
   */
  public void setGameType(String gameType) {
    this.gameType = gameType;
  }

  /**
   * Mutator method for name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Mutator method for description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
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

  public static void setCurrentPlayer(Player player) {
    currentPlayer = player;
  }

  public static void setPlayerView(PlayerView playerView) {
    playerView = playerView;
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
   * Adds a player to the game. The player is added to the list of players if they are not already
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
      notifyObservers(new GameEvent
          (Event.PLAYER_ADDED, player.getName() + " joined the game", player));
    }
  }


  /**
   * Assigns four pieces to a player.
   *
   * @param player the player receiving pieces
   */
  public void giveLudoPieces(Player player) {
    for (int i = 0; i < 4; i++) {
      player.getPieces()
          .add(new Piece(player, board.getStartingTileForPiece(player.getPiece()), false, true));
    }
  }

  /**
   * Creates a new board with the specified number of rows and columns, and adds tiles in a
   * serpentine pattern.
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

    notifyObservers(new GameEvent
        (Event.BOARD_CREATED, "The game board has been created.", null));
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
    this.dice = new Dice(numberOfDice);
    notifyObservers(new GameEvent
        (Event.DICE_CREATED, "The game dice have been created.", null));
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
   * The rollDice method is responsible for handling a turn in a Ladder game. It rolls the dice and
   * moves the current player the appropriate amount of tiles, making sure to check for any
   * potential tileActions on the destination tile
   *
   * @param player the player whose turn it is
   */
  public static void rollDice(Player player) {

    if (player.shouldHold()) {
      System.out.println(player.getName() + " holds ");
      player.setHoldAction(false);
      nextTurn();
    }

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
   * The playLudo method is responsible for managing Ludo game play. It iterates over the players,
   * allowing each player to roll the dice and move on the board. The game concludes when the first
   * player has gotten all their pieces into their home, at which point a winner is decided.
   */
  public void playLudo() {

    System.out.println("playLudo() called");

    if (gameWon) {
      return;
    }

    Player player = players.get(currentPlayerIndex);
    setCurrentPlayer(player);
    notifyObservers(
        new GameEvent(Event.PLAYER_CHANGE, "Player changed to " + player.getName(), player));
  }
  //TODO: remove prints from ludo methods

  /**
   * The handleTurn method is responsible for handling a players turn, it rolls the dice and sees
   * that only active pieces can move. If the player rolls a six, they get a second turn.
   *
   * @param player
   */
  public void handleTurn(Player player) {
    System.out.println("handleTurn() called");

    int diceValue = getDice().roll();

    List<Piece> activePieces = currentPlayer.getPieces().stream().filter(p -> canMove(p, diceValue))
        .toList();

    if (activePieces.isEmpty()) {
      notifyObservers(new GameEvent(Event.NO_MOVES, "No valid moves", currentPlayer));
      System.out.println(player.getName() + " has no active pieces");
      nextLudoTurn();
      return;
    } else if (activePieces.size() == 1) {
      System.out.println(player.getName() + " has 1 active piece moving " + diceValue + " steps");
      movePiece(activePieces.get(0), diceValue);

      //player can go again if they rolled a 6
      if (diceValue != 6) {
        nextLudoTurn();
      }
      return;

    } else {
      System.out.println(
          player.getName() + " has multiple active pieces that can move " + diceValue + " steps");
      List<String> options = new ArrayList<>();
      for (int i = 0; i < activePieces.size(); i++) {
        options.add("Piece " + (i + 1));
      }

      ChoiceDialog<String> dialog = new ChoiceDialog<>(options.get(0), options);
      dialog.setTitle("Choose piece");
      dialog.setHeaderText("Choose which piece to move");
      dialog.setContentText("Which piece do you want to move?");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent()) {
        int index = options.indexOf(result.get());
        Piece chosen = activePieces.get(index);
        movePiece(chosen, diceValue);

        if (diceValue != 6) {
          nextLudoTurn();
        }
      } else {
        System.out.println("No piece selected");
        nextLudoTurn();
      }
      return;
    }
  }

  /**
   * The nextTurn handles updating the current player
   */
  public static void nextTurn() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    play();
  }

  /**
   * The nextLudoTurn method handles updating the current player in Ludo
   */
  public void nextLudoTurn() {
    System.out.println("NextLudoTurn() called");
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    setCurrentPlayer(players.get(currentPlayerIndex)); //kan hende denne bør flyttes?
    playLudo();
  }

  /**
   * The canMove method sees whether a piece can move, when a piece is in start, if a player rolls a
   * six canMove will return true.
   *
   * @param piece     the piece being checked for its ability to move
   * @param diceValue the value of the dice rolled
   * @return whether or not the piece can move
   */
  public boolean canMove(Piece piece, int diceValue) {
    System.out.println("canMove() called");
    if (piece.isHome()) {
      return false;
    }
    if (piece.isInStart()) {
      return diceValue == 6;
    }
    return getDestinationTile(piece, diceValue) != null;
  }

  /**
   * The movePiece method is responsible for moving the piece on the board based on the value rolled
   * on the die.
   *
   * @param piece     the piece being moved
   * @param diceValue the value rolled on the die
   */
  public void movePiece(Piece piece, int diceValue) {
    System.out.println("movePiece() called");
    piece.getPlayer().setCurrentPiece(piece);

    // Move piece to its starting tile when activated
    if (piece.isInStart()) {
      notifyObservers(new GameEvent(Event.PLAYER_MOVED,
          "Players piece moved onto entry tile" + piece.getPlayer().getName(), getCurrentPlayer()));
      System.out.println("Moving " + piece.getPlayer().getName() + " onto entry tile");
      Tile entryTile = board.getTile(
          board.getStartingTileForPiece(piece.getPlayer().getPiece()).getTileId());
      piece.setCurrentTile(entryTile);
      piece.setInStart(false);
      entryTile.getLandAction().perform(piece.getPlayer());
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
    notifyObservers(
        new GameEvent(Event.PLAYER_MOVED, "Players piece moved " + piece.getPlayer().getName(),
            getCurrentPlayer()));
  }

  /**
   * The getDestinationTile method handles finding the tile a piece should move to. It checks for
   * possible tileActions or win conditions being met and handles these if they occur.
   *
   * @param piece     the piece being moved
   * @param diceValue the value rolled on the die
   * @return the tile the piece should move to
   */
  public Tile getDestinationTile(Piece piece, int diceValue) {
    System.out.println("getDestinationTile() called");
    Tile current = piece.getCurrentTile();
    for (int i = 0; i < diceValue; i++) {
      //Check whether a piece has reached its home entry tile.
      if (current.getLandAction() instanceof HomeEntryAction) {
        HomeEntryAction homeAction = (HomeEntryAction) current.getLandAction();
        if (homeAction.getPiece().equalsIgnoreCase(piece.getPlayer().getPiece())) {
          homeAction.perform(piece.getPlayer());
          return piece.getCurrentTile();
        }

      }
      //If there is no next tile, ascertain that the piece is home, and assess whether the game has been won.
      if (current.getNextTile() == null) {
        piece.setHome(true);
        setLudoWinner(piece.getPlayer());
        return null;
      }
      current = current.getNextTile();
    }
    return current;
  }

  /**
   * The setLudoWinner method checks whether a player has gotten all their pieces into their home,
   * and thereby won the game.
   *
   * @param player the player being examined
   */
  public void setLudoWinner(Player player) {
    System.out.println("setLudoWinner() called");
    int homeCount = 0;
    for (Piece piece : player.getPieces()) {
      if (piece.isHome()) {
        homeCount++;
      }
    }
    if (homeCount == 4) {
      gameWon = true;
      player.isWinner(true);
      notifyObservers(new GameEvent(Event.PLAYER_WIN,
          player.getName() + " wins the game!", player));
      notifyObservers(new GameEvent(Event.GAME_END,
          "The game has ended.", null));
    }
  }

  /**
   * Accessor method for the variable gameWon, determines whether the game has been won.
   *
   * @return whether or not the game has been won
   */
  public boolean getGameWon() {
    return gameWon;
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

  /**
   * Responsible for resetting the game state. It clears the players list,
   * resets the board and dice
   */
  public void restartGameWithSavedPlayers() {
    try {
      String playersFilePath = "src/main/resources/players.csv";
      String boardFilePath = "src/main/resources/board.json";

      PlayerFiles playersToFile = new PlayerFiles();
      BoardFileWriterGson boardFileWriter = new BoardFileWriterGson();
      BoardFileReaderGson boardFileReader = new BoardFileReaderGson();
      playersToFile.writePlayersToFile(players);
      boardFileWriter.writeBoard(board);

      if (!new File(playersFilePath).exists() || !new File(boardFilePath).exists()) {
        throw new Exception("Required files not found.");
      }

      dice = new Dice(2);
      gameWon = false;
      currentPlayerIndex = 0;

      players.clear();
      board = boardFileReader.readBoard(Paths.get(boardFilePath));

      PlayerFiles.addPlayersThroughFile(new File(playersFilePath));

      Tile startingTile = board.getStartingTile();
      if (startingTile == null) {
        throw new Exception("Starting tile is not set on the board.");
      }

      for (Player player : players) {
        if (player.getCurrentTile() != null) {
          player.getCurrentTile().leavePlayer(player);
        }
        player.placeOnTile(startingTile);
        startingTile.landPlayer(player);

        notifyObservers(new GameEvent(Event.PLAYER_MOVED,
            player.getName() + " placed on starting tile.", player));
      }

    } catch (Exception e) {
      System.err.println("Failed to restart game: " + e.getMessage());
    }
  }
}
