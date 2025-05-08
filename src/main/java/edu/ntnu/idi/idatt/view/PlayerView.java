package edu.ntnu.idi.idatt.view;


import static edu.ntnu.idi.idatt.observer.events.Event.PLAYER_MOVED;
import static edu.ntnu.idi.idatt.observer.events.Event.ROLL_DICE;

import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.BoardGameObserver;
import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.events.Event;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  private  Text tileName;
  private  Text diceRollValue;
  private final ImageView dice1ImageView;
  private final ImageView dice2ImageView;
  private VBox vBox;

  /**
   * Adds a button to roll the dice, an image of the current players piece and
   * a text field for the player name, tile name, and dice roll value.
   *
   * @throws FileNotFoundException if an image file cannot be found.
   */
  public PlayerView() throws FileNotFoundException {
    this.setSpacing(50);
    this.rollButton = new Button("Roll");
    this.playerName = new Text("Player Name");
    this.tileName = new Text("Current Tile");
    this.diceRollValue = new Text("Dice Roll Value");
    this.dice1ImageView = new ImageView();
    this.dice2ImageView = new ImageView();
    this.vBox = new VBox();

    this.setAlignment(Pos.CENTER);
    vBox.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10, 10, 50, 10));


    // Player piece image
    FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setFitWidth(50);

    vBox.getChildren().addAll(imageView, playerName);
    this.getChildren().addAll(vBox, rollButton, dice1ImageView, dice2ImageView);
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
   * @param diceRollValue the dice roll value
   */
  public void setDiceRollValue(int diceRollValue) {
    this.diceRollValue.setText("Dice roll " + diceRollValue);
  }

  /**
   * Returns the text field for the player name.
   * @param playerName the player name
   */
  public void setPlayerName(String playerName) {
    this.playerName.setText(playerName);
  }

  /**
   * Returns the text field for the tile name.
   * @param tileName the tile id
   */
  public void setTileName(int tileName) {
    this.tileName.setText(String.valueOf(tileName));
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

      // Set image sizes
      dice1ImageView.setFitWidth(50);
      dice1ImageView.setFitHeight(50);
      dice2ImageView.setFitWidth(50);
      dice2ImageView.setFitHeight(50);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
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
                setTileName(player.getCurrentTile().getTileId());
            });
            }
        }
      case ROLL_DICE -> {
            if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Platform.runLater(() -> {
                setPlayerName(player.getName());
                setTileName(player.getCurrentTile().getTileId());
            });
            }
        }
      case PLAYER_CHANGE -> {
            if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Platform.runLater(() -> {
                setPlayerName(player.getName());
                setTileName(player.getCurrentTile().getTileId());
            });
            }
        }

      default -> {
          // Handle other events if needed
      }
    }
  }
}
