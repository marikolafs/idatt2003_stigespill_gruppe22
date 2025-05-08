package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.SceneManager;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.view.PlayerSetupView;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class PlayerSetupController {

  private final SceneManager sceneManager;
  private BoardGame game = BoardGame.getInstance(BoardGame.getName(), BoardGame.getDescription());
  private BoardGameFactory boardGameFactory = new BoardGameFactory();

  public PlayerSetupController(Stage stage, SceneManager sceneManager, String gameType) {
    this.sceneManager = sceneManager;
    PlayerSetupView view = new PlayerSetupView(gameType);
    Scene scene = new Scene(view.getLayout(), 400, 400);

    List<String> availablePieces = List.of("Pig", "Cat", "Rabbit", "Chicken", "Sheep");

    view.getPlayerComboBox().setOnAction(e -> {
      int count = (int) view.getPlayerComboBox().getValue();
      view.addPlayerFields(count, availablePieces);
    });

    view.getStartButton().setOnAction(e -> {
      List<Player> players = new ArrayList<>();
      Set<String> usedPieces = new HashSet<>();

      if (gameType == "LadderGame"){
        try {
          boardGameFactory.createLadderGame90();
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      } else if (gameType == "Ludo"){
        try {
          boardGameFactory.createLadderGame45();
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }

      for (int i = 0; i < view.getNameFields().size(); i++) {
        String name = view.getNameFields().get(i).getText().trim();
        String piece = view.getPieceFields().get(i).getValue();

        if (name.isEmpty() || piece == null || usedPieces.contains(piece)) {
          new Alert(Alert.AlertType.ERROR, "All players must have unique pieces", ButtonType.OK).show();
          return;
        }

        usedPieces.add(piece);
        game.addPlayer(new Player(name, game, piece));
      }

      game.play();
      try {
        new GameBoardController(stage, sceneManager);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    sceneManager.switchScene(scene, "Player Setup");
  }
}
