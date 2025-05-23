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

    Button ladder90Button = new Button("LadderGame90");
    ladder90Button.setOnAction(e -> {
      try {
        ladderGameView.selectPlayerSetupView(stage, "LadderGame90");
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

    Button ladder90PlusButton = new Button("LadderGame90+");
    ladder90PlusButton.setOnAction(e -> {
      try {
        ladderGameView.selectPlayerSetupView(stage, "LadderGame90Plus");
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button ladder45Button = new Button("LadderGame45");
    ladder45Button.setOnAction(e -> {
      try {
        ladderGameView.selectPlayerSetupView(stage, "LadderGame45");
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });
  }
}
