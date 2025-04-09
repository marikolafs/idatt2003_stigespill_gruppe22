package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.TileAction;

public class BoardGameFactory {

  private static TileAction ladderAction;

  public static BoardGame createLadderGame(int columns, int rows) throws Exception {
    BoardGame game = createGame("Ladder game", 2, columns, rows);
    game.setName("Ladder game");
    game.setDescription("Ladder game");
    Tile tile = game.getBoard().getTile(2);
    return game;
  }

  private static BoardGame createGame(String gameName, int numberOfDice, int columns, int rows)
      throws Exception {
    BoardGame game = BoardGame.getInstance("default name", "default description");
    game.createBoard(columns, rows);
    game.createDice(numberOfDice);
    System.out.println("Created game: " + gameName + " with " + rows * columns + " tiles.");
    return game;
  }
}

