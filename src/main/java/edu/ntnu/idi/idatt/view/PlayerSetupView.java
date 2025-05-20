package edu.ntnu.idi.idatt.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerSetupView {

  private final VBox layout = new VBox(15);
  private final ComboBox<Integer> playerComboBox = new ComboBox<>();
  private final Button startButton = new Button("Start");

  private final List<TextField> nameFields = new ArrayList<>();
  private final List<ComboBox<String>> pieceFields = new ArrayList<>();

  public PlayerSetupView(String gameType) {
    startButton.setStyle("-fx-background-color:OLIVE;");
    layout.setPadding(new Insets(20));

    Label label = new Label("How many players should be added?");
    if (gameType.equals("LadderGame")) {
      playerComboBox.getItems().addAll(2, 3, 4, 5);
    } else if (gameType.equals("Ludo")) {
      playerComboBox.getItems().addAll(2, 3, 4);
    }

    playerComboBox.setValue(2);

    layout.getChildren().addAll(label, playerComboBox, startButton);
  }

  public void addPlayerFields(int count, List<String> availablePieces) {
    layout.getChildren().removeIf(node -> node instanceof VBox);
    nameFields.clear();
    pieceFields.clear();

    for (int i = 0; i < count; i++) {
      TextField nameField = new TextField();
      ComboBox<String> pieceBox = new ComboBox<>();
      pieceBox.getItems().addAll(availablePieces);

      VBox playerBox = new VBox(5, new Label("Player " + (i + 1)),  nameField, pieceBox);
      layout.getChildren().add(playerBox);

      nameFields.add(nameField);
      pieceFields.add(pieceBox);
    }
  }

  public VBox getLayout() {
    return layout;
  }

  public Button getStartButton() {
    return startButton;
  }

  public ComboBox getPlayerComboBox() {
    return playerComboBox;
  }

  public List<TextField> getNameFields() {
    return nameFields;
  }

  public List<ComboBox<String>> getPieceFields() {
    return pieceFields;
  }


}
