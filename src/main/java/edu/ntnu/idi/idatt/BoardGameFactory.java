package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Engine.BoardGame;
import edu.ntnu.idi.idatt.Model.Board;

public class BoardGameFactory {

  public static BoardGame createLadderGame(int tiles) throws Exception {
    BoardGame game = createGame("Ladder game", tiles);
    Board board = game.getBoard();

    board.addLadder(1, 2);
    return game;
  }

  private static BoardGame createGame(String gameName, int tiles) throws Exception {
    BoardGame game = BoardGame.getInstance();
    game.createBoard(tiles);
    game.createDice(2);
    System.out.println("Created game: " + gameName + " with " + tiles + " tiles.");
    return game;
  }
}

