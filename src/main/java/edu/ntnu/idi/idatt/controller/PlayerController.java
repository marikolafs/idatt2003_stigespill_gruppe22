package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.SceneManager;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.engine.Dice;
import edu.ntnu.idi.idatt.model.Piece;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.events.Event;
import edu.ntnu.idi.idatt.view.PlayerView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The PlayerController class handles the player actions in the game.
 * It is responsible for managing the player's interactions with the game.
 */
public class PlayerController {
  private PlayerView playerView;
  private Player player;
  private SceneManager sceneManager;

  private static final BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());

  /**
   * Initializes the PlayerController with Dice and PlayerView.
   * It sets up the roll button action to roll the dice for the current player.
   *
   * @param dice       the dice to be rolled
   * @param playerView the view to be updated
   */
  public PlayerController(Dice dice, PlayerView playerView, String gameType, Stage stage) {
    this.sceneManager = SceneManager.getInstance(stage);
    BoardGame.setPlayerView(playerView);
    Player player = game.getCurrentPlayer();

    if (player == null) {
      throw new IllegalStateException("Current player is not initialized.");
    }

    // Set initial player details
    playerView.setPlayerName(player.getName());
    playerView.setPieceImage(player.getPiece());

    Button mainMenuButton = playerView.getMainMenuButton();
    Button restartButton = playerView.getRestartButton();
    Button rollButton = playerView.getRollButton();

    rollButton.setOnAction(e -> {
      System.out.println("Roll button pressed");
      if (gameType.equals("LadderGame90") || gameType.equals("LadderGame45") || gameType.equals("LadderGame90Plus")) {
        BoardGame.rollDice(game.getCurrentPlayer());
        int dice1Value = game.getDice().getDie(1);
        int dice2Value = game.getDice().getDie(2);
        playerView.setDiceImages(dice1Value, dice2Value);
        playerView.setPieceImage(player.getPiece());
      } else if (gameType.equals("Ludo")) {
        System.out.println("Ludo should play");
        game.handleTurn(game.getCurrentPlayer());
      }
    });

    mainMenuButton.setOnAction(event -> {

      Scene welcomeScene = sceneManager.getScene("Welcome");
      sceneManager.switchScene(welcomeScene, "Welcome");
    });

  }
}
