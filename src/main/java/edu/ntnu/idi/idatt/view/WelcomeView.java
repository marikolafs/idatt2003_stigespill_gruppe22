package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.controller.LadderGameController;
import edu.ntnu.idi.idatt.controller.WelcomeController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeView {

  private final VBox root = new VBox(10);
  private final Button ladderButton = new Button("Ladder Game");
  private final Button ludoButton = new Button("Ludo");

  public WelcomeView() {
    root.setAlignment(Pos.CENTER);
    root.getChildren().addAll(new Label("Welcome!"),ladderButton, ludoButton);
  }

  public VBox getView() {
    return root;
  }

  public Button getLadderButton() {
    return ladderButton;
  }

  public Button getLudoButton() {
    return ludoButton;
  }
}
