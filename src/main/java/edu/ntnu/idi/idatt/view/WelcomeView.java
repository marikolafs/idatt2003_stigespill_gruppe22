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
  private final Button ladder90Button = new Button("Ladder Game 90");
  private final Button ludoButton = new Button("Ludo");
  private final Label welcomeLabel = new Label("Welcome!");
  private final Button ladder90PlusButton = new Button("Ladder Game 90 +");
  private final Button ladder45Button = new Button("Ladder Game 45");

  public WelcomeView() throws FileNotFoundException {
    root.setAlignment(Pos.CENTER);
    root.getChildren().addAll(welcomeLabel, ladder45Button, ladder90Button, ladder90PlusButton, ludoButton);

    welcomeLabel.setStyle("-fx-font-size: 65px;");

    setButtonStyle();
  }

  public VBox getView() {
    return root;
  }

  public Button getLadder90Button() {
    return ladder90Button;
  }

  public Button getLadder45Button() {
    return ladder45Button;
  }

  public Button getLadder90PlusButton() {
    return ladder90PlusButton;
  }

  public Button getLudoButton() {
    return ludoButton;
  }

  public void setButtonStyle() throws FileNotFoundException {

    FileInputStream ladderGameImageStream = new FileInputStream("src/main/resources/game_images/ladderGame.png");
    FileInputStream ludoImageStream = new FileInputStream("src/main/resources/game_images/ludo.png");
    FileInputStream ladderGame90ImageStream = new FileInputStream("src/main/resources/game_images/LadderGame90.PNG");
    FileInputStream ladderGame90PlusImageStream = new FileInputStream("src/main/resources/game_images/LadderGame90Plus.PNG");
    FileInputStream ladderGame45ImageStream = new FileInputStream("src/main/resources/game_images/LadderGame45.PNG");


    //Image ladderGame90Image = new Image(ladderGameImageStream);
    Image ludoImage = new Image(ludoImageStream);
    Image ladderGame90PlusImage = new Image(ladderGame90PlusImageStream);
    Image ladderGame45Image = new Image(ladderGame45ImageStream);
    Image ladderGame90Image = new Image(ladderGame90ImageStream);


    javafx.scene.image.ImageView ladderGameImageView = new javafx.scene.image.ImageView(ladderGame90Image);
    javafx.scene.image.ImageView ludoImageView = new ImageView(ludoImage);
    javafx.scene.image.ImageView ladderGame90ImageView = new javafx.scene.image.ImageView(ladderGame90Image);
    javafx.scene.image.ImageView ladderGame90PlusImageView = new javafx.scene.image.ImageView(ladderGame90PlusImage);
    javafx.scene.image.ImageView ladderGame45ImageView = new javafx.scene.image.ImageView(ladderGame45Image);


    ladderGameImageView.setFitWidth(65);
    ladderGameImageView.setFitHeight(65);

    ladderGame90ImageView.setFitWidth(65);
    ladderGame90ImageView.setFitHeight(65);

    ladderGame90PlusImageView.setFitWidth(65);
    ladderGame90PlusImageView.setFitHeight(65);

    ladderGame45ImageView.setFitWidth(65);
    ladderGame45ImageView.setFitHeight(65);

    ludoImageView.setFitWidth(65);
    ludoImageView.setFitHeight(65);


    ladder90Button.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ludoButton.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ladder45Button.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ladder90PlusButton.setStyle("-fx-background-color: OLIVE; -fx-font-size: 32px;");
    ladder90Button.setGraphic(ladderGame90ImageView);
    ladder90PlusButton.setGraphic(ladderGame90PlusImageView);
    ladder45Button.setGraphic(ladderGame45ImageView);
    ludoButton.setGraphic(ludoImageView);
  }
}
