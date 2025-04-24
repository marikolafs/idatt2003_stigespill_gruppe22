package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.engine.Dice;
import edu.ntnu.idi.idatt.view.PlayerView;
import javafx.scene.control.Button;

public class PlayerController {
  private final Dice dice;
  private final PlayerView playerView;

  public PlayerController(Dice dice, PlayerView playerView) {
    this.dice = dice;
    this.playerView = playerView;

    Button rollButton = playerView.getRollButton();
    rollButton.setOnAction(e-> rollDice());

  }

  public void rollDice() {
    int diceValue = dice.roll();
    playerView.setDiceRollValue(diceValue);

    BoardGame.play();
  }
}
