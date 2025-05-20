package edu.ntnu.idi.idatt.view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeView {

  private final VBox root = new VBox(10);
  private final Button ladderButton = new Button("Ladder Game");
  private final Button ludoButton = new Button("Ludo");
  private final Label welcomeLabel = new Label("Welcome!");

  public WelcomeView() throws FileNotFoundException {
    root.setAlignment(Pos.CENTER);
    root.getChildren().addAll(welcomeLabel,ladderButton, ludoButton);

    welcomeLabel.setStyle("-fx-font-size: 65px;");

    setButtonStyle();
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

  public void setButtonStyle() throws FileNotFoundException {

    FileInputStream ladderGameImageStream = new FileInputStream("src/main/resources/game_images/ladderGame.png");
    FileInputStream ludoImageStream = new FileInputStream("src/main/resources/game_images/ludo.png");


    Image ladderGame90Image = new Image(ladderGameImageStream);
    Image ludoImage = new Image(ludoImageStream);


    javafx.scene.image.ImageView ladderGameImageView = new javafx.scene.image.ImageView(ladderGame90Image);
    javafx.scene.image.ImageView ludoImageView = new ImageView(ludoImage);


    ladderGameImageView.setFitWidth(65);
    ladderGameImageView.setFitHeight(65);
    ludoImageView.setFitWidth(65);
    ludoImageView.setFitHeight(65);


    ladderButton.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ludoButton.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ladderButton.setGraphic(ladderGameImageView);
    ludoButton.setGraphic(ludoImageView);
  }
}
