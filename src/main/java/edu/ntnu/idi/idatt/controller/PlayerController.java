package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.engine.Dice;
import edu.ntnu.idi.idatt.view.PlayerView;
import javafx.scene.control.Button;

/**
 * The PlayerController class handles the player actions in the game.
 * It is responsible for managing the player's interactions with the game.
 */
public class PlayerController {

  private static final BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());

  /**
   * Initializes the PlayerController with Dice and PlayerView.
   * It sets up the roll button action to roll the dice for the current player.
   *
   * @param dice the dice to be rolled
   * @param playerView the view to be updated
   */
  public PlayerController(Dice dice, PlayerView playerView) {
    BoardGame.setPlayerView(playerView);


    Button rollButton = playerView.getRollButton();
    rollButton.setOnAction(e -> {
      BoardGame.rollDice(game.getCurrentPlayer());
      playerView.setDiceRollValue(game.getDice().getLastRolledValue());
    });
  }
}
