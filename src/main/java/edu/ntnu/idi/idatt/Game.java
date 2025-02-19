package edu.ntnu.idi.idatt;

public class Game {
  private int numberOfTiles;
  private int numberOfDice;
  private int numberOfPlayers;

  public Game(int numberOfTiles, int numberOfDice, int numberOfPlayers) {
    this.numberOfTiles = numberOfTiles;
    this.numberOfDice = numberOfDice;
    this.numberOfPlayers = numberOfPlayers;
  }

  public int getNumberOfTiles() {
    return numberOfTiles;
  }

  public int getNumberOfDice() {
    return numberOfDice;
  }
  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }
}
