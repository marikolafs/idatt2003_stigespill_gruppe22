package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.model.actions.TileAction;

public class BoardGameFactory {
  private static TileAction ladderAction;

  public static BoardGame createLadderGame(int tiles) throws Exception {
    BoardGame game = createGame("Ladder game", tiles, 2);
    game.setName("Ladder game");
    game.setDescription("Ladder game");
    Tile tile = game.getBoard().getTile(2);
    return game;
  }

  private static BoardGame createGame(String gameName, int tiles, int numberOfDice) throws Exception {
    BoardGame game = BoardGame.getInstance("default name", "default description");
    game.createBoard(tiles);
    game.createDice(numberOfDice);
    System.out.println("Created game: " + gameName + " with " + tiles + " tiles.");
    return game;
  }
}

