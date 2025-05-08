package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

  private final Stage stage;
  private final Map<String, Scene> scenes = new HashMap<>();

  public SceneManager(Stage stage) {
    this.stage = stage;
  }

  public void switchScene(Scene scene, String title) {
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
  }

  public Scene getScene(String title) {
    return scenes.get(title);
  }

  public Stage getStage() {
    return stage;
  }

}
