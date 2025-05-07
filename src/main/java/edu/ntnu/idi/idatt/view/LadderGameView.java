package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.BoardGameFactory;
import edu.ntnu.idi.idatt.engine.BoardGame;
import edu.ntnu.idi.idatt.model.Board;
import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.model.Tile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LadderGameView extends BorderPane {

  GameBoardView gameBoardView;

  BoardGameFactory boardGameFactory = new BoardGameFactory();
  private static BoardGame game = BoardGame.getInstance(BoardGame.getName(),
      BoardGame.getDescription());
  private Board board = game.getBoard();
  private Map<Integer, Tile> tiles = board.getTiles();

  private int playerCount;
  private static List<Player> players = game.getPlayers();
  private final List<String> availablePieces = new ArrayList<>(List.of("Pig", "Cat", "Rabbit", "Chicken", "Sheep"));
  private final Button ladderButton = new Button("LadderGame");
  private final Button ludoButton = new Button("Ludo");

  BorderPane layout = new BorderPane();
  Scene scene = new Scene(layout, 300, 300, Color.WHITE);

  public LadderGameView(GameBoardView gameBoardView, PlayerView playerView) {
    setCenter(gameBoardView);
    setBottom(playerView);
  }

  public LadderGameView(Stage stage) throws Exception {
    welcomeView(stage);
    selectPlayerSetupView(stage, "LadderGame");
    gameBoardView();
    //playerView();
    scene.setRoot(layout);
    stage.setTitle("Ladder Game");
    stage.setScene(welcomeScene);
  }

  /**
   * View for the board and tiles, as well as the players on the board.
   *
   * @throws FileNotFoundException if an image file cannot be found.
   */
  //TODO edit to be updated by the observer
  public void gameBoardView() throws FileNotFoundException {
    GridPane gridPane = new GridPane();

    // adding tiles and labelling them
    for (Tile tile : tiles.values()) {
      StackPane tilePane = new StackPane();
      Label label = new Label("tile: " + tile.getTileId());
      Rectangle r = new Rectangle();
      r.setX(tile.getX());
      r.setY(tile.getY());
      r.setWidth(50);
      r.setHeight(50);
      if (tile.getTileId() % 2 == 0) {
        r.setFill(Color.BEIGE);
      } else {
        r.setFill(Color.OLIVE);
      }

      tilePane.getChildren().addAll(r, label);
      gridPane.add(tilePane, tile.getX(), tile.getY());
    }

    // Adding players to the tile they´re on
    // Should be updated to replace the image based on the chosen player piece
    for (Player player : players) {
      StackPane playerPane = new StackPane();

      FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
      Image image = new Image(inputstream);
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(40);
      imageView.setFitWidth(40);

      Label label = new Label(player.getPiece());
      label.setStyle("-fx-font-size: 5;");

      playerPane.getChildren().addAll(imageView, label);
      gridPane.add(playerPane, player.getCurrentTile().getX(), player.getCurrentTile().getY());
    }

    gridPane.setAlignment(Pos.CENTER);
    layout.setCenter(gridPane);
  }

  /**
   * View for currentPlayers menu
   *
   * @throws FileNotFoundException if the image file cannot be found
   */

  /*
  public void playerView() throws FileNotFoundException {

    HBox hBox = new HBox();

    FileInputStream inputstream = new FileInputStream("src/main/resources/images/pieces/kitty.png");
    Image image = new Image(inputstream);
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(50);
    imageView.setFitWidth(50);

    Button rollButton = new Button("Roll");
    rollButton.setOnAction(e -> {
      BoardGame.play();
    });
    Text playerName = new Text("Player Name");
    Text tileName = new Text("Current Tile");

    hBox.getChildren().addAll(imageView,  rollButton,  playerName, tileName);

    layout.setBottom(hBox);
  }

   */

  Group root = new Group();
  Group root2 = new Group();
  Group root3 = new Group();
  Scene playerScene2 = new Scene(root3);
  Scene playerScene = new Scene(root2, 300, 300, Color.WHITE);
  Scene welcomeScene = new Scene(root, 300, 300, Color.WHITE);

  public void welcomeView(Stage stage) throws FileNotFoundException {
    TilePane tilePane = new TilePane();

    Label welcomeLabel = new Label("Welcome! What game would you like to play?");

    tilePane.setAlignment(Pos.CENTER);
    tilePane.setHgap(10);
    tilePane.setVgap(10);
    tilePane.getChildren().addAll(welcomeLabel, ladderButton, ludoButton);
    root.getChildren().addAll(tilePane);
    game.setName("Select a game");
    game.setDescription("Select a game");
  }

  public void selectPlayerSetupView(Stage stage, String gameType) throws FileNotFoundException {
    Label label = new Label("How many players?");
    ComboBox<Integer> playerCountBox = new ComboBox<>();
    if (gameType.equals("LadderGame")) {
      playerCountBox.getItems().addAll(2, 3, 4, 5);
    } else if (gameType.equals("Ludo")) {
      playerCountBox.getItems().addAll(2, 3, 4);
    }
    playerCountBox.setValue(2);

    Button nextButton = new Button("Next");
    nextButton.setOnAction(e -> {
      playerCount = playerCountBox.getValue();
      try {
        showPlayerScene(stage, gameType);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    VBox playerLayout = new VBox(10, label,  playerCountBox, nextButton);
    playerLayout.setPadding(new Insets(20));
    Scene  playerScene = new Scene(playerLayout, 300, 150);
    stage.setScene(playerScene);
    stage.show();
  }

  private void showPlayerScene(Stage stage, String gameType) throws FileNotFoundException {
    VBox playerLayout = new VBox(15);
    playerLayout.setPadding(new Insets(20));

    List<TextField> nameFields = new ArrayList<>();
    List<ComboBox<String>>  pieceFields = new ArrayList<>();

    List<String> availablePieces = new ArrayList<>();
    if (gameType.equals("LadderGame")) {
      availablePieces.addAll(List.of("Pig", "Cat", "Rabbit", "Chicken", "Sheep"));
    } else if (gameType.equals("Ludo")) {
      availablePieces.addAll(List.of("Cat", "Rabbit", "Chicken", "Sheep"));
    }

    for (int i = 0; i < playerCount; i++) {
      Label playerLabel  = new Label("Player " + (i + 1));

      TextField nameField = new TextField();
      nameField.setPromptText("Player name");

      ComboBox<String> pieceField = new ComboBox<>();
      pieceField.getItems().addAll(availablePieces);

      nameFields.add(nameField);
      pieceFields.add(pieceField);

      VBox playerBox = new VBox(5, playerLabel, nameField, pieceField);
      playerLayout.getChildren().add(playerBox);
    }

    Button startButton = new Button("Start");
    startButton.setOnAction(e -> {
      players.clear();
      Set<String> usedPieces = new HashSet<>();
      for (int i = 0; i < playerCount; i++) {
        String name = nameFields.get(i).getText().trim();
        String piece = pieceFields.get(i).getValue();
        usedPieces.add(name);
        if (name.isEmpty() || piece == null  || usedPieces.contains(piece)) {
          showAlert("Error", "All players must have a unique name and piece.");
          return;
        }
        usedPieces.add(piece);
        players.add(new Player(name, game, piece));
      }

      System.out.println("Game started with players: ");
      players.forEach(player -> {
        System.out.println(player.getName() + " (" + player.getPiece() + ")");
      });

      try {
        initalizeGame(gameType);
        stage.setScene(scene);
      } catch (FileNotFoundException ex) {
        throw new RuntimeException(ex);
      }
    });

    playerLayout.getChildren().add(startButton);
    Scene playerScene = new Scene(playerLayout, 400, 100 + playerCount*90);
    stage.setScene(playerScene);
    stage.setTitle("Player setup");
  }

  private void initalizeGame(String gameType) throws FileNotFoundException {
    if (gameType.equals("LadderGame")) {
      try {
        boardGameFactory.createLadderGame90();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else if (gameType.equals("Ludo")) {
      try {
        boardGameFactory.createLadderGame45(); // change once ludo is incorporated
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
