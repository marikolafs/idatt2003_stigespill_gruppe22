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
    game.getBoard().addLadder(2, 38);
    game.getBoard().addLadder(9, 11);
    game.getBoard().addLadder(26, 6);
    game.getBoard().addLadder(27, 47);
    game.getBoard().addLadder(44, 4);
    game.getBoard().addLadder(46, 74);
    game.getBoard().addLadder(53, 71);
    game.getBoard().addLadder(59, 40);
    game.getBoard().addLadder(65, 86);
    game.getBoard().addLadder(70, 31);
    game.getBoard().addLadder(79, 14);
    game.getBoard().addLadder(84, 63);
    return game;
  }

  public static BoardGame createLadderGame90Plus() throws Exception {
    BoardGame game = createLadderGame90();
    game.setName("Ladder game 90+");
    game.setDescription("Ladder game with 90 tiles and added obstacles.");
    game.getBoard().addReturn(5);
    game.getBoard().addReturn(18);
    game.getBoard().addReturn(23);
    game.getBoard().addReturn(49);
    game.getBoard().addReturn(75);
    game.getBoard().addHold(21);
    game.getBoard().addHold(33);
    game.getBoard().addHold(58);
    game.getBoard().addHold(88);
    return game;
  }

  public static BoardGame createLadderGame45() throws Exception {
    BoardGame game = createGame("Ladder game 45", "Ladder game with 45 tiles", 2, 9, 5);
    game.getBoard().addLadder(4, 14);
    game.getBoard().addLadder(9, 31);
    game.getBoard().addLadder(20, 42);
    return game;
  }
}

