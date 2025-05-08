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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

    this.setAlignment(Pos.CENTER);


    // Player piece image
    FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setFitWidth(50);

    this.getChildren().addAll(imageView, rollButton, playerName, tileName, diceRollValue);
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
