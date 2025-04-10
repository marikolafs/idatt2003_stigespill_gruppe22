package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.engine.BoardGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LadderGameView {

  private BoardGame boardGame;

  Group root = new Group();
  Scene scene = new Scene(root, 300, 300, Color.OLIVE);

  public LadderGameView() {

  }

}
