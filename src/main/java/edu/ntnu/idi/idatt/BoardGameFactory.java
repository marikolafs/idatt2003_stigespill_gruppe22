package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;

public class BoardGameFactory {

  private static BoardGame createGame(String gameName, String gameDescription, int numberOfDice,
      int columns, int rows)
      throws Exception {
    BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
    game.setName(gameName);
    game.setDescription(gameDescription);
    game.createBoard(columns, rows);
    game.createDice(numberOfDice);
    System.out.println("Created game: " + gameName + " with " + rows * columns + " tiles.");
    return game;
  }

  public static BoardGame createLadderGame90() throws Exception {
    BoardGame game = createGame("Ladder game 90", "Ladder game with 90 tiles", 2, 9, 10);
    game.getBoard().addLadder(4, 14);
    game.getBoard().addLadder(9, 31);
    game.getBoard().addLadder(20, 42);
    game.getBoard().addLadder(27, 84);
    game.getBoard().addLadder(51, 67);
    game.getBoard().addLadder(73, 86);
    return game;
  }

  public static BoardGame createLadderGame45() throws Exception {
    BoardGame game = createGame("Ladder game 45", "Ladder game with 45 tiles", 2, 5, 9);
    game.getBoard().addLadder(4, 14);
    game.getBoard().addLadder(9, 31);
    game.getBoard().addLadder(20, 42);
    return game;
  }
}

