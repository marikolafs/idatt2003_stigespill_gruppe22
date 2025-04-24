package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.engine.Dice;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.events.Event;
import edu.ntnu.idi.idatt.view.PlayerView;
import java.util.List;
import javafx.scene.control.Button;

public class PlayerController {

  private static Dice dice;
  public static PlayerView playerView;
  private static final BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());

  public PlayerController(Dice dice, PlayerView playerView) {
    this.dice = dice;
    this.playerView = playerView;

    game.setPlayerView(playerView);

    Button rollButton = playerView.getRollButton();
    rollButton.setOnAction(e -> game.rollDice(game.getCurrentPlayer()));
  }
}
