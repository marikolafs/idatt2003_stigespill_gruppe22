package edu.ntnu.idi.idatt.view;


import static edu.ntnu.idi.idatt.observer.events.Event.PLAYER_MOVED;
import static edu.ntnu.idi.idatt.observer.events.Event.ROLL_DICE;

import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.events.Event;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The PlayerView class represents the view for a player in the game.
 * It displays the player's name, current tile, and dice roll value including a roll button.
 */
public class PlayerView extends HBox implements BoardGameObserver {
  private final Button rollButton;
  private  Text playerName;
  private  Text diceRollValue;
  private final ImageView dice1ImageView;
  private final ImageView dice2ImageView;
  private VBox vBox;
  private ImageView pieceImageView;
  private String pieceName;

  /**
   * Adds a button to roll the dice, an image of the current players piece and
   * a text field for the player name, tile name, and dice roll value.
   *
   * @throws FileNotFoundException if an image file cannot be found.
   */
  public PlayerView() throws FileNotFoundException {
    this.setSpacing(50);
    this.rollButton = new Button("ROLL DICE");
    this.playerName = new Text("Player Name");
    this.dice1ImageView = new ImageView();
    this.dice2ImageView = new ImageView();
    this.vBox = new VBox();
    this.pieceImageView = new ImageView();
    this.pieceName = pieceName;

    this.setAlignment(Pos.CENTER);
    vBox.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10, 10, 50, 10));


    vBox.getChildren().addAll(pieceImageView, playerName);
    this.getChildren().addAll(vBox, rollButton, dice1ImageView, dice2ImageView);

    rollButton.setStyle("-fx-background-color:OLIVE;");
  }

  /**
   * Returns the button to roll the dice.
   * @return the roll button
   */
  public Button getRollButton() {
    return rollButton;
  }



  /**
   * Returns the text field for the player name.
   * @param playerName the player name
   */
  public void setPlayerName(String playerName) {
    this.playerName.setText(playerName);
  }

  /**
   * Sets the images of the dice based on their values.
   *
   * @param dice1Value the value of the first die
   * @param dice2Value the value of the second die
   */
  public void setDiceImages(int dice1Value, int dice2Value) {
    try {
      // Load images based on dice values
      FileInputStream dice1Stream = new FileInputStream("src/main/resources/dice/dice-" + dice1Value + ".png");
      FileInputStream dice2Stream = new FileInputStream("src/main/resources/dice/dice-" + dice2Value + ".png");

      Image dice1Image = new Image(dice1Stream);
      Image dice2Image = new Image(dice2Stream);

      dice1ImageView.setImage(dice1Image);
      dice2ImageView.setImage(dice2Image);


      dice1ImageView.setFitWidth(50);
      dice1ImageView.setFitHeight(50);
      dice2ImageView.setFitWidth(50);
      dice2ImageView.setFitHeight(50);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates the image of the players piece.
   *
   * @param pieceName the name for the path to the image of the piece
   */
  public void setPieceImage(String pieceName) {
    String piecePath = "/images/pieces/" + pieceName + ".png";
    InputStream inputStream = getClass().getResourceAsStream(piecePath);
    if (inputStream == null) {
      throw new IllegalArgumentException("Image not found at path: " + piecePath);
    }
    pieceImageView.setFitWidth(50);
    pieceImageView.setFitHeight(50);

    Image image = new Image(inputStream);
    this.pieceImageView.setImage(image);
  }

  /**
   * Updates the view based on the game event.
   *
   * @param event the game event
   */
  @Override
  public void stateChanged(GameEvent event) {
    switch (event.getEventType()){
        case PLAYER_MOVED -> {
            if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Platform.runLater(() -> {
                setPlayerName(player.getName());
            });
            }
        }
      case ROLL_DICE -> {
            if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Platform.runLater(() -> {
                setPlayerName(player.getName());
            });
            }
        }
      case PLAYER_CHANGE -> {
            if (event.getPlayer() instanceof Player player) {
            Platform.runLater(() -> {
                setPlayerName(player.getName());
                setPieceImage(player.getPiece());
            });
            }
        }
      case PLAYER_PIECE_CHANGED -> {
            if (event.getPlayer() instanceof Player player) {
            Platform.runLater(() -> {
              setPieceImage(player.getPiece());
            });
            }
        }

      default -> {
          // Handle other events if needed
      }
    }
  }
}
