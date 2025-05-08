package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.engine.Dice;
import edu.ntnu.idi.idatt.view.LadderGameView;
import edu.ntnu.idi.idatt.view.PlayerView;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LadderGameController {

  private static Dice dice;
  public static LadderGameView ladderGameView;
  private static final BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());

  public LadderGameController(Stage stage) {

    Button ladderButton = new Button("LadderGame");
    ladderButton.setOnAction(e -> {
      try {
        ladderGameView.selectPlayerSetupView(stage, "LadderGame");
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button ludoButton = new Button("Ludo");
    ludoButton.setOnAction(e -> {
      try {
        ladderGameView.selectPlayerSetupView(stage, "Ludo");
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

  }
}
