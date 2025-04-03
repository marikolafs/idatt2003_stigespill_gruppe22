package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;

public class BoardGameFactory {

  public static BoardGame createLadderGame(int rows, int columns) throws Exception {
    BoardGame game = createGame("Ladder game", rows, columns);
    Board board = game.getBoard();

    board.addLadder(1, 2);
    return game;
  }

  private static BoardGame createGame(String gameName, int rows, int columns) throws Exception {
    BoardGame game = BoardGame.getInstance("default name", "default description");
    game.createBoard(rows, columns);
    game.createDice(2);
    System.out.println("Created game: " + gameName + " with " + (rows * columns) + " tiles.");
    return game;
  }
}

