package edu.ntnu.idi.idatt.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Responsible for managing scenes.
 */
public class SceneManager {
  private static SceneManager instance;
  private final Stage primaryStage;
  private final Map<String, Scene> scenes = new HashMap<>();


  public SceneManager(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  /**
   * Singleton method to get the instance of SceneManager.
   *
   * @param primaryStage the primary stage of the application
   * @return the single instance of SceneManager
   */
  public static SceneManager getInstance(Stage primaryStage) {
    if (instance == null) {
      instance = new SceneManager(primaryStage);
    }
    return instance;
  }

  /**
   * Adds a scene to the scene map of scenes.
   *
   * @param name  the name of the scene
   * @param scene the scene to be added
   */
  public void addScene(String name, Scene scene) {
    scenes.put(name, scene);
  }

  /**
   * Retrieves a scene by its name.
   *
   * @param name the name of the scene
   * @return the scene associated with the name
   */
  public Scene getScene(String name) {
    if (!scenes.containsKey(name)) {
      throw new IllegalArgumentException("Scene with title " + name + " not found.");
    }
    return scenes.get(name);
  }

  /**
   * Switches the current scene to the specified scene and sets the title of the primary stage.
   *
   * @param scene the scene to switch to
   * @param title the title of the primary stage
   */
  public void switchScene(Scene scene, String title) {
    if (scene == null) {
      throw new IllegalArgumentException("Scene cannot be null.");
    }
    primaryStage.setScene(scene);
    primaryStage.setTitle(title);
    primaryStage.show();
  }
}